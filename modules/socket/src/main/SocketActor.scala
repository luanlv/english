package lila.socket

import lila.common.LightUser
import lila.hub
import lila.hub.actorApi.chatRoom._

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.util.Random
import akka.pattern.{ ask, pipe }
import akka.actor.{ Deploy => _, _ }
import play.api.libs.json._
import play.twirl.api.Html

import actorApi._
import lila.hub.actorApi.{ Deploy, GetUids, GetUserIds }
import lila.memo.ExpireSetMemo
import makeTimeout.large

abstract class SocketActor[M <: SocketMember](uidTtl: Duration) extends Socket with Actor {


  val listRoomIds = List("123")
  val listSid  = scala.collection.mutable.Map.empty[String, (Option[String], String)]

  val members = scala.collection.mutable.Map.empty[String, M]
  val aliveUids = new ExpireSetMemo(uidTtl)
  var pong = Socket.initialPong

  val lilaBus = context.system.lilaBus

  // this socket is created during application boot
  // and therefore should delay its publication
  // to ensure the listener is ready (sucks, I know)

  val startsOnApplicationBoot: Boolean = false

  override def preStart() {
    if (startsOnApplicationBoot)
      context.system.scheduler.scheduleOnce(1 second) {
        lilaBus.publish(lila.socket.SocketHub.Open(self), 'socket)
      }
    else lilaBus.publish(lila.socket.SocketHub.Open(self), 'socket)
  }

  override def postStop() {
    lilaBus.publish(lila.socket.SocketHub.Close(self), 'socket)
    members.keys foreach eject
  }

  // to be defined in subclassing actor
  def receiveSpecific: Receive

  // generic message handler
  def receiveGeneric: Receive = {

    case Ping(uid, v)             => ping(uid, v)

    case SetAlive(uid)         => setAlive(uid)

    case SendName(uid, id, name)   => sendName(uid, id,  name)

    case OnlineFriends(uid, listUser) => sendOnlineFriend(uid, listUser)

    case SendMissingMes(uid, f, t, data) => sendMissingMes(uid, f, t, data)

    case SendInitMes(uid, data) => sendInitMes(uid, data)

    case SendInitNotify(uid, data) => sendInitNotify(uid, data)

    case InitChatRoom(uid, roomId, userId) => {
      println(roomId)
      if(roomId != "chatrooms") initChat(uid, roomId, userId)
      else initChatRooms(uid, roomId, userId)
    }

    case GetPrevChat(uid, roomId, lastTime) => getPrevChat(uid, roomId, lastTime)

    case Sub(uid, roomId, userId) => sub(uid, roomId, userId)

    case UnSub(uid, roomId) => unSub(uid, roomId)

    case UserEnterRoom(user, roomId) => notifyUserEnterRoom(user, roomId)

    case UserLeavesRoom(user, roomId) => notifyUserLeaveRoom(user, roomId)

    case DoChat(chat, roomId) =>  sendChatRoom(chat, roomId)

    case Broom                 => {
      broom
    }

    // when a member quits
    case Quit(uid)             => {
      if(listSid.keySet.contains(uid)) unSub(uid)
      quit(uid)
    }

    case NbMembers(_, pongMsg) => pong = pongMsg

    case GetUids               => sender ! uids

    case GetUserIds            => sender ! userIds

    case Resync(uid)           => {
      resync(uid)
    }

    case d: Deploy             => {
      onDeploy(d)
    }
  }

  def receive = receiveSpecific orElse receiveGeneric

  def notifyAll[A: Writes](t: String, data: A) {
    notifyAll(makeMessage(t, data))
  }

  def notifyAll(t: String) {
    notifyAll(makeMessage(t))
  }

  def notifyAll(msg: JsObject) {
    members.values.foreach(_ push msg)
  }

  def notifyMember[A: Writes](t: String, data: A)(member: M) {
    member push makeMessage(t, data)
  }



  def ping(uid: String, notify: Int) {
    setAlive(uid)
    withMember(uid)(_ push makeMessage("n", pong.++(Json.obj("n" -> notify))))
  }

  def listUidInRoom(roomId: String) = {
    listSid collect {
      case (uid, (_, idRoom)) if (roomId == idRoom) =>  uid
    }
  }

  def notifyUserEnterRoom(user: String, roomId: String) {
    val mes = Json.obj("room" -> roomId, "t" -> "userEnter", "u" -> Json.obj("name" -> user, "role" -> "user", "avatar" -> "/assets/avatar/2.jpg"))
    listUidInRoom(roomId) foreach {uid =>
      withMember(uid)(_ push makeMessage("chatNotify", mes))
    }
  }

  def notifyUserLeaveRoom(user: String, roomId: String) = {
    val mes = Json.obj("room" -> roomId, "t" -> "userLeaves", "u" -> user)
    listUidInRoom(roomId) foreach {uid =>
      withMember(uid)(_ push makeMessage("chatNotify", mes))
    }
  }

  def sendChatRoom(chat: JsObject, roomId: String) = {
    listUidInRoom(roomId) foreach { uid =>
      withMember(uid)(_ push makeMessage("chatNotify", chat))
    }
  }

  def initChatRooms(uid: String, roomId: String, userId:Option[String]) = {
    sub(uid, roomId, userId)
    val listByGroup = listSid.groupBy(x => x._2._2)
    val data = listRoomIds.map(roomId => Json.obj("id" -> roomId, "c" -> (if(listByGroup.contains(roomId)) listByGroup(roomId).toSeq.length else 0), "u" -> (if(listByGroup.contains(roomId)) listByGroup(roomId).toSeq.filter(x => x._2._1.get.length > 0 ).length else 0)))
    listUidInRoom(roomId) foreach { uid =>
      withMember(uid)(_ push makeMessage("chatNotify", Json.obj("t" -> "initChatRooms" , "v" -> data)))
    }
  }

  def initChat(uid: String, roomId: String, userId:Option[String]) = {
    sub(uid, roomId, userId)
    (lila.hub.Env.current.actor.chatRoom ? GetInitChatRoom(roomId)) foreach {
      case data: JsObject => {
        withMember(uid)(_ push makeMessage("chatNotify", data))
      }
    }
  }

  def getPrevChat(uid: String, roomId: String, lastTime: Long) = {
    (lila.hub.Env.current.actor.chatRoom ? PrevChat(roomId, lastTime)) foreach {
      case data: JsValue => {
        val mes = Json.obj("room" -> roomId, "t" -> "prevChat" , "lc" -> data)
        withMember(uid)(_ push makeMessage("chatNotify", mes))
      }
    }
  }

  def changeNBMember(roomId: String, data: JsObject) = {
    listUidInRoom(roomId) foreach { uid =>
      withMember(uid)(_ push makeMessage("chatNotify", data))
    }
  }


  def sub(uid: String, roomId: String, userId: Option[String]) = {
    if(!listSid.contains(uid)){
      userId foreach { userId =>
        if(userId.length > 0){
          if(roomId != "roomchats") {
            if(!userInRoom(userId, roomId, listSid.keys.toList)) {
              listSid += (uid -> (Some(userId), roomId))
              val data = Json.obj("t" -> "io", "v" -> Json.obj("rid" -> roomId, "c" -> 1, "u" -> 1))
              changeNBMember("chatrooms", data)
              changeNBMember("roomId", data)
            } else {
              listSid += (uid -> (Some(userId), roomId))
              val data = Json.obj("t" -> "io", "v" -> Json.obj("rid" -> roomId, "c" -> 1))
              changeNBMember("chatrooms", data)
              changeNBMember("roomId", data)
            }
          } else {
            listSid += (uid -> (Some(userId), roomId))
          }
        } else {
          listSid += (uid -> (Some(userId), roomId))
          if(roomId != "roomchats") {
            val data = Json.obj("t" -> "io", "v" -> Json.obj("rid" -> roomId, "c" -> 1))
            changeNBMember("chatrooms", data)
            changeNBMember(roomId, data)
          }
        }
      }
    }
  }


  def unSub(uid: String):Unit = {
    unSub(uid, listSid(uid)._2)
  }

  def unSub(uid: String, roomId: String):Unit = {
    listSid(uid)._1 foreach { userId =>
      if(userId.length > 0){
        listSid -= uid
        if(!userInRoom(userId, roomId, listSid.keys.toList)) {
          lila.hub.Env.current.actor.chatRoom ! UserUnSubscribe(userId, roomId)
          if(roomId != "chatrooms" ){
            val data = Json.obj("t" -> "io", "v" -> Json.obj("rid" -> roomId, "u" -> -1, "c" -> -1))
            changeNBMember(roomId, data)
            changeNBMember("chatrooms", data)
          }
        }
      } else {
        listSid -= uid
        val data = Json.obj("t" -> "io", "v" -> Json.obj("rid" -> roomId, "c" -> -1))
        changeNBMember("chatrooms", data)
      }

    }
  }

  def userInRoom(userId: String, roomId: String, sids: List[String]):Boolean = {
    if (sids.isEmpty)  false
    else if(listSid(sids.head)._1.contains(userId) && listSid(sids.head)._2 == roomId) true
    else userInRoom(userId, roomId, sids.tail)
  }


  def uidByUserId(userId: String): Iterable[String] = members collect {
    case (uid , member) if member.userId.contains(userId) => uid
  }

  def broom {
    members.keys foreach { uid =>
      if (!aliveUids.get(uid)) eject(uid)
    }

    listSid.keys foreach { uid =>
      if (!aliveUids.get(uid)) {
        unSub(uid)
      }
    }

  }

  def eject(uid: String) {
    withMember(uid) { member =>
      member.end
      quit(uid)
    }
  }

  def quit(uid: String) {
    members get uid foreach { member =>
      members -= uid
      lilaBus.publish(SocketLeave(uid, member), 'socketDoor)
    }
  }

  def onDeploy(d: Deploy) {
    notifyAll(makeMessage(d.event.key, d.html))
  }

  private val resyncMessage = makeMessage("resync")

  protected def resync(member: M) {
    import scala.concurrent.duration._
    context.system.scheduler.scheduleOnce((Random nextInt 2000).milliseconds) {
      resyncNow(member)
    }
  }

  protected def resync(uid: String) {
    withMember(uid)(resync)
  }

  protected def resyncNow(member: M) {
    member push resyncMessage
  }

  def addMember(uid: String, member: M) {
    eject(uid)
    members += (uid -> member)
    setAlive(uid)
    lilaBus.publish(SocketEnter(uid, member), 'socketDoor)
  }

  def setAlive(uid: String) { aliveUids put uid }

  def sendName(uid: String, id: String, name: String) {
    withMember(uid)(_ push makeMessage("nu", Json.obj("id" -> id, "n" -> name)))
  }

  def sendOnlineFriend(uid: String, listUser: List[LightUser]) {
    withMember(uid)(_ push makeMessage("ul", listUser))
  }

  def sendMissingMes(uid: String, f: Int, t: Int, data: List[JsValue]){
    withMember(uid)(_ push makeMessage("smm", Json.obj("f" -> f, "t" -> t, "d" -> data)))
  }

  def sendInitMes(uid: String, data: List[JsValue]) {
    withMember(uid)(_ push makeMessage("init_chat", data))
  }

  def sendInitNotify(uid: String, data: List[JsValue]) = {
    withMember(uid)(_ push makeMessage("init_notify", data))
  }
  def uids = members.keys

  def membersByUserId(userId: String): Iterable[M] = members collect {
    case (_, member) if member.userId.contains(userId) => member
  }

  def userIds: Iterable[String] = members.values.flatMap(_.userId)

  def showSpectators(users: List[lila.common.LightUser], nbAnons: Int) = nbAnons match {
    case 0 => users.distinct.map(_.titleName)
    case x => users.distinct.map(_.titleName) :+ ("Anonymous (%d)" format x)
  }

  def withMember(uid: String)(f: M => Unit) {
    members get uid foreach f
  }
}
