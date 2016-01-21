package lila.activity

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

private[activity] final class ActivityActor( postApi: PostApi) extends Actor {

  private def relationApi = lila.relation.Env.current.api
  private def friendshipApi = lila.relation.Env.current.friendshipApi

  private val bus = context.system.lilaBus

  private var onlines = Map[ID, LightUser]()

  def receive = {

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


}
