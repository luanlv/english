
package controllers

import java.io.File
import javax.inject.Inject


import lila.image.{ ImageRepo, Image => ImageModel }
import lila.user.{UserContext, UserRepo}
import play.api.i18n.MessagesApi

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.{Iteratee, Enumerator}
import play.api.libs.json._
import play.api.mvc.{Action, Controller, Request}

import play.modules.reactivemongo.json.collection.JSONCollection
import play.modules.reactivemongo.{JSONFileToSave, MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import MongoController.readFileReads

import play.modules.reactivemongo.json.JSONSerializationPack
import reactivemongo.api.indexes.{IndexType, Index}
import reactivemongo.api.{BSONSerializationPack, QueryOpts}
import reactivemongo.api.gridfs.{DefaultFileToSave, GridFS, ReadFile}
import reactivemongo.bson.BSONDocument

import reactivemongo.api.gridfs.Implicits._
import java.util.UUID
import play.modules.reactivemongo.json._
import reactivemongo.bson._
import play.api.libs.json._
import lila.image.Image
import reactivemongo.api.commands.bson.BSONFindAndModifyImplicits._
import play.modules.reactivemongo.json._, ImplicitBSONHandlers._

import scala.concurrent.{Await, Future}
import images.{ImagesDaoMongo, ImagesDao}
import scala.concurrent.ExecutionContext.Implicits.global

class ImageCtrl @Inject() (
                            val messagesApi: MessagesApi,
                            val reactiveMongoApi: ReactiveMongoApi,
                            val imagesDao: ImagesDao)
  extends LilaController with MongoController with ReactiveMongoComponents {

  type JSONReadFile = ReadFile[reactivemongo.play.json.JSONSerializationPack.type, JsString]
  type BSONReadFile = ReadFile[BSONSerializationPack.type, BSONValue]

  private val gridFS = reactiveMongoApi.gridFS

  // let's build an index on our gridfs chunks collection if none
  gridFS.ensureIndex().onComplete {
    case index =>
//      Logger.info(s"Checked index, result is $index")
  }

  def gfs = {
    import play.modules.reactivemongo.json.collection._
    GridFS[BSONSerializationPack.type](db)
  }
//
//  gfs.files.indexesManager.ensure(
//    Index(List("metadata.uuid" -> IndexType.Ascending))
//  )
//  gfs.files.indexesManager.ensure(
//    Index(List("username" -> IndexType.Ascending))
//  )

  def getList(name: String, page: Int) = Action.async { request =>
    val futureList = ImageRepo.find(name, page)
    val total = lila.image.Env.current.cached.countCached(name).map(x => Math.ceil(x/12.0).toInt)

    val FuResult = for {
      list <- futureList
      total <- total
    } yield (list, total)
    FuResult map {
      data => Ok("")
    }
  }


  def uploadView = Action.async { request =>
    Future(Ok(""))
  }


  def upload = Action.async(parse.multipartFormData) { implicit request =>
    val uuid = UUID.randomUUID().toString
    val FuOpUserInfo= reqToCtx(request).map {
      ctx => (ctx.userId, ctx.avatar)
    }

    import scala.concurrent.duration._

    val opUserInfo =  Await.result(FuOpUserInfo, 5.second)

    opUserInfo._1 match {
      case None => Future(Ok("error"))
      case Some(userId) => {
        val fuResult = request.body.files.map { picture =>
          val file = picture.ref.file
          import java.io.File
          val filename = picture.filename

          val contentType = picture.contentType
          val length = picture.ref.file.length()

          imagesDao.saveAvatar(gfs, file, uuid, filename, contentType)
        }
        fuResult.head.map {
          result => {
            UserRepo.setAvatar(userId, uuid)
            lila.user.Env.current.lightUserApi.refresh(userId)
            delete(opUserInfo._2.get)
            Ok(uuid)
          }
        }
      }
    }
  }

  def delete(uuid: String) =  {
    import play.modules.reactivemongo.json._
    gridFS.find[JsObject, JSONReadFile](Json.obj("metadata.uuid" -> uuid)).
      collect[List]().flatMap { files =>
      // for each attachment, delete their chunks and then their file entry
      val deletions = files.map(gridFS.remove(_))
      Future.sequence(deletions)
    }
  }

  def get(size: String, uuid: String) = Action.async { request =>
    import play.modules.reactivemongo.json._
    val image = gridFS.find[JsObject, JSONReadFile](Json.obj("metadata.uuid" -> uuid, "metadata.size" -> size))
    serve[JsString, JSONReadFile](gridFS)(image, CONTENT_DISPOSITION_INLINE).map(_.withHeaders("cache-Control" -> "public, max-age=%d".format(60 * 60 * 24 * 30)))
  }
}