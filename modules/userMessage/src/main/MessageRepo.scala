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
import lila.memo.UserMessage
import play.modules.reactivemongo.json.BSONFormats
import scala.concurrent.ExecutionContext.Implicits.global

final class MessageRepo(repo: UserMessage.Builder) {

  def lastMesVersion(chatId: String) = {
    repo.lastMesVersion(chatId)
  }
  def lastUserMesVersion(userId: String) = {
      repo.lastUserMesVersion(userId)
    }

  def insert(mesId: String, v: Int, fromId: String, vSender: Int, toId: String, vReceive: Int, mes: String, time: DateTime) = {
    repo.insert(mesId, v, fromId, vSender, toId, vReceive, mes, time)
  }

  def getInitMes(mesId: String) = {
    repo.getInitMes(mesId).map{
      data => data.map{
        mes => BSONFormats.toJSON(mes).as[JsObject].-("_id").-("lid").-("mid")
      }
    }
  }

}


//val bson = BSONFormats.toBSON(o).get.asInstanceOf[BSONDocument]