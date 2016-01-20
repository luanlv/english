package controllers

import lila.activity.PostRepo
import org.joda.time.DateTime
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
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global


object API extends LilaController {

  private def env = Env.user
  private def relationApi = Env.relation.api
  private def friendshipApi = Env.relation.friendshipApi
  private def makeFriendApi = Env.relation.makeFriendApi
  private def postApi = Env.activity.postApi

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

  def doPost =  OpenBody(BodyParsers.parse.tolerantJson) { implicit ctx =>
    val req = ctx.body
    val content = (Json.parse(req.body.toString).as[JsObject]\"content").as[String]
    ctx.userId match {
      case Some(id) => {
        postApi.newPost(id, content, DateTime.now()) map {
          writeResult =>
            if(writeResult.ok){
              Ok(Json.obj("action" -> "ok"))
            } else {
              Ok(Json.obj("action" -> "error"))
            }
        }
      }
      case None => BadRequest.fuccess
    }
  }

  def getPost(username: String) = Open { implicit ctx =>
    if(ctx.userId.get == username) {
      val listFriend = friendshipApi.friends(username).await
      val listFollowing = relationApi.following(username).await
      val listUser = (listFriend ++ listFollowing).+(username)
//      println(listUser)
      val fuPost = PostRepo.getPost(listUser, DateTime.now())
      fuPost map {
        posts => Ok(Json.toJson(posts))
      }
    } else {
      val fuPost = PostRepo.getPost(Set(username), DateTime.now())
      fuPost map {
        posts => Ok(Json.toJson(posts))
      }
    }
  }

}
