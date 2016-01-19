package lila.relation

import akka.actor.{ Actor, ActorSelection }
import akka.pattern.{ ask, pipe }
import lila.hub.actorApi.userMessage.PingVersion
import play.api.libs.json.Json

import actorApi._
import lila.common.LightUser
import lila.hub.actorApi.relation._
import lila.hub.actorApi.{ SendTo, SendTos }
import makeTimeout.short

private[relation] final class RelationActor(
    getOnlineUserIds: () => Set[String],
    lightUser: String => Option[LightUser],
    api: RelationApi,
    makeFriendApi: MakeFriendApi,
    friendshipApi: FriendshipApi) extends Actor {

  private val bus = context.system.lilaBus

  private var onlines = Map[ID, LightUser]()

  def receive = {

    case PingVersion(userId) => {
      sender ! makeFriendApi.nbRequester(userId).await
    }

    case GetFriendRequest(userId) => {
      sender ! makeFriendApi.requester(userId).map(setId => setId.map(id => lightUser(id).get))
    }

    case GetOnlineUser(userId) => sender ! onlineFriends(userId).await

    case ReloadNotify(userId) => reloadNotify(userId)

    // triggers following reloading for this user id
    case ReloadOnlineFriends(userId) => onlineFriends(userId) foreach {
      case users:List[LightUser] =>
        bus.publish(SendTo(userId, "following_onlines", users.map(_.titleName)), 'users)
    }

    case NotifyMovement =>
      val prevIds = onlineIds
      val curIds = getOnlineUserIds()
      val leaveIds = (prevIds diff curIds).toList
      val enterIds = (curIds diff prevIds).toList
      val leaves = leaveIds.flatMap(i => lightUser(i))
      val enters = enterIds.flatMap(i => lightUser(i))
      onlines = onlines -- leaveIds ++ enters.map(e => e.id -> e)
      notifyFollowers(enters, "following_enters")
      notifyFollowers(leaves, "following_leaves")
  }

  private def onlineIds: Set[ID] = onlines.keySet

  private def onlineFriends(userId: String): Fu[List[LightUser]] =
    friendshipApi friends  userId map { ids =>
      println(ids)
      println(onlines)
      ids.flatMap(onlines.get).toList
    }

  private def notifyFollowers(users: List[LightUser], message: String) {
    users foreach { user =>
      friendshipApi friends  user.id map (_ filter onlines.contains) foreach { ids =>
        if (ids.nonEmpty) bus.publish(SendTos(ids.toSet, message, user), 'users)
      }
    }
  }

  private def reloadNotify(userId: String) = {
      makeFriendApi nbRequester  userId map {
        nbRequester => bus.publish(SendTo(userId, "nbRequester", nbRequester), 'users)
      }
  }

}
