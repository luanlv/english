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

  def getInformationUser(user: String) = Open { implicit ctx =>
    UserRepo.byId(user).map {
      dataUser => dataUser match {
        case None => BadRequest
        case Some(userInfo) => Ok(Json.obj("username" -> userInfo.username, "name" -> userInfo.name, "avatar" -> userInfo.avatar))
      }
    }
  }

}
