package lila.memo

import lila.db.api.SortOrder
import org.joda.time.DateTime
import play.api.libs.json.{JsObject, Json}
import reactivemongo.api.indexes.{IndexType, Index}
import reactivemongo.bson._
import reactivemongo.bson.Macros
import scala.concurrent.Future
import scala.concurrent.duration._
import spray.caching.{ LruCache, Cache }
import play.modules.reactivemongo.json._
import lila.db.BSON.BSONJodaDateTimeHandler
import lila.db.Types._

object UserMessage {

  private type Handler[T] = BSONHandler[_ <: BSONValue, T]

  final class Builder(coll: Coll) {

    def lastMesVersion(chatId: String) = {
      coll.find(BSONDocument("mid" -> chatId))
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

    def insert(mesId: String, mv: Int, fromId: String, toId: String, mes: String, time: DateTime) = {
      val bs = BSONDocument("_id" -> (mesId + "_" + mv), "mid" -> mesId, "mv" -> mv,  "f" -> fromId, "t" -> toId, "mes" -> mes, "time" -> time)
      coll.insert(bs)
    }

    def getInitMes(mesId: String, cv: Int) = {
      val bs = if(cv == 0) BSONDocument("mid" -> mesId) else BSONDocument("mid" -> mesId, "mv" -> BSONDocument("$lt" -> cv))
      coll.find(bs, BSONDocument("_id" -> 0, "mid" -> 0))
      .sort(BSONDocument("mv" -> -1))
      .cursor[BSONDocument]()
      .collect[List](10).map(_.map(toJSON(_)))
    }

    def getMissingMes(listMesIds: Array[String]) = {
      coll.find(BSONDocument("_id" -> BSONDocument("$in" -> listMesIds)))
      .sort(BSONDocument("mv" -> -1))
      .cursor[BSONDocument]()
      .collect[List](10).map(_.map(toJSON(_)))
    }

  }

  def apply(coll: Coll) = new Builder(coll)
}
