package lila.activity

import java.util.UUID

import akka.actor.{ Actor, ActorSelection }
import akka.pattern.{ ask, pipe }
import lila.activity.actorApi._
import lila.hub.actorApi.activity._
import lila.hub.actorApi.userMessage.PingVersion
import org.joda.time.DateTime
import play.api.libs.json.Json

//import actorApi._
import lila.common.LightUser
import lila.hub.actorApi.relation._
import lila.hub.actorApi.{ SendTo, SendTos }
import makeTimeout.short

private[activity] final class ActivityActor(
                                             postApi: PostApi,
                                             commentApi: CommentApi,
                                             childCommentApi: ChildCommentApi
                                           ) extends Actor {

  private def relationApi = lila.relation.Env.current.api
  private def friendshipApi = lila.relation.Env.current.friendshipApi

  private val bus = context.system.lilaBus

  private var onlines = Map[ID, LightUser]()

  def receive = {

    case CommentPost(userId, postId, comment) => sender ! Json.toJson(commentPost(userId, postId, comment).await)

    case ChildCommentPost(userId, postId, parentId, comment) => sender ! Json.toJson(childCommentPost(userId, postId, parentId, comment))

    case InitPost(userId) => {
      val listFriend = friendshipApi.friends(userId).await
      val listFollowing = relationApi.following(userId).await
      val listUser = (listFriend ++ listFollowing).+(userId).+("admin")
      sender ! Json.toJson(postApi.getPost(userId, listUser, DateTime.now()).await)
    }

    case NewPost(userId, postId) => {
      val listFriend = friendshipApi.friends(userId).await
      val listFollower = relationApi.followers(userId).await
      val listUser = (listFriend ++ listFollower).+(userId)
      postApi.getOnePost(userId, postId).map{
        post => bus.publish(SendTos(listUser, "newPost", Json.toJson(post)), 'users)
      }
    }

  }

  def commentPost(userId: String, postId: String, comment: String) = {
    commentApi.newComment(userId, postId, comment)
  }

  def childCommentPost(userId: String, postId: String, parentId: String, comment: String) = {
    val opComment = childCommentApi.newChildComment(userId, postId, parentId, comment).await
    opComment foreach {
        comment =>  commentApi.addChild(parentId, comment)
    }
    opComment
  }
}
