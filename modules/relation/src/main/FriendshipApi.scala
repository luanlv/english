package lila.relation

import akka.actor.ActorSelection
import actorApi._
import scala.util.Success

import lila.db.api._
import lila.db.Implicits._
//import lila.game.GameRepo
import lila.hub.actorApi.relation.ReloadOnlineFriends
import lila.hub.actorApi.timeline.{ Propagate, Follow => FollowUser }
import lila.user.tube.userTube
import lila.user.{ User => UserModel, UserRepo }
import tube.friendshipTube

final class FriendshipApi(
                           cached: Cached,
                           actor: ActorSelection,
                           bus: lila.common.Bus,
                           getOnlineUserIds: () => Set[String],
                           timeline: ActorSelection,
                           reporter: ActorSelection,
                           followable: String => Fu[Boolean],
                           maxFollow: Int,
                           maxBlock: Int) {

  def friends(userId: ID) = cached friends userId


  //
  //  def blockers(userId: ID) = cached blockers userId
  //  def blocking(userId: ID) = cached blocking userId

  //  def blocks(userId: ID) = blockers(userId) ⊹ blocking(userId)

  def nbFriends(userId: ID) = friends(userId) map (_.size)

  //  def nbBlocking(userId: ID) = blocking(userId) map (_.size)
  //  def nbBlockers(userId: ID) = blockers(userId) map (_.size)

  //  def friends(userId: ID) = following(userId) zip followers(userId) map {
  //    case (f1, f2) => f1 intersect f2
  //  }

  //  def areFriends(u1: ID, u2: ID) = friends(u1) map (_ contains u2)
  //  def blocks(u1: ID, u2: ID) = blocking(u1) map (_ contains u2)

  def friendship(u1: ID, u2: ID): Fu[Option[Relation]] = cached.friendship(u1, u2)

  //  def onlinePopularUsers(max: Int): Fu[List[UserModel]] =
  //    (getOnlineUserIds().toList map { id =>
  //      nbFollowers(id) map (id -> _)
  //    }).sequenceFu map (_ sortBy (-_._2) take max map (_._1)) flatMap UserRepo.byOrderedIds

  def doFriend(u1: ID, u2: ID): Funit =
    if (u1 == u2) funit
    else followable(u2) zip friendship(u1, u2) flatMap {
      case (false, _)      => funit
      case (_, Some(Friend)) => funit
      case _ =>
        FriendshipRepo.doFriend(u1, u2) >>
        MakeFriendRepo.unrequest(u2, u1) >>
        refresh(u1, u2)
    }



  def unfriend(u1: ID, u2: ID): Funit =
    if (u1 == u2) funit
    else friendship(u1, u2) flatMap {
      case Some(Friend) => FriendshipRepo.unfriend(u1, u2) >> refresh(u1, u2)
      case _            => funit
    }

  //  def unfollowAll(u1: ID): Funit = RelationRepo.unfollowAll(u1)

  private def refresh(u1: ID, u2: ID): Funit =
    cached.invalidateFriendship(u1, u2) >>-
      List(u1, u2).foreach(actor ! ReloadOnlineFriends(_))
}
