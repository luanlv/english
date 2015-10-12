package lila.userMessage

import lila.common.LightUser
import org.joda.time.DateTime
import play.api.libs.json._


private[userMessage] case class NotifyMessage(
                                               user: LightUser,
                                               n: Int,
                                               d: DateTime,
                                               lm: UserMessage)

private[userMessage] object NotifyMessage {
  implicit val formatNotifyMessage = Json.format[NotifyMessage]
  import reactivemongo.bson._

  private[userMessage] implicit val BSONReader = new BSONDocumentReader[NotifyMessage] {
    implicit object BSONDateTimeHandler extends BSONHandler[BSONDateTime, DateTime] {
      def read(time: BSONDateTime) = new DateTime(time.value)
      def write(jdtime: DateTime) = BSONDateTime(jdtime.getMillis)
    }
    def read(doc: BSONDocument): NotifyMessage = {
      NotifyMessage(
        user = lila.user.Env.current.lightUserApi.get(~doc.getAs[String]("uid")).head,
        n = ~doc.getAs[Int]("n"),
        d = doc.getAs[DateTime]("d").head,
        lm = doc.getAs[UserMessage]("lm").head
      )
    }
  }
}
