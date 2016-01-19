package controllers

import play.api.data._, Forms._
import play.api.i18n.Messages.Implicits._
import play.api.libs.json._
import play.api.mvc._, Results._
import play.api.Play.current

import lila.api.Context
import lila.app._
import lila.common.{ LilaCookie, HTTPRequest }
import lila.user.{ UserRepo, User => UserModel }
import views._
import scala.concurrent.Future


object API extends LilaController {

  private def env = Env.user
  private def relationApi = Env.relation.api
  private def friendshipApi = Env.relation.friendshipApi
  private def makeFriendApi = Env.relation.makeFriendApi

  def getSelfInformation = Auth { implicit ctx =>
    me => {
      Ok(Json.obj("username" -> me.username, "name" -> me.name, "avatar" -> me.avatar)).fuccess
    }
  }

  def updateInformation =  OpenBody(BodyParsers.parse.tolerantJson) { implicit ctx =>
    val req = ctx.body
    val name = (Json.parse(req.body.toString).as[JsObject]\"name").as[String]
    println(name)
    UserRepo.updateName(ctx.userId.get, name) map {
      lila.user.Env.current.lightUserApi.refresh(ctx.userId.get)
      result => Ok("")
    }
  }

  def getInformationUser(username: String) = Open { implicit ctx =>
    OptionFuResult(UserRepo named username) { user =>

      (ctx.userId ?? { relationApi.blocks(user.id, _) }) zip
        (ctx.isAuth ?? { Env.pref.api.followable(user.id) }) zip
        (ctx.userId ?? { relationApi.relation(_, user.id) }) zip
        (ctx.userId ?? { makeFriendApi.makeFriend(_, user.id) }) zip
        (ctx.userId ?? { friendshipApi.friendship(_, user.id) }) zip
        relationApi.nbFollowers(user.id)  zip
        friendshipApi.nbFriends(user.id)  map {
        case ((((((blocked, followable), relation), makeFriend), friend), nbFollower), nbFriends) =>
          Ok(Json.obj(
            "username" -> user.username,
            "name" -> user.name,
            "avatar" -> user.avatar,
            "extra" -> Json.obj(
              "block" -> blocked,
              "followable" -> followable,
              "relation" -> relation,
              "makeFriend" -> makeFriend,
              "friend" -> friend,
              "nbFollower" -> nbFollower,
              "nbFriends" -> nbFriends
            )
          ))
      }
    }
  }
    //        case Some(userInfo) => Ok(Json.obj("username" -> userInfo.username, "name" -> userInfo.name,
  // "avatar" -> userInfo.avatar))
}
