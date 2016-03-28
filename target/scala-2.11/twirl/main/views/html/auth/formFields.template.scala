
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object formFields_Scope0 {
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

class formFields extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[Field,Field,Option[Field],Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(username: Field, password: Field, emailOption: Option[Field])(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.87*/("""

"""),format.raw/*3.1*/("""<div class="field">
	<label for=""""),_display_(/*4.15*/username/*4.23*/.id),format.raw/*4.26*/("""">"""),_display_(/*4.29*/trans/*4.34*/.username()),format.raw/*4.45*/("""</label>
	<input
	type="text"
	pattern="^[\w-]+$"
	required="required"
	name=""""),_display_(/*9.9*/username/*9.17*/.name),format.raw/*9.22*/(""""
	id=""""),_display_(/*10.7*/username/*10.15*/.id),format.raw/*10.18*/(""""
	value=""""),_display_(/*11.10*/username/*11.18*/.value),format.raw/*11.24*/(""""/>
</div>


<div class="field">
	<label for=""""),_display_(/*16.15*/password/*16.23*/.id),format.raw/*16.26*/("""">"""),_display_(/*16.29*/trans/*16.34*/.password()),format.raw/*16.45*/("""</label>
	<input
	type="password"
	required="required"
	name=""""),_display_(/*20.9*/password/*20.17*/.name),format.raw/*20.22*/(""""
	id=""""),_display_(/*21.7*/password/*21.15*/.id),format.raw/*21.18*/("""" />
	"""),_display_(/*22.3*/errMsg(password)),format.raw/*22.19*/("""
"""),format.raw/*23.1*/("""</div>


"""),_display_(/*26.2*/emailOption/*26.13*/.map/*26.17*/ { email =>_display_(Seq[Any](format.raw/*26.28*/("""
	"""),format.raw/*27.2*/("""<div class="field">
		<label for=""""),_display_(/*28.16*/email/*28.21*/.id),format.raw/*28.24*/("""">"""),_display_(/*28.27*/trans/*28.32*/.email()),format.raw/*28.40*/("""</label>
		<input
		type="email"
		required="required"
		name=""""),_display_(/*32.10*/email/*32.15*/.name),format.raw/*32.20*/(""""
		id=""""),_display_(/*33.8*/email/*33.13*/.id),format.raw/*33.16*/(""""
		value=""""),_display_(/*34.11*/email/*34.16*/.value),format.raw/*34.22*/(""""/>
		"""),_display_(/*35.4*/errMsg(email)),format.raw/*35.17*/("""
	"""),format.raw/*36.2*/("""</div>
""")))}),format.raw/*37.2*/("""
"""))
      }
    }
  }

  def render(username:Field,password:Field,emailOption:Option[Field],ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(username,password,emailOption)(ctx)

  def f:((Field,Field,Option[Field]) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (username,password,emailOption) => (ctx) => apply(username,password,emailOption)(ctx)

  def ref: this.type = this

}


}

/**/
object formFields extends formFields_Scope0.formFields
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/auth/formFields.scala.html
                  HASH: e5aa0b2e9be386d5d431563f35ca53ba1b2730a2
                  MATRIX: 841->1|1021->86|1049->88|1109->122|1125->130|1148->133|1177->136|1190->141|1221->152|1325->231|1341->239|1366->244|1400->252|1417->260|1441->263|1479->274|1496->282|1523->288|1597->335|1614->343|1638->346|1668->349|1682->354|1714->365|1803->428|1820->436|1846->441|1880->449|1897->457|1921->460|1954->467|1991->483|2019->484|2055->494|2075->505|2088->509|2137->520|2166->522|2228->557|2242->562|2266->565|2296->568|2310->573|2339->581|2430->645|2444->650|2470->655|2505->664|2519->669|2543->672|2582->684|2596->689|2623->695|2656->702|2690->715|2719->717|2757->725
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|35->4|35->4|35->4|40->9|40->9|40->9|41->10|41->10|41->10|42->11|42->11|42->11|47->16|47->16|47->16|47->16|47->16|47->16|51->20|51->20|51->20|52->21|52->21|52->21|53->22|53->22|54->23|57->26|57->26|57->26|57->26|58->27|59->28|59->28|59->28|59->28|59->28|59->28|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|67->36|68->37
                  -- GENERATED --
              */
          