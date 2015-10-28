package lila.socket

import akka.actor.ActorRef
import akka.pattern.{ ask, pipe }
import lila.common.LightUser
import lila.hub.actorApi.userMessage._
import lila.hub.actorApi.chatRoom._
import play.api.libs.iteratee.{ Iteratee, Enumerator }
import play.api.libs.json._

import actorApi._
import lila.common.PimpedJson._
import lila.hub.actorApi.relation.ReloadOnlineFriends
import makeTimeout.large

import scala.concurrent.Future
import scala.util.parsing.json.JSONObject

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
      case ("p", o) => {
        userId match {
          case None => socket ! Ping(uid, 0)
          case Some(user) => {
            (hub.actor.userMessage ? PingVersion(user)) foreach {
              case v: Int => socket ! Ping(uid, v)
              case _ => //println("unhander !!!")
            }
          }
        }
      }

      case("initChat", o) => {
        val obj = (o obj "d").get
        (obj str "t").get match {
          case "chatrooms" => {
            socket ! Sub(uid, "chatrooms", userId)
            userId foreach { userId =>
              if(userId.length() > 0) hub.actor.chatRoom ! UserSubscribe(userId, "chatrooms")
            }
          }
          case "room" => {
            val roomId = (obj str "v").get
            userId foreach { userId =>
              if(userId.length()>0) hub.actor.chatRoom ! UserSubscribe(userId, roomId)
            }
            socket ! InitChatRoom(uid, roomId, userId)
          }
          case _ =>
        }
      }

      case("sub", o) => {
        val obj = (o obj "d").get
        (obj str "t").get match {
          case "chatrooms" => {
            socket ! Sub(uid, "chatrooms", userId)
            userId foreach { userId =>
              if(userId.length() > 0) hub.actor.chatRoom ! UserSubscribe(userId, "chatrooms")
            }
          }
          case "room" => {
            val roomId = (obj str "v").get
            socket ! Sub(uid, roomId, userId)
            userId foreach { userId =>
              if(userId.length()>0) hub.actor.chatRoom ! UserSubscribe(userId, roomId)
            }
          }
          case _ =>
        }
      }

      case("unSub", o) => {
        ((o obj "d").get str "t").get match {
          case "chatrooms" => {
            socket ! UnSub(uid, "chatrooms")
          }
          case "room" => socket ! UnSub(uid, ((o obj "d").get str "v").get)

          case _ =>
        }
      }

      case ("get_onlines", _) => userId foreach { u =>
        (hub.actor.userMessage ? GetOnlineUser(u)) foreach {
          case data: List[LightUser] => socket ! OnlineFriends(uid, data)
          case _ =>
        }
      }

      case ("gn", o) => userId foreach { u =>
        val id = (o\"d").as[String]
        (hub.actor.userMessage ? GetName(id)) foreach {
          case "error" => //println("errror:" +id)
          case name:String  => socket ! SendName(uid, id, name)
          case _ =>
        }
      }

      case ("gmm", o) => userId foreach { u =>
        if(u.length > 0){
          val f = ((o\"d").as[JsObject]\"f").as[Int]
          val t = ((o\"d").as[JsObject]\"t").as[Int]
          (hub.actor.userMessage ? MissingMes(u, f, t)) foreach {
            case dataFu: Future[List[JsValue]] => dataFu.map{
              data => socket ! SendMissingMes(uid, f, t, data)
            }
            case _ => //println("gmm from " + userId + " error!")
          }
        }
      }

      case ("mr", o) => userId foreach { userId =>
        if(userId.length > 0){
          val toId = (o\"d"\"uid").as[String]
          val mv = (o\"d"\"mv").as[Int]
          hub.actor.userMessage ! MarkRead(userId, toId, mv)
        }
      }

      case ("init_chat", o) => userId foreach { fromId =>
        if(fromId.length() > 0) {
          (hub.actor.userMessage ? InitChat(fromId, (o \ "d" \ "w").as[String], (o \ "d" \ "cv").as[Int])) foreach{
            case dataFu: Future[List[JsValue]] => dataFu.map{
              case data => {
                socket ! SendInitMes(uid, data)
              }
              case _ => //println("init_chat from " + userId + " error!")
            }
          }
        }
      }

      case ("gnm", o) => userId foreach { userId =>
        if(userId.length() > 0) {
          (hub.actor.userMessage ? InitNotify(userId)) foreach {
            case dataFu: Future[List[JsValue]] => dataFu.map{
              case data => socket ! SendInitNotify(uid, data)
              case _ => //println("gnm from" + userId + " error!")
            }

          }
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
