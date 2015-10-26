package lila.chatRoom

import play.modules.reactivemongo.json._

import scala.concurrent.Future
import scala.concurrent.duration._

import org.joda.time.DateTime
import play.api.libs.json._
import reactivemongo.bson._

import spray.caching.{ LruCache, Cache }

import lila.common.LightUser
import lila.db.api.{ $count, $primitive }
//import BSONHandlers._
import lila.db.BSON._
import lila.db.Implicits._
import scala.concurrent.ExecutionContext.Implicits.global


object RoomMessageRepo {

  private lazy val coll = Env.current.roomMessageColl


}
