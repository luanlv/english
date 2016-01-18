package lila.relation

import akka.actor._
import akka.pattern.pipe
import com.typesafe.config.Config

import lila.common.PimpedConfig._

final class Env(
    config: Config,
    db: lila.db.Env,
    hub: lila.hub.Env,
    getOnlineUserIds: () => Set[String],
    lightUser: String => Option[lila.common.LightUser],
    followable: String => Fu[Boolean],
    system: ActorSystem,
    scheduler: lila.common.Scheduler) {

  private val settings = new {
    val CollectionRelation = config getString "collection.relation"
    val CollectionMakeFriend = config getString "collection.makeFriend"
    val CollectionFriendship = config getString "collection.friendship"
    val ActorNotifyFreq = config duration "actor.notify_freq"
    val ActorName = config getString "actor.name"
    val MaxFollow = config getInt "limit.follow"
    val MaxBlock = config getInt "limit.block"
  }
  import settings._

  lazy val makeFriendApi = new MakeFriendApi(
    cached = cached,
    actor = hub.actor.relation,
    bus = system.lilaBus,
    getOnlineUserIds = getOnlineUserIds,
    timeline = hub.actor.timeline,
    reporter = hub.actor.report,
    followable = followable,
    maxFollow = MaxFollow,
    maxBlock = MaxBlock)

  lazy val friendshipApi = new FriendshipApi(
    cached = cached,
    actor = hub.actor.relation,
    bus = system.lilaBus,
    getOnlineUserIds = getOnlineUserIds,
    timeline = hub.actor.timeline,
    reporter = hub.actor.report,
    followable = followable,
    maxFollow = MaxFollow,
    maxBlock = MaxBlock)

  lazy val api = new RelationApi(
    cached = cached,
    actor = hub.actor.relation,
    bus = system.lilaBus,
    getOnlineUserIds = getOnlineUserIds,
    timeline = hub.actor.timeline,
    reporter = hub.actor.report,
    followable = followable,
    maxFollow = MaxFollow,
    maxBlock = MaxBlock)

  private lazy val cached = new Cached

  private[relation] val actor = system.actorOf(Props(new RelationActor(
    getOnlineUserIds = getOnlineUserIds,
    lightUser = lightUser,
    api = api,
    makeFriendApi = makeFriendApi,
    friendshipApi = friendshipApi
  )), name = ActorName)

//  {
//    import scala.concurrent.duration._
//
//    scheduler.once(10 seconds) {
//      scheduler.message(ActorNotifyFreq) {
//        actor -> actorApi.NotifyMovement
//      }
//    }
//  }

  private[relation] lazy val relationColl = db(CollectionRelation)
  private[relation] lazy val makeFriendColl = db(CollectionMakeFriend)
  private[relation] lazy val friendshipColl = db(CollectionFriendship)
}

object Env {

  lazy val current: Env = "relation" describes new Env(
    config = lila.common.PlayApp loadConfig "relation",
    db = lila.db.Env.current,
    hub = lila.hub.Env.current,
    getOnlineUserIds = () => lila.user.Env.current.onlineUserIdMemo.keySet,
    lightUser = lila.user.Env.current.lightUser,
    followable = lila.pref.Env.current.api.followable _,
    system = lila.common.PlayApp.system,
    scheduler = lila.common.PlayApp.scheduler)
}
