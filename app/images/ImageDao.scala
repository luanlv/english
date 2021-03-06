package images

import java.io._

import com.google.inject.ImplementedBy
import play.api.libs.json.JsValue
import reactivemongo.api.{CursorProducer, BSONSerializationPack}
import reactivemongo.api.gridfs.{GridFS, ReadFile}
import reactivemongo.bson.BSONValue

import scala.concurrent.Future

@ImplementedBy(classOf[ImagesDaoMongo])
trait ImagesDao{

  def save3size(gfs: GridFS[BSONSerializationPack.type], f: File, uuid: String, filename: String, contentType: Option[String]):   Future[ReadFile[BSONSerializationPack.type, BSONValue]]

  def saveAvatar(gfs: GridFS[BSONSerializationPack.type], f: File, uuid: String, filename: String, contentType: Option[String]):   Future[ReadFile[BSONSerializationPack.type, BSONValue]]

}