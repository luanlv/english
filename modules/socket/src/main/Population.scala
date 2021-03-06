package lila.socket

import akka.actor._

import lila.socket.actorApi._
import play.api.libs.json.Json

private[socket] final class Population extends Actor {

  var nb = 0
  val bus = context.system.lilaBus

  bus.subscribe(self, 'socketDoor)

  override def postStop() {
    bus.unsubscribe(self)
  }

  def receive = {

    case _: SocketEnter[_] => nb = nb + 1
    case _: SocketLeave[_] => nb = nb - 1

    case PopulationTell =>
      bus.publish(NbMembers(nb, Socket.makeMessage("n", nb)), 'nbMembers)
  }
}
