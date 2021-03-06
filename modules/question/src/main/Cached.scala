package lila.question

import scala.concurrent.Future
import scala.concurrent.duration._

import org.joda.time.DateTime
import play.api.libs.json.JsObject
import reactivemongo.bson._

import spray.caching.{ LruCache, Cache }

import lila.common.LightUser
import lila.db.api.{ $count, $primitive }
import lila.db.BSON._
import lila.db.Implicits._
import lila.memo.{ ExpireSetMemo, MongoCache }

final class Cached(
                    nbTtl: FiniteDuration,
                    mongoCache: MongoCache.Builder) {

  private def oneWeekAgo = DateTime.now minusWeeks 1

  private val cache: Cache[Int] = LruCache(timeToLive = 1 hour)

  def clearCache(pre: String, uid: String) = cache.remove(pre + uid)

}