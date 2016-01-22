package lila.activity

import play.modules.reactivemongo.json._
import reactivemongo.api.commands.WriteResult

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


object CommentRepo {

  private lazy val coll = Env.current.commentColl

  def insert(id: String, postId: String, userId: String,  comment: String, time: DateTime): Future[WriteResult] = {
    //    val info = Info(0, List(), 0, List(), 0)
    val newComment = Comment(id, postId, comment, lila.user.Env.current.lightUserApi.get(userId).get, time, 0, Option(List()), 0 , List())
    coll.insert(newComment)
  }

  def getOneComment(userId: String, commentId: String):Fu[Option[Comment]] = {
    coll.find(BSONDocument("_id" -> commentId),
      BSONDocument(
        "_id" -> 1,
        "parentPost" -> 1,
        "comment" -> 1,
        "userId" -> 1,
        "time" -> 1,
        "likeCount" -> 1,
        "likes" -> BSONDocument("$elemMatch" -> BSONDocument("$eq" -> userId)),
        "childCount" -> 1,
        "children" -> 1
      )
    )
      .cursor[Comment]()
      .headOption
  }

  def addChild(parentId: String, comment: ChildComment) = {
//    val childComment = BSONFormats.toBSON(Json.toJson(comment)).get.asInstanceOf[BSONDocument]
    coll.update(
      BSONDocument("_id" -> parentId),
      BSONDocument(
        "$push" -> BSONDocument("children" -> BSONDocument(
          "$each" -> BSONArray(comment),
          "$slice" -> -2
          )
        )
      )
    )
  }

  def getComment(userId: String, postId: String, timepoint: DateTime, nb: Int): Fu[List[Comment]] = {
    println(userId)
    println(postId)
    coll.find(BSONDocument("parentPost" -> postId),
      BSONDocument(
        "_id" -> 1,
        "parentPost" -> 1,
        "comment" -> 1,
        "userId" -> 1,
        "time" -> 1,
        "likeCount" -> 1,
        "likes" -> BSONDocument("$elemMatch" -> BSONDocument("$eq" -> userId)),
        "childCount" -> 1,
        "children" -> 1
      )
    )
      .sort(BSONDocument("time" -> -1))
      .cursor[Comment]()
      .collect[List](nb)
  }

  def like(userId: String, postId: String) = {
    coll.update(
      BSONDocument("_id" -> postId, "likes" -> BSONDocument("$ne" -> userId)),
      BSONDocument(
        "$inc" -> BSONDocument("likeCount" -> 1),
        "$push" -> BSONDocument("likes" -> userId)
      )
    ).void
  }

  def unlike(userId: String, postId: String) = {
    coll.update(
      BSONDocument("_id" -> postId, "likes" -> userId),
      BSONDocument(
        "$inc" -> BSONDocument("likeCount" -> -1),
        "$pull" -> BSONDocument("likes" -> userId)
      )
    ).void
  }

}


//val bson = BSONFormats.toBSON(o).get.asInstanceOf[BSONDocument]