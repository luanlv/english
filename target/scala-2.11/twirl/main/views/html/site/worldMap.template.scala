
package views.html.site

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object worldMap_Scope0 {
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

class worldMap extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html>
  <head>
    <title>Lichess World Map</title>
    <link rel="shortcut icon" href=""""),_display_(/*8.38*/routes/*8.44*/.Assets.at("images/favicon-32-white.png")),format.raw/*8.85*/("""" type="image/x-icon" />

  </head>
  <body>
    <h1><a href="http://lichess.org">lichess<span class="extension">.org</span></a> network</h1>
    <div id="worldmap"></div>
    <div id="stats">
      <div class="wrapL">
        <div id="time">time: <span></span></div>
        <div id="moves">moves: <span></span></div>
        <div id="countries">countries: <span></span></div>
      </div>
      <div class="wrapL">
        <div id="topCountries"></div>
      </div>
    </div>

  </body>
</html>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object worldMap extends worldMap_Scope0.worldMap
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/site/worldMap.scala.html
                  HASH: b89de07aeb541bba3864c0e4285433b6a6568d8e
                  MATRIX: 803->1|899->3|927->5|1060->112|1074->118|1135->159
                  LINES: 27->1|32->1|34->3|39->8|39->8|39->8
                  -- GENERATED --
              */
          