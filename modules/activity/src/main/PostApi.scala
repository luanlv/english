package lila.activity

import java.util.UUID

import akka.actor.ActorSelection
import lila.activity.actorApi._
import lila.common.LightUser
import org.joda.time.DateTime
import scala.util.Success
import lila.activity.Cached
import lila.db.api._
import lila.db.Implicits._

import lila.hub.actorApi.relation.ReloadOnlineFriends
import lila.hub.actorApi.timeline.{ Propagate, Follow => FollowUser }
//import lila.usrMessage.MessageRepo

final class PostApi(
                 cached: Cached,
                 actor: ActorSelection) {

  def getPost(ids: Set[String], timepoint: DateTime) = PostRepo.getPost(ids, timepoint)

  def newPost(userId: String, content: String,  published: DateTime) = {
    val postId = UUID.randomUUID().toString
    PostRepo.insert(postId, userId, content, published) >>- pushPost(userId, postId)
  }

  def getOnePost(postId: String) = {
    PostRepo.getOnePost(postId)
  }

  def pushPost(userId: String, postId: String) = {
    actor ! NewPost(userId, postId)
  }
}
