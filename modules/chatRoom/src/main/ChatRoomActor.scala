package lila.chatRoom

import akka.actor.{ Actor, ActorSelection }
import akka.pattern.{ ask, pipe }
import lila.hub.actorApi.chatRoom._
import lila.memo.ExpireSetMemo
import org.joda.time.DateTime
import play.api.libs.json._

import actorApi._

import lila.common.LightUser
import lila.hub.actorApi.relation._
import lila.hub.actorApi.{SendTos, SendTo}
import makeTimeout.short
import play.modules.reactivemongo.json._
import reactivemongo.bson.BSONDocument
import scala.concurrent.duration._

private[chatRoom] final class ChatRoomActor(
                                                 lightUser: String => Option[LightUser],
                                                 api: Api) extends Actor {

  val listUsers = scala.collection.mutable.Map.empty[String, ExpireSetMemo]
  def roomById(s: String) = {
    listUsers.get(s) match {
      case None => val memo = new ExpireSetMemo(1 minute); listUsers += (s -> memo); memo
      case Some(memo) => memo
    }
  }

  private val bus = context.system.lilaBus

  private var onlines = Map[ID, LightUser]()

  def receive = {
    case UserSubscribe(u, roomId) => subscribeUser(u, roomId);
    case UserUnSubscribe(u, roomId) => unSubscribleUser(u, roomId)
    case GetInitChatRoom(roomId) => sender ! initChat(roomId)

    case _ =>
  }
  private def onlineIds: Set[ID] = onlines.keySet


  def subscribeUser(user: String, roomId: String) = {
    if(!roomById(roomId).get(user)){
      roomById(roomId) put user
      lila.hub.Env.current.socket.site ! UserEnterRoom(user, roomId)
    }
  }

  def unSubscribleUser(user: String, roomId: String) = {
    roomById(roomId) remove user
    lila.hub.Env.current.socket.site ! UserLeavesRoom(user, roomId)
  }

  def initChat(roomId: String) = {
    roomById(roomId).keySet
  }
}
