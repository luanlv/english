
package views.html.site

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

class layout extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template6[String,Html,Html,Option[Html],Html,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, moreCss: Html = Html(""), moreJs: Html = Html(""), side: Option[Html] = None)(body: Html)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.130*/("""

"""))
      }
    }
  }

  def render(title:String,moreCss:Html,moreJs:Html,side:Option[Html],body:Html,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(title,moreCss,moreJs,side)(body)(ctx)

  def f:((String,Html,Html,Option[Html]) => (Html) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (title,moreCss,moreJs,side) => (body) => (ctx) => apply(title,moreCss,moreJs,side)(body)(ctx)

  def ref: this.type = this

}


}

/**/
object layout extends layout_Scope0.layout
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/site/layout.scala.html
                  HASH: dba41d33f2d4d62124cd264867bc3902a8323e4d
                  MATRIX: 842->1|1066->129
                  LINES: 27->1|32->1
                  -- GENERATED --
              */
          