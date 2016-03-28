
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object login_Scope0 {
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

class login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},Option[String],Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_], referrer: Option[String])(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.66*/("""

"""),_display_(/*3.2*/auth/*3.6*/.layout(title = trans.signIn.str(), zen = true)/*3.53*/ {_display_(Seq[Any](format.raw/*3.55*/("""

	"""),format.raw/*5.2*/("""<div class="ui segment">
		<div class="ui form">
			<h1 class="lichess_title">Login !</h1>
			<form action=""""),_display_(/*8.19*/routes/*8.25*/.Auth.authenticate),format.raw/*8.43*/("""?referrer="""),_display_(/*8.54*/referrer),format.raw/*8.62*/("""" method="POST">
				<div>
					"""),_display_(/*10.7*/form/*10.11*/.globalError.map/*10.27*/ { error =>_display_(Seq[Any](format.raw/*10.38*/("""
						"""),format.raw/*11.7*/("""<p class="error">"""),_display_(/*11.25*/error/*11.30*/.message),format.raw/*11.38*/("""</p>
					""")))}),format.raw/*12.7*/("""
					"""),_display_(/*13.7*/auth/*13.11*/.formFields(form("username"), form("password"), None)),format.raw/*13.64*/("""
					"""),format.raw/*14.6*/("""<div>
						<button type="submit" class="ui teal button" data-icon="F"> """),_display_(/*15.68*/trans/*15.73*/.signIn()),format.raw/*15.82*/("""</button>

					</div>
					<div>
						<br>
						"""),_display_(/*20.8*/trans/*20.13*/.forgotPassword()),format.raw/*20.30*/("""
						"""),format.raw/*21.7*/("""<a href="javascript:void(0)"> """),_display_(/*21.38*/trans/*21.43*/.passwordReset()),format.raw/*21.59*/("""</a>
						<br>
						Not have account?
						<a href="javascript:void(0)" onclick="signup()"> """),_display_(/*24.57*/trans/*24.62*/.signUp()),format.raw/*24.71*/("""</a>
					</div>
				</div>
				<input type="hidden" class="referrer" name="_referrer" value=""""),_display_(/*27.68*/referrer/*27.76*/.getOrElse(currentUrl)),format.raw/*27.98*/("""" />
			</form>
		</div>
	</div>

""")))}))
      }
    }
  }

  def render(form:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},referrer:Option[String],ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(form,referrer)(ctx)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},Option[String]) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (form,referrer) => (ctx) => apply(form,referrer)(ctx)

  def ref: this.type = this

}


}

/**/
object login extends login_Scope0.login
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/login.scala.html
                  HASH: eae10c6cec16b91f415f598bf60c94135be3ff32
                  MATRIX: 899->1|1058->65|1086->68|1097->72|1152->119|1191->121|1220->124|1355->233|1369->239|1407->257|1444->268|1472->276|1531->309|1544->313|1569->329|1618->340|1652->347|1697->365|1711->370|1740->378|1781->389|1814->396|1827->400|1901->453|1934->459|2034->532|2048->537|2078->546|2156->598|2170->603|2208->620|2242->627|2300->658|2314->663|2351->679|2474->775|2488->780|2518->789|2640->884|2657->892|2700->914
                  LINES: 29->1|34->1|36->3|36->3|36->3|36->3|38->5|41->8|41->8|41->8|41->8|41->8|43->10|43->10|43->10|43->10|44->11|44->11|44->11|44->11|45->12|46->13|46->13|46->13|47->14|48->15|48->15|48->15|53->20|53->20|53->20|54->21|54->21|54->21|54->21|57->24|57->24|57->24|60->27|60->27|60->27
                  -- GENERATED --
              */
          