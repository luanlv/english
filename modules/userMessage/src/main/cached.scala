package lila.userMessage

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

  def chatVersion(chatId: String): Fu[Int] = cache("chatVer:" + chatId) {
    val v = Env.current.messageRepo.lastMesVersion(chatId)
    println("chatVer:" + chatId + ": " + v.await)
    v
  }
  def userChatVersion(userId: String): Fu[Int] = cache("userChatVer:" + userId) {
    val v = Env.current.messageRepo.lastUserMesVersion(userId)
    println("userChatVer:" + userId + ": " + v.await)
    v
  }

  def setNewVersion(id: String, v: Int) = {
    cache.remove(id)
    cache.apply(id)(Future(v))
  }

  def clearCache = fuccess(cache.clear)

}