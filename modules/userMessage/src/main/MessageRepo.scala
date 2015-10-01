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

  def lastMesVersion(mesId: String) = {
    repo.lastMesVersion(mesId)
  }
  def lastUserMesVersion(userId: String) = {
      repo.lastUserMesVersion(userId)
    }

  def insert(mesId: String, mv: Int, fromId: String, toId: String, mes: String, time: DateTime) = {
    repo.insert(mesId, mv, fromId, toId, mes, time)
  }

  def getInitMes(mesId: String, cv: Int) = {
    repo.getInitMes(mesId, cv)
  }

  def getMissingMes(listMesIds: Array[String]) = {
    repo.getMissingMes(listMesIds)
  }

}


//val bson = BSONFormats.toBSON(o).get.asInstanceOf[BSONDocument]