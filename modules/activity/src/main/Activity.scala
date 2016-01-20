package lila.activity

import lila.common.LightUser
import org.joda.time.DateTime
import play.api.libs.json._


private[activity] case class Post(
                                        id: String,
                                       content: String,
                                       user: LightUser,
                                       published: DateTime
                                     )

private[activity] object Post {
  implicit val formatPost = Json.format[Post]

  import reactivemongo.bson._

  private[activity] implicit val BSONReaderPost = new BSONDocumentReader[Post] {
    implicit object BSONDateTimeHandler extends BSONHandler[BSONDateTime, DateTime] {
      def read(time: BSONDateTime) = new DateTime(time.value)
      def write(jdtime: DateTime) = BSONDateTime(jdtime.getMillis)
    }
    def read(doc: BSONDocument): Post = {
      Post(
        id = ~doc.getAs[String]("_id"),
        content = ~doc.getAs[String]("content"),
        user = lila.user.Env.current.lightUserApi.get(~doc.getAs[String]("userId")).head,
        published = doc.getAs[DateTime]("published").head
      )
    }
  }

}