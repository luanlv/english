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

import lila.db.BSON.BSONJodaDateTimeHandler
import lila.db.Types._

object UserMessage {

  private type Handler[T] = BSONHandler[_ <: BSONValue, T]

  final class Builder(coll: Coll) {

    def lastMesVersion(chatId: String) = {
      coll.find(BSONDocument("mid" -> chatId))
        .sort(BSONDocument("v" -> -1))
        .cursor[BSONDocument]()
          .headOption.map { x => x match {
        case None => 0
        case Some(doc) => doc.getAs[Int]("v").getOrElse(0)
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

    def insert(mesId: String, v: Int, fromId: String, vSender: Int, toId: String, vReceive: Int, mes: String, time: DateTime) = {
      val bs = BSONDocument("mid" -> mesId, "v" -> v, "lid" -> BSONArray(BSONDocument("id" -> fromId, "v" -> vSender), BSONDocument("id" -> toId, "v" -> vReceive)), "f" -> fromId, "t" -> toId, "mes" -> mes, "time" -> time)
      coll.insert(bs)
    }

    def getInitMes(mesId: String) = {
      coll.find(BSONDocument("mid" -> mesId))
      .sort(BSONDocument("v" -> -1))
      .cursor[BSONDocument]()
      .collect[List](10)
    }

    def getMissingMes(userId: String, f: Int, t: Int) = {
      import coll.BatchCommands.AggregationFramework, AggregationFramework.{ AddToSet, Group, Match, Project, Push, Unwind, Sort, Ascending, Descending, Limit}
      coll.aggregate(Match(BSONDocument("lid.id" -> userId)), List(
        Unwind("lid"),
        Match(BSONDocument("lid.id" -> userId)),
        Sort(Descending("lid.v")),
        Match(BSONDocument("$and" -> BSONArray(BSONDocument("lid.v" -> BSONDocument("$gte" -> f)), BSONDocument("lid.v" -> BSONDocument("$lte" -> t))))),
        Project(BSONDocument("v" -> 1, "mes" -> 1, "time" -> 1, "f" -> 1, "t" -> 1, "mv" -> "$lid.v"))
      )).map(_.documents.map(_.as[BSONDocument]))
    }
  }

  def apply(coll: Coll) = new Builder(coll)
}
