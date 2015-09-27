package lila.socket

import play.api.libs.json._

object Socket extends Socket

private[socket] trait Socket {

  def makeMessage[A](t: String, data: A)(implicit writes: Writes[A]): JsObject =
    JsObject(List("t" -> JsString(t), "d" -> writes.writes(data)))

  def makeMessage(t: String): JsObject = JsObject(List("t" -> JsString(t)))

  def makePong(n: Int) = makeMessage("n", n)

  val initialPong = makePong(0)

}
