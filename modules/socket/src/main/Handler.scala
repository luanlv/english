package lila.socket

import akka.actor.ActorRef
import akka.pattern.{ ask, pipe }
import lila.hub.actorApi.userMessage._
import play.api.libs.iteratee.{ Iteratee, Enumerator }
import play.api.libs.json._

import actorApi._
import lila.common.PimpedJson._
import lila.hub.actorApi.relation.ReloadOnlineFriends
import makeTimeout.large

object Handler {

  type Controller = PartialFunction[(String, JsObject), Unit]
  type Connecter = PartialFunction[Any, (Controller, JsEnumerator, SocketMember)]

  def apply(
    hub: lila.hub.Env,
    socket: ActorRef,
    uid: String,
    join: Any,
    userId: Option[String])(connecter: Connecter): Fu[JsSocketHandler] = {

    def baseController(member: SocketMember): Controller = {
      case ("p", _) => {
        userId match {
          case None => socket ! Ping(uid)
          case Some(user) => {
            socket ! SetAlive(uid)
            hub.actor.userMessage ! PingVersion(user)
          }
        }
      }

      case ("get_onlines", _) => userId foreach { u =>
        hub.actor.userMessage ! GetOnlineUser(u)
      }

      case ("gn", o) => userId foreach { u =>
        val id = (o\"d").as[String]
        (hub.actor.userMessage ? GetName(id)) foreach {
          case "error" => println("errror:" +id)
          case name:String  => socket ! SendName(uid, id, name)
        }
      }

      case ("init_chat", o) => userId foreach { fromId =>
        if(fromId.length() > 0) {
          hub.actor.userMessage ! InitChat(fromId, (o \ "d").as[String])
        }
      }

      case ("m", o) => userId foreach { u =>
        if(u.length() > 0) {
          (o \ "d").asOpt[JsObject] foreach { data =>
            hub.actor.userMessage ! Msg(u, data)
          }
        }
      }

      case ("following_onlines", _) => userId foreach { u =>
        hub.actor.relation ! ReloadOnlineFriends(u)
      }

      case ("test", o) => {
        val mes = (o\"d").as[JsObject]
        mes str "to" foreach { to =>
          mes str "mes" foreach { mes =>
            socket ! Test2(uid, to, mes)
          }
        }
      }
      case _ => // logwarn("Unhandled msg: " + msg)
    }

    def iteratee(controller: Controller, member: SocketMember): JsIteratee = {
      val control = controller orElse baseController(member)
      Iteratee.foreach[JsValue](jsv =>
        jsv.asOpt[JsObject] foreach { obj =>
          obj str "t" foreach { t =>
            control.lift(t -> obj)
          }
        }
      ).map(_ => socket ! Quit(uid))
    }

    socket ? join map connecter map {
      case (controller, enum, member) => iteratee(controller, member) -> enum
    }
  }
}
