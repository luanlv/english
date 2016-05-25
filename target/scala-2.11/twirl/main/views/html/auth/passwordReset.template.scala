
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object passwordReset_Scope0 {
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

class passwordReset extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},lila.common.Captcha2,Option[Boolean],Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_], captcha: lila.common.Captcha2, ok: Option[Boolean] = None)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.99*/("""


"""))
      }
    }
  }

  def render(form:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},captcha:lila.common.Captcha2,ok:Option[Boolean],ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(form,captcha,ok)(ctx)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},lila.common.Captcha2,Option[Boolean]) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (form,captcha,ok) => (ctx) => apply(form,captcha,ok)(ctx)

  def ref: this.type = this

}


}

/**/
object passwordReset extends passwordReset_Scope0.passwordReset
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/passwordReset.scala.html
                  HASH: 712b2010e73ce98fe6ca1c53958de9ee55af9a19
                  MATRIX: 937->1|1129->98
                  LINES: 29->1|34->1
                  -- GENERATED --
              */
          