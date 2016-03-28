
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object signup_Scope0 {
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

class signup extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},lila.common.Captcha2,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_], captcha: lila.common.Captcha2)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.71*/("""

	"""),_display_(/*3.3*/auth/*3.7*/.layout(
		title = trans.signUp.str(),
		zen = true)/*5.14*/ {_display_(Seq[Any](format.raw/*5.16*/("""
		"""),format.raw/*6.3*/("""<div class="ui segment">
			<div class="ui form">
				<h1 class="lichess_title">Signup!</h1>
				<form action=""""),_display_(/*9.20*/routes/*9.26*/.Auth.signupPost),format.raw/*9.42*/("""" method="POST">
					<div>
						"""),_display_(/*11.8*/auth/*11.12*/.formFields(form("username"), form("password"), form("email").some)),format.raw/*11.79*/("""
						"""),format.raw/*12.7*/("""<div class="field">
							"""),_display_(/*13.9*/captcha/*13.16*/.question),format.raw/*13.25*/("""
							"""),format.raw/*14.8*/("""<input type="hidden" name="qId" value=""""),_display_(/*14.48*/captcha/*14.55*/.qId),format.raw/*14.59*/("""">
							<input type="text" name="solution" value>
						</div>
						"""),_display_(/*17.8*/errMsg(form)),format.raw/*17.20*/("""
						"""),format.raw/*18.7*/("""<div class="field" data-icon="!">&nbsp;
							<small>
								By registering, you agree to ....
							</small>
						</div>
						<div>
							<button type="submit" class="ui teal button" data-icon="F"> """),_display_(/*24.69*/trans/*24.74*/.signUp()),format.raw/*24.83*/("""</button>
						</div>
						<div>
							<br>
							Already Have an Account?
								<a href="javascript:void(0)" onclick="signin()"> """),_display_(/*29.59*/trans/*29.64*/.signIn()),format.raw/*29.73*/("""</a>
						</div>
					</div>
					<input type="hidden" class="referrer" name="_referrer" value=""""),_display_(/*32.69*/currentUrl),format.raw/*32.79*/("""" />
				</form>
			</div>
		</div>
	""")))}))
      }
    }
  }

  def render(form:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},captcha:lila.common.Captcha2,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(form,captcha)(ctx)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},lila.common.Captcha2) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (form,captcha) => (ctx) => apply(form,captcha)(ctx)

  def ref: this.type = this

}


}

/**/
object signup extends signup_Scope0.signup
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/signup.scala.html
                  HASH: 909b030a8bed945a9bd4d7132a751bfafcfbedfb
                  MATRIX: 907->1|1071->70|1100->74|1111->78|1171->130|1210->132|1239->135|1377->247|1391->253|1427->269|1488->304|1501->308|1589->375|1623->382|1677->410|1693->417|1723->426|1758->434|1825->474|1841->481|1866->485|1964->557|1997->569|2031->576|2264->782|2278->787|2308->796|2472->933|2486->938|2516->947|2641->1045|2672->1055
                  LINES: 29->1|34->1|36->3|36->3|38->5|38->5|39->6|42->9|42->9|42->9|44->11|44->11|44->11|45->12|46->13|46->13|46->13|47->14|47->14|47->14|47->14|50->17|50->17|51->18|57->24|57->24|57->24|62->29|62->29|62->29|65->32|65->32
                  -- GENERATED --
              */
          