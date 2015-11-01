package controllers

import lila.app._
import lila.hub.actorApi.{GetUserIds, GetUids}
import lila.user.{ Cached, UserRepo, User => UserModel }
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.http.ContentTypes
import play.api.libs.concurrent.Promise
import play.api.libs.json.{JsString, JsArray, JsObject, Json}
import play.api.mvc._
import views.html.helper.form
import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.duration._


import com.ybrikman.ping.javaapi.bigpipe.PageletRenderOptions
import com.ybrikman.ping.scalaapi.bigpipe._
import com.ybrikman.ping.scalaapi.bigpipe.HtmlStreamImplicits._

import com.ybrikman.ping.javaapi.bigpipe.PageletRenderOptions._
import scala.concurrent.ExecutionContext


object Application extends LilaController{

  private def env = lila.app.Env.current

  def index = Open { implicit  ctx =>
      Ok(views.html.index.home()).fuccess
  }

  def json = Open { implicit ctx =>
      val fuJson = Future(Json.obj("data" -> "data recieved from sever!!!"))
      val fuJsonDelay:Future[JsObject] =  Promise.timeout(fuJson, 1 second).flatMap(x => x)
    fuJsonDelay.map{
      data => Ok(data)
    }
  }

}



