package lila.relation

import lila.memo.AsyncCache

private[relation] final class Cached {

  private[relation] val followers = AsyncCache(RelationRepo.followers, maxCapacity = 2000)
  private[relation] val following = AsyncCache(RelationRepo.following, maxCapacity = 2000)
  private[relation] val relation = AsyncCache(findRelation, maxCapacity = 20000)

  private[relation] val requester = AsyncCache(MakeFriendRepo.requester, maxCapacity = 2000)
  private[relation] val requesting = AsyncCache(MakeFriendRepo.requesting, maxCapacity = 2000)
  private[relation] val makeFriend = AsyncCache(findMakeFriend, maxCapacity = 20000)
  private[relation] val blockers = AsyncCache(RelationRepo.blockers, maxCapacity = 2000)

  private[relation] val friends = AsyncCache(FriendshipRepo.friends, maxCapacity = 2000)
  private[relation] val friendship = AsyncCache(findFriendship, maxCapacity = 20000)

  private[relation] val blocking = AsyncCache(RelationRepo.blocking, maxCapacity = 2000)


  private def findRelation(pair: (String, String)): Fu[Option[Relation]] = pair match {
    case (u1, u2) => following(u1) flatMap { f =>
      f(u2).fold(fuccess(true.some), blocking(u1) map { b =>
        b(u2).fold(false.some, none)
      })
    }
  }

  private def findMakeFriend(pair: (String, String)): Fu[Option[Relation]] = pair match {
    case (u1, u2) => requesting(u1) flatMap { f =>
      f(u2).fold(fuccess(true.some), blocking(u1) map { b =>
        b(u2).fold(false.some, none)
      })
    }
  }

  private def findFriendship(pair: (String, String)): Fu[Option[Relation]] = pair match {
    case (u1, u2) => friends(u1) flatMap { f =>
      f(u2).fold(fuccess(true.some), blocking(u1) map { b =>
        b(u2).fold(false.some, none)
      })
    }
  }

  private[relation] def invalidate(u1: ID, u2: ID): Funit =
    (List(followers, following, blockers, blocking) flatMap { cache =>
      List(u1, u2) map cache.remove
    }).sequenceFu.void >> relation.remove(u1, u2)

  private[relation] def invalidateMakeFriend(u1: ID, u2: ID): Funit =
    (List(requester, requesting) flatMap { cache =>
      List(u1, u2) map cache.remove
    }).sequenceFu.void >> makeFriend.remove(u1, u2)

  private[relation] def invalidateFriendship(u1: ID, u2: ID): Funit = {
    (List(friends, requester, requesting) flatMap { cache =>
      List(u1, u2) map cache.remove
    }).sequenceFu.void >> {
      friendship.remove(u1, u2)
      friendship.remove(u2, u1)
      makeFriend.remove(u2, u1)
      makeFriend.remove(u1, u2)
    }
  }
}
