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


object PostRepo {

  private lazy val coll = Env.current.postColl

  def insert(id: String, userId: String, content: String, published: DateTime): Future[WriteResult] = {
    val post = Post(id: String, content, lila.user.Env.current.lightUserApi.get(userId).get, published)
    coll.insert(post)
  }

  def getOnePost(postId: String) = {
    coll.find(BSONDocument("_id" -> postId))
    .cursor[Post]()
    .headOption
  }

  def getPost(ids: Set[ID], timepoint: DateTime): Fu[List[Post]] = {
    coll.find(BSONDocument("userId" -> BSONDocument("$in" -> ids)))
      .sort(BSONDocument("published" -> -1))
      .cursor[Post]()
      .collect[List](10)
  }

}


//val bson = BSONFormats.toBSON(o).get.asInstanceOf[BSONDocument]