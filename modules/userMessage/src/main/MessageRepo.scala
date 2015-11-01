package lila.userMessage

import play.modules.reactivemongo.json._

import scala.concurrent.Future
import scala.concurrent.duration._

import org.joda.time.DateTime
import play.api.libs.json._
import reactivemongo.bson._

import spray.caching.{ LruCache, Cache }

import lila.common.LightUser
import lila.db.api.{ $count, $primitive }
import BSONHandlers._
import lila.db.BSON._
import lila.db.Implicits._
import scala.concurrent.ExecutionContext.Implicits.global


object MessageRepo {

  private lazy val coll = Env.current.userMessageColl

  def lastMesVersion(mesId: String) = {
    coll.find(BSONDocument("mid" -> mesId))
        .sort(BSONDocument("mv" -> -1))
        .cursor[BSONDocument]()
        .headOption.map { x => x match {
        case None => 0
        case Some(doc) => doc.getAs[Int]("mv").getOrElse(0)
      }
    }
  }

  def lastUserMesVersion(userId: String) = {
    import coll.BatchCommands.AggregationFramework, AggregationFramework.{ AddToSet, Group, Match, Project, Push, Unwind, Sort, Ascending, Descending, Limit}
    coll.aggregate(Match(BSONDocument("lid.id" -> userId)), List(
      Unwind("lid"),
      Match(BSONDocument("lid.id" -> userId)),
      Sort(Descending("lid.v")),
      Limit(1)
    )).map(_.documents.headOption.flatMap(_.getAs[BSONDocument]("lid")).flatMap(_.getAs[Int]("v"))).map {
      _ match {
        case None => 0
        case Some(num) => num
      }
    }
  }

  def insert(mv: Int, fromId: LightUser, toId: LightUser, mes: String, time: DateTime) = {
    coll.insert(UserMessage(mv, fromId, toId, mes, time))
  }

  def getInitMes(mesId: String, cv: Int) = {
    val bs = if(cv == 0) BSONDocument("mid" -> mesId) else BSONDocument("mid" -> mesId, "mv" -> BSONDocument("$lt" -> cv))
    coll.find(bs)
      .sort(BSONDocument("mv" -> -1))
      .cursor[UserMessage]()
      .collect[List](30)
  }

  def getMissingMes(listMesIds: Array[String]) = {
    coll.find(BSONDocument("_id" -> BSONDocument("$in" -> listMesIds)))
      .sort(BSONDocument("mv" -> -1))
      .cursor[UserMessage]()
      .collect[List](10)
  }

}


//val bson = BSONFormats.toBSON(o).get.asInstanceOf[BSONDocument]