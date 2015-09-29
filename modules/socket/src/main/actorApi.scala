package lila.socket
package actorApi

import play.api.libs.json.JsObject
import akka.actor.ActorRef

case class Connected[M <: SocketMember](
  enumerator: JsEnumerator,
  member: M)
case class Sync(uid: String, friends: List[String])
case class Ping(uid: String, v: Int)
case class SetAlive(uid: String)
case class Test(uid: String)
case class Test2(uid: String, to: String, mes: String)
case object Broom
case class Quit(uid: String)

case class SocketEnter[M <: SocketMember](uid: String, member: M)
case class SocketLeave[M <: SocketMember](uid: String, member: M)

case class Resync(uid: String)

case object GetVersion

case class SendToFlag(flag: String, message: JsObject)

case object PopulationGet
case object PopulationTell
case object Test
case class NbMembers(nb: Int, pong: JsObject)

case class StartWatching(uid: String, member: SocketMember, gameIds: Set[String])

case class SendName(uid: String, id: String, name: String)

case class OnelineFriend(uid: String, list: Set[String])