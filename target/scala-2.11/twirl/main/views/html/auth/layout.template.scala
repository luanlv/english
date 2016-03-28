
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object layout_Scope0 {
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

class layout extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[String,Option[Html],Boolean,Html,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, side: Option[Html] = None, zen: Boolean = false)(body: Html)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.101*/("""


"""),_display_(/*4.2*/(body)),format.raw/*4.8*/("""
"""))
      }
    }
  }

  def render(title:String,side:Option[Html],zen:Boolean,body:Html,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(title,side,zen)(body)(ctx)

  def f:((String,Option[Html],Boolean) => (Html) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (title,side,zen) => (body) => (ctx) => apply(title,side,zen)(body)(ctx)

  def ref: this.type = this

}


}

/**/
object layout extends layout_Scope0.layout
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/layout.scala.html
                  HASH: b3193de3aef89376b616ea61e7369ebcf9be6ec1
                  MATRIX: 840->1|1035->100|1064->104|1089->110
                  LINES: 27->1|32->1|35->4|35->4
                  -- GENERATED --
              */
          