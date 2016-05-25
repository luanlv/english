
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object checkYourEmail_Scope0 {
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

class checkYourEmail extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[User,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(u: User)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.34*/("""

"""),_display_(/*3.2*/auth/*3.6*/.layout(
title = "Check your email",
zen = true)/*5.12*/ {_display_(Seq[Any](format.raw/*5.14*/("""
"""),format.raw/*6.1*/("""<div class="content_box small_box signup">
  <div class="signup_box">
    <h1 class="lichess_title is-green" data-icon="E">Check your email</h1>
    <p>We've sent you an email. Click the link in the email to activate your account.</p>
    <p>If you don't see the email, check other places it might be, like your junk, spam, social, or other folders.</p>
  </div>
</div>
""")))}),format.raw/*13.2*/("""
"""))
      }
    }
  }

  def render(u:User,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(u)(ctx)

  def f:((User) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (u) => (ctx) => apply(u)(ctx)

  def ref: this.type = this

}


}

/**/
object checkYourEmail extends checkYourEmail_Scope0.checkYourEmail
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/checkYourEmail.scala.html
                  HASH: 3b6e3b0d1585f8d3df11a088cc0eea308e313c60
                  MATRIX: 828->1|955->33|983->36|994->40|1050->88|1089->90|1116->91|1517->462
                  LINES: 27->1|32->1|34->3|34->3|36->5|36->5|37->6|44->13
                  -- GENERATED --
              */
          