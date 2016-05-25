
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object passwordResetConfirm_Scope0 {
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

class passwordResetConfirm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[User,String,Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},Option[Boolean],Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(u: User, token: String, form: Form[_], ok: Option[Boolean] = None)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.92*/("""


"""))
      }
    }
  }

  def render(u:User,token:String,form:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},ok:Option[Boolean],ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(u,token,form,ok)(ctx)

  def f:((User,String,Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},Option[Boolean]) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (u,token,form,ok) => (ctx) => apply(u,token,form,ok)(ctx)

  def ref: this.type = this

}


}

/**/
object passwordResetConfirm extends passwordResetConfirm_Scope0.passwordResetConfirm
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/passwordResetConfirm.scala.html
                  HASH: a377155a542be80df4fe0cd18cc1244e38fd0a87
                  MATRIX: 942->1|1127->91
                  LINES: 29->1|34->1
                  -- GENERATED --
              */
          