package lila.userMessage

import lila.common.LightUser
import lila.user.LightUserApi
import lila.db.BSON
import reactivemongo.bson._
import org.joda.time.DateTime

object BSONHandlers {
  implicit val messageBSONHandler = new BSON[UserMessage] {
    def reads(r: BSON.Reader) = {
      UserMessage(
        //_id = r str "_id",
        //mid = r str "mid",
        mv = r int "mv",
        f = lila.user.Env.current.lightUserApi.get(r str "f").head,
        t = lila.user.Env.current.lightUserApi.get(r str "t").head,
        mes = r str "mes",
        time = r date "time")
    }
    def writes(w: BSON.Writer, o: UserMessage) = {
      val mid = if(o.f.id < o.t.id) o.f.id + o.t.id else o.t.id + o.f.id
      BSONDocument(
        //"_id" -> o._id,
        "mid" -> mid,
        "mv" -> o.mv,
        "f" -> o.f.id,
        "t" -> o.t.id,
        "mes" -> o.mes,
        "time" -> w.date(o.time))
    }
  }
}