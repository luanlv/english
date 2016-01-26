package lila.question

import java.util.UUID

import akka.actor.ActorSelection
//import lila.activity.actorApi._
import lila.common.LightUser
import org.joda.time.DateTime
import scala.util.Success
import lila.question.Cached
import lila.db.api._
import lila.db.Implicits._

import lila.hub.actorApi.relation.ReloadOnlineFriends
import lila.hub.actorApi.timeline.{ Propagate, Follow => FollowUser }
//import lila.usrMessage.MessageRepo

final class QuestionApi(
                     cached: Cached,
                     actor: ActorSelection) {

  def newQuestion(userId: String, question: String, description: String) = {
    val questionId = UUID.randomUUID().toString
    QuestionRepo.insert(questionId, userId, question, description) // >>- pushPost(userId, postId)
  }
}
