
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object tor_Scope0 {
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

class tor extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),_display_(/*3.2*/auth/*3.6*/.layout(
title = "TOR exit node",
zen = true)/*5.12*/ {_display_(Seq[Any](format.raw/*5.14*/("""
"""),format.raw/*6.1*/("""<div class="content_box small_box signup">
  <div class="signup_box">
    <h1 class="lichess_title text" data-icon="2">Cannot login from your IP</h1>
    <p>
      We have detected that you are using TOR to remain anonymous on the Internet.
      <br />
      <br />
      It's a great idea, and we'll make sure you stay Anonymous on lichess as well.
      <br />
      <br />
      As an Anonymous user, you can play, train, and use all lichess features. Enjoy!
    </p>
  </div>
</div>
""")))}),format.raw/*20.2*/("""
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
object tor extends tor_Scope0.tor
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/tor.scala.html
                  HASH: 8c4fb76d940af8a8518c027c7a625a043c6eae20
                  MATRIX: 801->1|921->26|949->29|960->33|1013->78|1052->80|1079->81|1598->570
                  LINES: 27->1|32->1|34->3|34->3|36->5|36->5|37->6|51->20
                  -- GENERATED --
              */
          