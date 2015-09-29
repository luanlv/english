package lila.userMessage

import akka.actor.{ Actor, ActorSelection }
import akka.pattern.{ ask, pipe }
import lila.hub.actorApi.userMessage._
import org.joda.time.DateTime
import play.api.libs.json._

import actorApi._


import lila.common.LightUser
import lila.hub.actorApi.relation._
import lila.hub.actorApi.{SendTos, SendTo}
import makeTimeout.short

private[userMessage] final class UserMessageActor(
                                               getOnlineUserIds: () => Set[String],
                                               lightUser: String => Option[LightUser],
                                               api: MessageApi) extends Actor {

  private val bus = context.system.lilaBus

  private var onlines = Map[ID, LightUser]()

  def receive = {

    case PingVersion(userId) => sendPong(userId)

    case Msg(userId, o) => sendMessage(userId, o)

    case GetOnlineUser(userId) => sendUserOnline(userId)

    case InitChat(fromId, toId) => sendInitChat(fromId, toId)

    case MissingMes(userId, f, t) => {
      sendMissingMes(userId, f, t)
    }

    case GetName(id) => lightUser(id) match {
      case None => sender ! "anonymous"
      case Some(user) => sender ! user.name
    }

    case NotifyMovement =>
      val prevIds = onlineIds
      val curIds = getOnlineUserIds()
      val leaveIds = (prevIds diff curIds).toList
      val enterIds = (curIds diff prevIds).toList
      val leaves = leaveIds.flatMap(i => lightUser(i))
      val enters = enterIds.flatMap(i => lightUser(i))
      onlines = onlines -- leaveIds ++ enters.map(e => e.id -> e)
      notifyFollowers(enters, "following_enters")
      notifyFollowers(leaves, "following_leaves")

    case _ =>
  }
  private def onlineIds: Set[ID] = onlines.keySet

  def sendPong(userId: String) {
    val mv = api.findLastesUserMesVersion(userId).await
    bus.publish(SendTo(userId, "p", mv), 'users)

  }

  def sendInitChat(fromId: String, toId: String) {
    val mesId = if(fromId < toId) fromId + toId else toId + fromId
    api.getInitMes(mesId).map{
      listMes => bus.publish(SendTo(fromId, "init_chat", listMes), 'users)
    }
  }

  def sendMissingMes(userId: String, f: Int, t: Int) {
    api.getMissingMes(userId, f, t).map{
      missingMes => bus.publish(SendTo(userId, "smm", missingMes), 'users)
    }
  }

  def sendMessage(fromId: String, o: JsObject) {
    val curUsers = getOnlineUserIds().flatMap(i => lightUser(i)).toList
    onlines = curUsers.map(e => e.id -> e).toMap
    (o\"to").asOpt[String] foreach { toId =>
      (o\"mes").asOpt[String] foreach { mes =>
        val compareId = fromId < toId
        val mesId = if(compareId) fromId + toId else toId + fromId

        var v = api.findLastMesVersion(mesId).await + 1
        var vSender = api.findLastesUserMesVersion(fromId).await + 1
        var vReceive = api.findLastesUserMesVersion(toId).await + 1
        var time = DateTime.now()

        api.insert(mesId, v, fromId, vSender, toId, vReceive, mes, time) map {
          writeResult => writeResult match {
            case ok if ok.ok   => {
              Env.current.cached.setNewVersion("chatVer:" + mesId, v)
              Env.current.cached.setNewVersion("userChatVer:" + fromId, vSender)
              Env.current.cached.setNewVersion("userChatVer:" + toId, vReceive)

              if(onlines.contains(toId)){
                val data = Json.obj("mv" -> v, "f" -> fromId, "t" -> toId, "m" -> mes, "time" -> time)
                bus.publish(SendTo(toId, "mes", data.++(Json.obj("v" -> vReceive))), 'users)
                bus.publish(SendTo(fromId, "mes", data.++(Json.obj("v" -> vSender))), 'users)
              }
            }
            case error         => println("save mes ERROR!")
          }
        }
      }
    }
  }

  private def notifyFollowers(users: List[LightUser], message: String) {
    users foreach { user =>
        bus.publish(SendTos(onlineIds.toSet, message, user.titleName), 'users)
    }
  }

  private def sendUserOnline(userId: String){
    bus.publish(SendTo(userId, "ul", onlineIds), 'users)
  }

}
