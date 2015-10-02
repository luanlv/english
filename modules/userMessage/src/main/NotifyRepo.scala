package lila.usrMessage

import play.modules.reactivemongo.json.BSONFormats

import scala.concurrent.Future
import scala.concurrent.duration._

import org.joda.time.DateTime
import play.api.libs.json._
import reactivemongo.bson._

import spray.caching.{ LruCache, Cache }

import lila.common.LightUser
import lila.db.api.{ $count, $primitive }
import lila.db.BSON._
import lila.db.Implicits._
import lila.memo.NotifyMessage
import play.modules.reactivemongo.json.BSONFormats
import scala.concurrent.ExecutionContext.Implicits.global


final class NotifyRepo(repo: NotifyMessage.Builder) {

  def notifyMessage(uid: String, chatId: String, mesId: String, mv: Int, mes: String, time: DateTime) = {
    repo.notifyMessage(uid, chatId, mesId, mv, mes, time)
  }

  def getNotify(uid: String) = {
    repo.getNotify(uid)
  }

  def getNotifyMessage(userId: String) = {
    repo.getNotifyMessage(userId)
  }

  def resetNotify(userId: String) = {
    repo.resetNotify(userId)
  }

  def markRead(userId: String, toId: String, mv: Int) = {
    repo.markRead(userId, toId, mv)
  }
}


//val bson = BSONFormats.toBSON(o).get.asInstanceOf[BSONDocument]