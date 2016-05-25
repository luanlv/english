
package views.html.site

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object developers_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._
import lila.user.{ User, UserContext }
import lila.security.Permission
import lila.app.templating.Environment._
import lila.api.Context
import lila.common.paginator.Paginator
import com.ybrikman.ping.scalaapi.bigpipe.HtmlStream
import com.ybrikman.ping.scalaapi.bigpipe._

class developers extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),_display_(/*3.2*/site/*3.6*/.layout(title = "Developers")/*3.35*/ {_display_(Seq[Any](format.raw/*3.37*/("""

"""),format.raw/*5.1*/("""<div class="content_box small_box">
  <h1 class="lichess_title">Embed lichess in your site</h1>

  <p>
    Do you have a website, and want to add a playable live chess section?<br />
    Add the following line to your site html:
  </p>

  <pre>&lt;script src="http://en."""),_display_(/*13.35*/netDomain),format.raw/*13.44*/("""/embed?w=1016&amp;h=650"&gt;&lt;/script&gt;</pre>

  <p>The <strong>w</strong> and <strong>h</strong> parameters control the width and height of the chess window.</p>
</div>

<br />
<br />

<div class="content_box small_box developers">
  <h1 id="embed-tv" class="lichess_title">Embed the Chess TV in your site</h1>

  <div style="text-align: center;">
    <script src="http://en."""),_display_(/*25.29*/netDomain),format.raw/*25.38*/("""/tv/embed?theme=wood&bg=light"></script>
  </div>

  <p>
    Just add the following line to your site html:
  </p>

  <pre>&lt;script src="http://en."""),_display_(/*32.35*/netDomain),format.raw/*32.44*/("""/tv/embed?theme=brown&bg=light"&gt;&lt;/script&gt;</pre>

  <p>
    Parameters:
    <ul>
      <li>- <strong>theme</strong>: Controls the board theme. Available themes: """),_display_(/*37.82*/lila/*37.86*/.pref.Theme.list.map(_.name).mkString(", ")),format.raw/*37.129*/(""".</li>
      <li>- <strong>bg</strong>: Controls the background color. Available backgrounds: light, dark.</li>
    </ul>
  </p>
</div>

<br />
<br />

<div class="content_box small_box developers">
  <h1 id="embed-puzzle" class="lichess_title">Embed the daily puzzle in your site</h1>

  <div style="text-align: center;">
    <script src="http://en."""),_display_(/*50.29*/netDomain),format.raw/*50.38*/("""/training/embed?theme=blue2&bg=light"></script>
  </div>

  <p>
    Just add the following line to your site html:
  </p>

  <pre>&lt;script src="http://en."""),_display_(/*57.35*/netDomain),format.raw/*57.44*/("""/training/embed?theme=blue2&bg=light"&gt;&lt;/script&gt;</pre>

  <p>
    Parameters:
    <ul>
      <li>- <strong>theme</strong>: Controls the board theme. Available themes: """),_display_(/*62.82*/lila/*62.86*/.pref.Theme.list.map(_.name).mkString(", ")),format.raw/*62.129*/(""".</li>
      <li>- <strong>bg</strong>: Controls the background color. Available backgrounds: light, dark.</li>
    </ul>
  </p>

  <p>
    The text is automatically translated to your visitor language.
  </p>
</div>

<br />
<br />

<div class="content_box small_box developers">
  <h1 id="use-api" class="lichess_title">Lichess HTTP API</h1>

  <p>Lichess exposes a RESTful HTTP/JSON API that you are free to use without limitation.</p>
  <br />
  <p>Read the documentation on
    <a href="https://github.com/ornicar/lila#http-api">lichess github page</a>.
  </p>
</div>

<br />
<br />

""")))}),format.raw/*88.2*/("""
"""))
      }
    }
  }

  def render(ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply()(ctx)

  def f:(() => (Context) => play.twirl.api.HtmlFormat.Appendable) = () => (ctx) => apply()(ctx)

  def ref: this.type = this

}


}

/**/
object developers extends developers_Scope0.developers
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:26 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/site/developers.scala.html
                  HASH: b33facb445b6027e82e27b096696d0f73b224659
                  MATRIX: 815->1|935->26|963->29|974->33|1011->62|1050->64|1078->66|1376->337|1406->346|1814->727|1844->736|2021->886|2051->895|2248->1065|2261->1069|2326->1112|2704->1463|2734->1472|2918->1629|2948->1638|3151->1814|3164->1818|3229->1861|3848->2450
                  LINES: 27->1|32->1|34->3|34->3|34->3|34->3|36->5|44->13|44->13|56->25|56->25|63->32|63->32|68->37|68->37|68->37|81->50|81->50|88->57|88->57|93->62|93->62|93->62|119->88
                  -- GENERATED --
              */
          