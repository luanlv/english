package lila.memo

import com.typesafe.config.Config
import lila.db.Types._

final class Env(config: Config,  db: lila.db.Env) {

  private val CollectionCache = config getString "collection.cache"
  private val CollectionUserMessage = config getString "collection.userMessage"
  private val CollectionSocketMessage = config getString "collection.socketMessage"

  lazy val mongoCache: MongoCache.Builder = MongoCache(db(CollectionCache))
  lazy val mongoUserMessage: UserMessage.Builder = UserMessage(db(CollectionUserMessage))
  lazy val mongoSocketMessage: SocketMessage.Builder = SocketMessage(db(CollectionSocketMessage))

}

object Env {

  lazy val current = "[boot] memo" describes new Env(
    lila.common.PlayApp loadConfig "memo",
    lila.db.Env.current)
}
