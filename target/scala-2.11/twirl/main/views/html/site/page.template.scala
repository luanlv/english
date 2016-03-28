
package views.html.site

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object page_Scope0 {
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

class page extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),_display_(/*3.2*/site/*3.6*/.layout(
title = s"${}")/*4.16*/ {_display_(Seq[Any](format.raw/*4.18*/("""

"""),format.raw/*6.1*/("""<div class="content_box small_box doc_box">

  <h1 class="lichess_title"></h1>

  <div class="body">
    """),_display_(/*11.6*/Html("")),format.raw/*11.14*/("""
  """),format.raw/*12.3*/("""</div>
</div>
""")))}),format.raw/*14.2*/("""
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
object page extends page_Scope0.page
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/site/page.scala.html
                  HASH: 1fc0ba18166b1f9b6c9fd54a3fcb3485fb41f81a
                  MATRIX: 803->1|923->26|951->29|962->33|994->57|1033->59|1061->61|1193->167|1222->175|1252->178|1297->193
                  LINES: 27->1|32->1|34->3|34->3|35->4|35->4|37->6|42->11|42->11|43->12|45->14
                  -- GENERATED --
              */
          