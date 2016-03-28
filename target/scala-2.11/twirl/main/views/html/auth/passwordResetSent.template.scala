
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object passwordResetSent_Scope0 {
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

class passwordResetSent extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(email: String)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.40*/("""


"""))
      }
    }
  }

  def render(email:String,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(email)(ctx)

  def f:((String) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (email) => (ctx) => apply(email)(ctx)

  def ref: this.type = this

}


}

/**/
object passwordResetSent extends passwordResetSent_Scope0.passwordResetSent
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/passwordResetSent.scala.html
                  HASH: 6c19ab851d2afa705b259458edcb00295cc3d557
                  MATRIX: 836->1|969->39
                  LINES: 27->1|32->1
                  -- GENERATED --
              */
          