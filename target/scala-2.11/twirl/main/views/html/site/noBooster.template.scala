
package views.html.site

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object noBooster_Scope0 {
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

class noBooster extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),_display_(/*3.2*/site/*3.6*/.message("No engine area")/*3.32*/ {_display_(Seq[Any](format.raw/*3.34*/("""
"""),format.raw/*4.1*/("""Sorry, boosters and sandbaggers are not allowed in this area.
""")))}),format.raw/*5.2*/("""
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
object noBooster extends noBooster_Scope0.noBooster
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:26 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/site/noBooster.scala.html
                  HASH: 375c03bb757ec319a094f98255a7828ee59ddfd8
                  MATRIX: 813->1|933->26|961->29|972->33|1006->59|1045->61|1072->62|1164->125
                  LINES: 27->1|32->1|34->3|34->3|34->3|34->3|35->4|36->5
                  -- GENERATED --
              */
          