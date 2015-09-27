package lila.userMessage

import akka.actor.ActorSelection
import org.joda.time.DateTime
import scala.util.Success

import lila.db.api._
import lila.db.Implicits._

import lila.hub.actorApi.relation.ReloadOnlineFriends
import lila.hub.actorApi.timeline.{ Propagate, Follow => FollowUser }
import lila.usrMessage.MessageRepo

final class MessageApi(
                           //cached: Cached,
                           actor: ActorSelection,
                           bus: lila.common.Bus) {

  def findLastMesVersion(chatId: String) = {
    Env.current.cached.chatVersion(chatId)
  }

  def findLastesUserMesVersion(userId: String) = {
    Env.current.cached.userChatVersion(userId)
  }

  def insert(mesId: String, v: Int, fromId: String, vSender: Int, toId: String, vReceive: Int, mes: String, time: DateTime) = {
    Env.current.messageRepo.insert(mesId, v, fromId, vSender, toId, vReceive, mes, time)
  }

  def getInitMes(mesId: String) = {
    Env.current.messageRepo.getInitMes(mesId)
  }

}
