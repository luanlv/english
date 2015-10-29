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
import lila.db.BSON._
import lila.db.Implicits._
import scala.concurrent.ExecutionContext.Implicits.global


object NotifyRepo {
  private lazy val coll = Env.current.notifyColl

  def notifyMessage(uid: String, chatId: String, mesId: String, mv: Int, mes: String, time: DateTime) = {
    val bs = BSONDocument("_id" -> (mesId + "_" + mv), "mid" -> mesId, "mv" -> mv,  "f" -> chatId, "t" -> uid, "mes" -> mes, "time" -> time)

    if(mv == 1){
      coll.update(
        BSONDocument("_id" -> uid),
        BSONDocument(
          "$push" -> BSONDocument("m" -> BSONDocument("uid" -> chatId, "n" -> 1, "d" -> time, "lm" -> bs)),
          "$inc" -> BSONDocument("n" -> 1),
          "$push" -> BSONDocument("ur" -> chatId)
        ),
        upsert = true
      )
      coll.update(
        BSONDocument("_id" -> chatId),
        BSONDocument(
          "$push" -> BSONDocument("m" -> BSONDocument("uid" -> uid, "n" -> 0, "d" -> time, "lm" -> bs)),
          "$inc" -> BSONDocument("n" -> 0)
        ),
        upsert = true
      )
      true
    } else {
      val data = coll.find(BSONDocument("_id" -> uid, "m.uid" -> chatId), BSONDocument("m.$" -> 1, "n" -> 1, "ur" -> 1))
        .cursor[BSONDocument]()
        .collect[List]().map(_.map(toJSON(_))).map {
        list => list match {
          case List() => (-1, List())
          case neList => (((neList.head\"m").as[List[JsObject]].head\"n").as[Int], (neList.head\"ur").as[List[String]])
        }
      }

      coll.update(
        BSONDocument("_id" -> chatId, "m" -> BSONDocument("$elemMatch" -> BSONDocument("uid" -> uid))),
        BSONDocument(
          "$set" -> BSONDocument("m.$.d" -> time),
          "$set" -> BSONDocument("m.$.lm" -> bs)
          //"$push" -> BSONDocument("ur" -> chatId)
        )
      )

      val update = data.map{num =>
        if(num._1 == 0) {
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
          coll.update(
            BSONDocument("_id" -> uid, "m" -> BSONDocument("$elemMatch" -> BSONDocument("uid" -> chatId))),
            BSONDocument(
              "$inc" -> BSONDocument("m.$.n" -> 1),
              "$set" -> BSONDocument("m.$.d" -> time),
              "$set" -> BSONDocument("m.$.lm" -> bs)
            )
          )
          false
        } else {
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

  def getNotifyMessage(userId: String) = {
    import coll.BatchCommands.AggregationFramework, AggregationFramework.{ AddToSet, Group, Match, Project, Push, Unwind, Sort, Ascending, Descending, Limit}
    coll.aggregate(Match(BSONDocument("_id" -> userId)), List(
      Unwind("m"),
      //Match(BSONDocument("lid.id" -> userId)),
      Sort(Descending("l.d")),
      Limit(5),
      Project(BSONDocument("_id" -> 0, "m" -> 1))
    )).map(_.documents.map(x => x.getAs[BSONDocument]("m").head).map(_.as[NotifyMessage]))
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
          if(num._3.contains(toId)){
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
            coll.update(
              BSONDocument("_id" -> userId, "m" -> BSONDocument("$elemMatch" -> BSONDocument("uid" -> toId))),
              BSONDocument(
                "$set" -> BSONDocument("m.$.n" -> unread)
              )
            )
            false
          }
        } else {
          false
        }
      }
    }
  }
}


//val bson = BSONFormats.toBSON(o).get.asInstanceOf[BSONDocument]