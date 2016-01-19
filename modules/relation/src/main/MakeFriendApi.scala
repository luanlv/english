package lila.relation

import akka.actor.ActorSelection
import lila.relation.actorApi.ReloadNotify
import scala.util.Success

import lila.db.api._
import lila.db.Implicits._
//import lila.game.GameRepo
import lila.hub.actorApi.relation.ReloadOnlineFriends
import lila.hub.actorApi.timeline.{ Propagate, Follow => FollowUser }
import lila.user.tube.userTube
import lila.user.{ User => UserModel, UserRepo }
import tube.makeFriendTube

final class MakeFriendApi(
                         cached: Cached,
                         actor: ActorSelection,
                         bus: lila.common.Bus,
                         getOnlineUserIds: () => Set[String],
                         timeline: ActorSelection,
                         reporter: ActorSelection,
                         followable: String => Fu[Boolean],
                         maxFollow: Int,
                         maxBlock: Int) {

  def requester(userId: ID) = cached requester userId
  def requesting(userId: ID) = cached requesting userId
//
//  def blockers(userId: ID) = cached blockers userId
//  def blocking(userId: ID) = cached blocking userId

//  def blocks(userId: ID) = blockers(userId) ⊹ blocking(userId)

  def nbRequester(userId: ID) = requester(userId) map (_.size)
  def nbRequesting(userId: ID) = requesting(userId) map (_.size)
//  def nbBlocking(userId: ID) = blocking(userId) map (_.size)
//  def nbBlockers(userId: ID) = blockers(userId) map (_.size)

//  def friends(userId: ID) = following(userId) zip followers(userId) map {
//    case (f1, f2) => f1 intersect f2
//  }

//  def areFriends(u1: ID, u2: ID) = friends(u1) map (_ contains u2)

  def requests(u1: ID, u2: ID) = requesting(u1) map (_ contains u2)
//  def blocks(u1: ID, u2: ID) = blocking(u1) map (_ contains u2)

  def makeFriend(u1: ID, u2: ID): Fu[Option[Relation]] = cached.makeFriend(u1, u2)

//  def onlinePopularUsers(max: Int): Fu[List[UserModel]] =
//    (getOnlineUserIds().toList map { id =>
//      nbFollowers(id) map (id -> _)
//    }).sequenceFu map (_ sortBy (-_._2) take max map (_._1)) flatMap UserRepo.byOrderedIds

  def request(u1: ID, u2: ID): Funit =
    if (u1 == u2) funit
    else followable(u2) zip makeFriend(u1, u2) zip makeFriend(u2, u1) flatMap {
      case ((false, _), _)        => funit
      case ((_, Some(Request)), _) => funit
      case ((_, _), Some(Block))  => funit
      case _ =>
        makeFriend(u2, u1) flatMap {
          case Some(Request) =>
            Env.current.friendshipApi.doFriend(u1, u2) >> reloadNotify(u1)

          case _ =>
            MakeFriendRepo.request(u1, u2) >>
            refresh(u1, u2) >> reloadNotify(u2)
        }
    }


  def unrequest(u1: ID, u2: ID): Funit =
    if (u1 == u2) funit
    else makeFriend(u1, u2) flatMap {
      case Some(Request) => {
        MakeFriendRepo.unrequest(u1, u2) >> refresh(u1, u2) >> reloadNotify(u2)
      }
      case _            => funit
    }

//  def unfollowAll(u1: ID): Funit = RelationRepo.unfollowAll(u1)

  private def refresh(u1: ID, u2: ID): Funit =
    cached.invalidateMakeFriend(u1, u2) >>-
      List(u1, u2).foreach(actor ! ReloadOnlineFriends(_))

  private def reloadNotify(userId: ID):Funit =
    fuccess(actor ! ReloadNotify(userId))
}
