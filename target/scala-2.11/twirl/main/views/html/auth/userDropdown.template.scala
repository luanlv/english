
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object userDropdown_Scope0 {
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

class userDropdown extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[User,Boolean,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(me: User, playing: Boolean)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.53*/("""

"""))
      }
    }
  }

  def render(me:User,playing:Boolean,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(me,playing)(ctx)

  def f:((User,Boolean) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (me,playing) => (ctx) => apply(me,playing)(ctx)

  def ref: this.type = this

}


}

/**/
object userDropdown extends userDropdown_Scope0.userDropdown
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/userDropdown.scala.html
                  HASH: d5772944f67f43795a37828916f7e0c4aa710679
                  MATRIX: 832->1|978->52
                  LINES: 27->1|32->1
                  -- GENERATED --
              */
          