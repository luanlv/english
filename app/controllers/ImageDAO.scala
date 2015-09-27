package controllers

import java.io._
import java.util.UUID

import com.sksamuel.scrimage.Image
import com.sksamuel.scrimage.ScaleMethod.Bicubic
import com.sksamuel.scrimage.nio.JpegWriter
import org.joda.time.DateTime
import play.api.libs.Files
import play.api.libs.iteratee.{Enumerator, Iteratee}
import play.api.libs.json.{JsObject, JsString, JsValue, Json}
import play.modules.reactivemongo.{MongoController, JSONFileToSave}
import play.modules.reactivemongo.json._
import play.mvc.Http.MultipartFormData

import reactivemongo.api.BSONSerializationPack
import reactivemongo.api.gridfs.{DefaultFileToSave, FileToSave, ReadFile, GridFS}
import reactivemongo.bson.{BSONString, BSONDocument, BSONDocumentReader, BSONValue}
import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import reactivemongo.api.commands.bson.BSONFindAndModifyImplicits._
import ImplicitBSONHandlers._
import MongoController.readFileReads
import play.modules.reactivemongo.json.BSONFormats._
import reactivemongo.api.SerializationPackObject

import reactivemongo.api.gridfs.Implicits._

import ImplicitBSONHandlers._

import scala.util.{Failure, Success}

object ImageDAO {

  type G = GridFS[BSONSerializationPack.type]
  type G2 = GridFS[JSONSerializationPack.type]

  def save3sizes(gfs: G, f: File, uuid: String, filename: String, contentType: Option[String]) = {

    val enumerator = Enumerator.fromFile(f)
    val iterator1 = Enumerator.fromFile(f)
        .run(Iteratee.consume[Array[Byte]]())
    val iterator2 = Enumerator.fromFile(f)
        .run(Iteratee.consume[Array[Byte]]())

    def data(size: String) = DefaultFileToSave(
      id = BSONString(UUID.randomUUID().toString()),
      filename = Option(filename),
      contentType = contentType,
      uploadDate = Some(DateTime.now().getMillis),
      metadata =  BSONDocument(
        "uuid" -> uuid,
        "size" -> size
      )
    )

    iterator1.flatMap {
      bytes => {
        val enumerator: Enumerator[Array[Byte]] = Enumerator.outputStream(
          out => {
            implicit val writer = JpegWriter().withProgressive(true)
            Image(bytes).scaleTo(80, 80, Bicubic).forWriter(writer).write(out)
          }
        )
        gfs.save(enumerator, data("thumb"))
      }
    }

    iterator2.flatMap {
      bytes => {
        val enumerator: Enumerator[Array[Byte]] = Enumerator.outputStream(
          out => {
            implicit val writer = JpegWriter().withProgressive(true)
            Image(bytes).scaleTo(180, 150, Bicubic).forWriter(writer).write(out)
          }
        )
        gfs.save(enumerator, data("small"))
      }
    }

    gfs.save(enumerator, data("origin"))

  }
}
