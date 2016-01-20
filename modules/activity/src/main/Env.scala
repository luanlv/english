package lila.activity

import akka.actor._
import com.typesafe.config.Config

import lila.common.PimpedConfig._
import lila.memo.{ExpireSetMemo, MongoCache}
import scala.concurrent.duration._

final class Env(
                 config: Config,
                 db: lila.db.Env,
                 hub: lila.hub.Env,
                 lightUser: String => Option[lila.common.LightUser],
                 mongoCache: MongoCache.Builder,
                 scheduler: lila.common.Scheduler,
                 system: ActorSystem) {

  private val settings = new {
    val collectionPostColl = config getString "collection.post"
//    val collectionNotify = config getString "collection.notify"
    val PaginatorMaxPerPage = 10 //config getInt "paginator.max_per_page"
    val CachedNbTtl = 10 second  //config duration "cached.nb.ttl"
    val OnlineTtl = 10 second //config duration "online.ttl"
    val ActorName = config getString "actor.name"
  }
  import settings._

  lazy val cached = new Cached(
    nbTtl = CachedNbTtl,
    mongoCache = mongoCache)

  lazy val postApi = new PostApi(
    cached = cached,
    actor = hub.actor.activity)

  private[activity] val actor = system.actorOf(Props(new ActivityActor(
    postApi = postApi
  )), name = ActorName)

  private[activity] lazy val postColl = db(collectionPostColl)



}

object Env {
  lazy val current: Env = "[boot] activity" describes new Env(
    config = lila.common.PlayApp loadConfig "activity",
    db = lila.db.Env.current,
    hub = lila.hub.Env.current,
    lightUser = lila.user.Env.current.lightUser,
    mongoCache = lila.memo.Env.current.mongoCache,

    scheduler = lila.common.PlayApp.scheduler,
    system = lila.common.PlayApp.system)
}
