
package views.html.site

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object noEngine_Scope0 {
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

class noEngine extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),_display_(/*3.2*/site/*3.6*/.message("No engine area")/*3.32*/ {_display_(Seq[Any](format.raw/*3.34*/("""
"""),format.raw/*4.1*/("""Sorry, engines are not allowed in this area.
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
object noEngine extends noEngine_Scope0.noEngine
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:26 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/site/noEngine.scala.html
                  HASH: 742ec72efcdd23cb8282985f3b6666d85738d12f
                  MATRIX: 811->1|931->26|959->29|970->33|1004->59|1043->61|1070->62|1145->108
                  LINES: 27->1|32->1|34->3|34->3|34->3|34->3|35->4|36->5
                  -- GENERATED --
              */
          