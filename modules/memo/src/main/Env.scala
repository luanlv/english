package lila.memo

import com.typesafe.config.Config
import lila.db.Types._

final class Env(config: Config,  db: lila.db.Env) {

  private val CollectionCache = config getString "collection.cache"
  private val CollectionUserMessage = config getString "collection.userMessage"
  private val CollectionNotifyMessage = config getString "collection.notifyMessage"

  lazy val mongoCache: MongoCache.Builder = MongoCache(db(CollectionCache))
  lazy val mongoUserMessage: UserMessage.Builder = UserMessage(db(CollectionUserMessage))
  lazy val mongoNotifyMessage: NotifyMessage.Builder = NotifyMessage(db(CollectionNotifyMessage))

}

object Env {

  lazy val current = "[boot] memo" describes new Env(
    lila.common.PlayApp loadConfig "memo",
    lila.db.Env.current)
}
