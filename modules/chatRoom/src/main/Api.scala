package lila.chatRoom

import akka.actor.ActorSelection
import lila.common.LightUser
import org.joda.time.DateTime
import scala.util.Success

import lila.db.api._
import lila.db.Implicits._

import lila.hub.actorApi.relation.ReloadOnlineFriends
import lila.hub.actorApi.timeline.{ Propagate, Follow => FollowUser }
//import lila.usrMessage.MessageRepo

final class Api(
                   cached: Cached,
                   actor: ActorSelection,
                   bus: lila.common.Bus) {


}
