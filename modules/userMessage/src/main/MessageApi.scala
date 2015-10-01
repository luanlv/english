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

  def insert(mesId: String, mv: Int, fromId: String, toId: String, mes: String, time: DateTime) = {
    Env.current.messageRepo.insert(mesId, mv, fromId, toId, mes, time)
  }

  def getInitMes(mesId: String) = {
    Env.current.messageRepo.getInitMes(mesId)
  }

  def getMissingMes(listMesIds: Array[String]) = {
    Env.current.messageRepo.getMissingMes(listMesIds)
  }
}
