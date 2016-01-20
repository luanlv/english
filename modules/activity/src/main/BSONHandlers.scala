package lila.activity

import lila.common.LightUser
import lila.user.LightUserApi
import lila.db.BSON
import reactivemongo.bson._
import org.joda.time.DateTime

object BSONHandlers {
  implicit val infoBSONHandler = new BSON[Info] {
    def reads(r: BSON.Reader) = {
      Info(
        nbLike = r int "nbLike",
        liker = r get[List[String]] "liker",
        nbShare = r int "nbShare",
        sharer = r get[List[String]] "liker",
        nbComment = r int "nbComment"
      )
    }

    def writes(w: BSON.Writer, o: Info) = {
      BSONDocument(
        "nbLike" -> o.nbLike,
        "liker" -> o.liker,
        "nbShare" -> o.nbShare,
        "sharer" -> o.sharer,
        "nbComment" -> o.nbComment
      )
    }
  }


  implicit val postBSONHandler = new BSON[Post] {
    def reads(r: BSON.Reader) = {
      Post(
        id = r str "_id",
        content = r str "content",
        user = lila.user.Env.current.lightUserApi.get(r str "userId").get,
        published = r date "published",
        info = r get[Info] "info"
      )
    }
    def writes(w: BSON.Writer, o: Post) = {
      BSONDocument(
        "_id" -> o.id,
        "content" -> o.content,
        "userId" -> o.user.id,
        "published" -> w.date(o.published),
        "info" -> o.info
      )
    }
  }
}