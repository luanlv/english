package lila.memo

import lila.db.api.SortOrder
import org.joda.time.DateTime
import play.api.libs.json.{JsObject, Json}
import reactivemongo.api.indexes.{IndexType, Index}
import reactivemongo.bson._
import reactivemongo.bson.Macros
import reactivemongo.core.commands.Update
import scala.concurrent.Future
import scala.concurrent.duration._
import spray.caching.{ LruCache, Cache }
import play.modules.reactivemongo.json._
import lila.db.BSON.BSONJodaDateTimeHandler
import lila.db.Types._

object NotifyMessage {

  private type Handler[T] = BSONHandler[_ <: BSONValue, T]

  final class Builder(coll: Coll) {

    def getNotifyMessage(userId: String) = {
      import coll.BatchCommands.AggregationFramework, AggregationFramework.{ AddToSet, Group, Match, Project, Push, Unwind, Sort, Ascending, Descending, Limit}
      coll.aggregate(Match(BSONDocument("_id" -> userId)), List(
        Unwind("m"),
        //Match(BSONDocument("lid.id" -> userId)),
        Sort(Descending("l.d")),
        Limit(5),
        Project(BSONDocument("_id" -> 0, "m" -> 1))
      )).map(_.documents.map(toJSON))
    }

    def resetNotify(userId: String) = {
      coll.update(
        BSONDocument("_id" -> userId),
        BSONDocument(
          "$set" -> BSONDocument("n" -> 0),
          "$set" -> BSONDocument("ur" -> BSONArray())
        )
      )
    }

    def markRead(userId: String, toId: String, mv: Int) = {

      coll.find(BSONDocument("_id" -> userId, "m.uid" -> toId), BSONDocument("m.$" -> 1, "ur" -> 1, "n" ->1))
      .cursor[BSONDocument]()
      .collect[List]().map(_.map(toJSON(_))).map {
        list => list match {
          case List() => (0, 0, List())
          case neList => (((neList.head\"m").as[List[JsObject]].head\"n").as[Int], ((neList.head\"m").as[List[JsObject]].head\"lm"\"mv").as[Int], (neList.head\"ur").as[List[String]])
        }
      }.map{
        num => {
          val unread = num._2 - mv
          if(num._1 != 0 && unread == 0){
            println("MARK READ: 1")
            if(num._3.contains(toId)){
              println("MARK READ: 11")
              coll.update(
                BSONDocument("_id" -> userId, "m" -> BSONDocument("$elemMatch" -> BSONDocument("uid" -> toId))),
                BSONDocument(
                  "$set" -> BSONDocument("m.$.n" -> unread),
                  "$inc" -> BSONDocument("n" -> -1),
                  "$pull" -> BSONDocument("ur" -> toId)
                )
              )
              true
            } else {
              println("MARK READ: 12")
              coll.update(
                BSONDocument("_id" -> userId, "m" -> BSONDocument("$elemMatch" -> BSONDocument("uid" -> toId))),
                BSONDocument(
                  "$set" -> BSONDocument("m.$.n" -> unread)
                )
              )
              false
            }
          } else {
            println("MARK READ: 2")
            false
          }
        }
      }



    }

    def getNotify(uid: String) = {
      coll.find(BSONDocument("_id" -> uid))
      .sort(BSONDocument("mv" -> -1))
      .cursor[BSONDocument]()
      .collect[List]().map(_.map(toJSON(_))).map {
        list => list match {
          case List() => 0
          case neList => (neList.head\"n").as[Int]
        }
      }
    }

    def notifyMessage(uid: String, chatId: String, mesId: String, mv: Int, mes: String, time: DateTime) = {

      val bs = BSONDocument("_id" -> (mesId + "_" + mv), "mid" -> mesId, "mv" -> mv,  "f" -> chatId, "t" -> uid, "mes" -> mes, "time" -> time)

      val data = coll.find(BSONDocument("_id" -> uid, "m.uid" -> chatId), BSONDocument("m.$" -> 1, "n" -> 1, "ur" -> 1))
      .cursor[BSONDocument]()
      .collect[List]().map(_.map(toJSON(_))).map {
        list => list match {
          case List() => (-1, List())
          case neList => println("LIST:" + neList.head);(((neList.head\"m").as[List[JsObject]].head\"n").as[Int], (neList.head\"ur").as[List[String]])
        }
      }

      val update = data.map{num =>
        if(num._1 == -1){
          println("CASE: 1")
          coll.update(
            BSONDocument("_id" -> uid),
            BSONDocument(
              "$push" -> BSONDocument("m" -> BSONDocument("uid" -> chatId, "n" -> 1, "d" -> time, "lm" -> bs)),
              "$inc" -> BSONDocument("n" -> 1),
              "$push" -> BSONDocument("ur" -> chatId)
            ),
            upsert = true
          )
          true
        } else if(num._1 == 0) {
          println("CASE: 0")
          coll.update(
            BSONDocument("_id" -> uid, "m" -> BSONDocument("$elemMatch" -> BSONDocument("uid" -> chatId))),
            BSONDocument(
              "$inc" -> BSONDocument("m.$.n" -> 1),
              "$set" -> BSONDocument("m.$.d" -> time),
              "$inc" -> BSONDocument("n" -> 1),
              "$set" -> BSONDocument("m.$.lm" -> bs),
              "$push" -> BSONDocument("ur" -> chatId)
            )
          )
          true
        } else if(num._1 > 0 && num._2.contains(chatId)) {
          println("CASE: contain " + num._1)
          coll.update(
            BSONDocument("_id" -> uid, "m" -> BSONDocument("$elemMatch" -> BSONDocument("uid" -> chatId))),
            BSONDocument(
              "$set" -> BSONDocument("m.$.d" -> time),
              "$set" -> BSONDocument("m.$.lm" -> bs)
            )
          )
          false
        } else {
          println("CASE: else " )
          coll.update(
            BSONDocument("_id" -> uid, "m" -> BSONDocument("$elemMatch" -> BSONDocument("uid" -> chatId))),
            BSONDocument(
              "$inc" -> BSONDocument("m.$.n" -> 1),
              "$set" -> BSONDocument("m.$.d" -> time),
              "$inc" -> BSONDocument("n" -> 1),
              "$set" -> BSONDocument("m.$.lm" -> bs),
              "$push" -> BSONDocument("ur" -> chatId)
            )
          )
          true
        }
      }
      update.await
    }
  }

  def apply(coll: Coll) = new Builder(coll)
}
