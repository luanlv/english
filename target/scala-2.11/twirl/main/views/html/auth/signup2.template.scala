
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object signup2_Scope0 {
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

class signup2 extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[_])(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.40*/("""

"""),_display_(/*3.2*/auth/*3.6*/.layout(
	title = trans.signUp.str(),
	zen = true)/*5.13*/ {_display_(Seq[Any](format.raw/*5.15*/("""
	"""),format.raw/*6.2*/("""<div class="content_box small_box signup">
		<div class="alternative">
			"""),_display_(/*8.5*/trans/*8.10*/.haveAnAccount()),format.raw/*8.26*/("""
			"""),format.raw/*9.4*/("""<a href=""""),_display_(/*9.14*/routes/*9.20*/.Auth.login()),format.raw/*9.33*/("""" class="button" data-icon="F"> """),_display_(/*9.66*/trans/*9.71*/.signIn()),format.raw/*9.80*/("""</a>
		</div>
		<div class="signup_box">
			<h1 class="lichess_title">"""),_display_(/*12.31*/trans/*12.36*/.signUp()),format.raw/*12.45*/("""</h1>
			<form action=""""),_display_(/*13.19*/routes/*13.25*/.Auth.signupPost),format.raw/*13.41*/("""" method="POST">
				<ul>
					"""),_display_(/*15.7*/auth/*15.11*/.formFields(form("username"), form("password"), form("email").some)),format.raw/*15.78*/("""
					"""),format.raw/*16.6*/("""<li>
					</li>
					"""),_display_(/*18.7*/errMsg(form)),format.raw/*18.19*/("""
					"""),format.raw/*19.6*/("""<li data-icon="!">&nbsp;
						<small>
							By registering, you agree to be bound by our
						</small>
					</li>
					<li>
						<button type="submit" class="submit button" data-icon="F"> """),_display_(/*25.67*/trans/*25.72*/.signUp()),format.raw/*25.81*/("""</button>
					</li>
				</ul>
				<input type="hidden" class="referrer" name="_referrer" value=""""),_display_(/*28.68*/currentUrl),format.raw/*28.78*/("""" />
			</form>
		</div>
	</div>
""")))}))
      }
    }
  }

  def render(form:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(form)(ctx)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (form) => (ctx) => apply(form)(ctx)

  def ref: this.type = this

}


}

/**/
object signup2 extends signup2_Scope0.signup2
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/signup2.scala.html
                  HASH: 9e31801b4526296e55197746273d0d32a63205fd
                  MATRIX: 888->1|1021->39|1049->42|1060->46|1118->96|1157->98|1185->100|1285->175|1298->180|1334->196|1364->200|1400->210|1414->216|1447->229|1506->262|1519->267|1548->276|1646->347|1660->352|1690->361|1741->385|1756->391|1793->407|1851->439|1864->443|1952->510|1985->516|2033->538|2066->550|2099->556|2319->749|2333->754|2363->763|2488->861|2519->871
                  LINES: 29->1|34->1|36->3|36->3|38->5|38->5|39->6|41->8|41->8|41->8|42->9|42->9|42->9|42->9|42->9|42->9|42->9|45->12|45->12|45->12|46->13|46->13|46->13|48->15|48->15|48->15|49->16|51->18|51->18|52->19|58->25|58->25|58->25|61->28|61->28
                  -- GENERATED --
              */
          