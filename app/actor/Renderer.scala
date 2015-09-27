package lila.app
package actor

import akka.actor._
import lila.user.UserRepo
import play.twirl.api.Html
import views.{html => V}

private[app] final class Renderer extends Actor {

  def receive = {


    case lila.notification.actorApi.RenderNotification(id, from, body) =>
      sender ! V.notification.view(id, from)(Html(body))


    case lila.hub.actorApi.RemindDeployPre =>
      sender ! spaceless(V.notification.deploy("pre"))
    case lila.hub.actorApi.RemindDeployPost =>
      sender ! spaceless(V.notification.deploy("post"))

  }

  private val spaceRegex = """\s{2,}""".r
  private def spaceless(html: Html) = Html {
    spaceRegex.replaceAllIn(html.body.replace("\\n", " "), " ")
  }
}
