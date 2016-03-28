
package views.html.relation

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object actions2_Scope0 {
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

class actions2 extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template8[String,Option[lila.relation.Relation],Option[lila.relation.Relation],Option[lila.relation.Relation],Boolean,Boolean,Boolean,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userId: String, relation: Option[lila.relation.Relation], makeFriend: Option[lila.relation.Relation], friend: Option[lila.relation.Relation],  followable: Boolean, blocked: Boolean, signup: Boolean = false)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.232*/("""

"""),format.raw/*3.1*/("""<div class="relation_actions">
	"""),_display_(/*4.3*/ctx/*4.6*/.userId.map/*4.17*/ { myId =>_display_(Seq[Any](format.raw/*4.27*/("""
		"""),_display_(/*5.4*/if(myId != userId)/*5.22*/ {_display_(Seq[Any](format.raw/*5.24*/("""
			"""),_display_(/*6.5*/if(!blocked)/*6.17*/ {_display_(Seq[Any](format.raw/*6.19*/("""
				"""),format.raw/*7.5*/("""<a data-hint=""""),_display_(/*7.20*/trans/*7.25*/.challengeToPlay()),format.raw/*7.43*/("""" href="@routes.Lobby.home()?user="""),_display_(/*7.79*/userId),format.raw/*7.85*/("""#friend" class="icon button hint--bottom" href="#"><span data-icon="U"></span></a>
				<a data-hint=""""),_display_(/*8.20*/trans/*8.25*/.composeMessage()),format.raw/*8.42*/("""" href="@routes.Message.form()?user="""),_display_(/*8.80*/userId),format.raw/*8.86*/("""" class="icon button hint--bottom" href="#"><span data-icon="c"></span></a>
			""")))}),format.raw/*9.5*/("""
			"""),_display_(/*10.5*/relation/*10.13*/ match/*10.19*/ {/*11.5*/case None =>/*11.17*/ {_display_(Seq[Any](format.raw/*11.19*/("""
					"""),_display_(/*12.7*/if(followable && !blocked)/*12.33*/ {_display_(Seq[Any](format.raw/*12.35*/("""
						"""),format.raw/*13.7*/("""<a
						class="icon button relation hint--bottom"
						href=""""),_display_(/*15.14*/routes/*15.20*/.Relation.follow(userId)),format.raw/*15.44*/(""""
						data-hint=""""),_display_(/*16.19*/trans/*16.24*/.follow()),format.raw/*16.33*/(""""><span data-icon="h"></span></a>
					""")))}),format.raw/*17.7*/("""
					"""),format.raw/*18.6*/("""<a
					class="icon button relation hint--bottom"
					href=""""),_display_(/*20.13*/routes/*20.19*/.Relation.block(userId)),format.raw/*20.42*/(""""
					data-hint=""""),_display_(/*21.18*/trans/*21.23*/.block()),format.raw/*21.31*/(""""><span data-icon="k"></span></a>
				""")))}/*23.5*/case Some(true) =>/*23.23*/ {_display_(Seq[Any](format.raw/*23.25*/("""
					"""),format.raw/*24.6*/("""<a class="button relation hover_text" href=""""),_display_(/*24.51*/routes/*24.57*/.Relation.unfollow(userId)),format.raw/*24.83*/("""">
						<span data-icon="h" class="base">&nbsp;"""),_display_(/*25.47*/trans/*25.52*/.following()),format.raw/*25.64*/("""</span>
						<span data-icon="h" class="hover">&nbsp;"""),_display_(/*26.48*/trans/*26.53*/.unfollow()),format.raw/*26.64*/("""</span>
					</a>
				""")))}/*29.5*/case Some(false) =>/*29.24*/ {_display_(Seq[Any](format.raw/*29.26*/("""
					"""),format.raw/*30.6*/("""<a class="button relation hover_text" href=""""),_display_(/*30.51*/routes/*30.57*/.Relation.unblock(userId)),format.raw/*30.82*/("""">
						<span data-icon="k" class="base">&nbsp;"""),_display_(/*31.47*/trans/*31.52*/.blocked()),format.raw/*31.62*/("""</span>
						<span data-icon="k" class="hover">&nbsp;"""),_display_(/*32.48*/trans/*32.53*/.unblock()),format.raw/*32.63*/("""</span>
					</a>
				""")))}}),format.raw/*35.5*/("""
		""")))}),format.raw/*36.4*/("""
	""")))}/*37.3*/.getOrElse/*37.13*/ {_display_(Seq[Any](format.raw/*37.15*/("""
		"""),_display_(/*38.4*/if(signup)/*38.14*/ {_display_(Seq[Any](format.raw/*38.16*/("""
			"""),_display_(/*39.5*/trans/*39.10*/.youNeedAnAccountToDoThat()),format.raw/*39.37*/("""
			"""),format.raw/*40.4*/("""<a href=""""),_display_(/*40.14*/routes/*40.20*/.Auth.login),format.raw/*40.31*/("""" class="signup">"""),_display_(/*40.49*/trans/*40.54*/.signUp),format.raw/*40.61*/("""</a>
		""")))}),format.raw/*41.4*/("""
	""")))}),format.raw/*42.3*/("""
"""),format.raw/*43.1*/("""</div>
"""))
      }
    }
  }

  def render(userId:String,relation:Option[lila.relation.Relation],makeFriend:Option[lila.relation.Relation],friend:Option[lila.relation.Relation],followable:Boolean,blocked:Boolean,signup:Boolean,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(userId,relation,makeFriend,friend,followable,blocked,signup)(ctx)

  def f:((String,Option[lila.relation.Relation],Option[lila.relation.Relation],Option[lila.relation.Relation],Boolean,Boolean,Boolean) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (userId,relation,makeFriend,friend,followable,blocked,signup) => (ctx) => apply(userId,relation,makeFriend,friend,followable,blocked,signup)(ctx)

  def ref: this.type = this

}


}

/**/
object actions2 extends actions2_Scope0.actions2
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/relation/actions2.scala.html
                  HASH: c7c8beea2238f55b508339be721aae581002cb9e
                  MATRIX: 939->1|1265->231|1293->233|1351->266|1361->269|1380->280|1427->290|1456->294|1482->312|1521->314|1551->319|1571->331|1610->333|1641->338|1682->353|1695->358|1733->376|1794->412|1820->418|1948->520|1961->525|1998->542|2061->580|2087->586|2196->666|2227->671|2244->679|2259->685|2269->692|2290->704|2330->706|2363->713|2398->739|2438->741|2472->748|2563->812|2578->818|2623->842|2670->862|2684->867|2714->876|2784->916|2817->922|2906->984|2921->990|2965->1013|3011->1032|3025->1037|3054->1045|3111->1089|3138->1107|3178->1109|3211->1115|3283->1160|3298->1166|3345->1192|3421->1241|3435->1246|3468->1258|3550->1313|3564->1318|3596->1329|3637->1357|3665->1376|3705->1378|3738->1384|3810->1429|3825->1435|3871->1460|3947->1509|3961->1514|3992->1524|4074->1579|4088->1584|4119->1594|4173->1622|4207->1626|4228->1629|4247->1639|4287->1641|4317->1645|4336->1655|4376->1657|4407->1662|4421->1667|4469->1694|4500->1698|4537->1708|4552->1714|4584->1725|4629->1743|4643->1748|4671->1755|4709->1763|4742->1766|4770->1767
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|35->4|36->5|36->5|36->5|37->6|37->6|37->6|38->7|38->7|38->7|38->7|38->7|38->7|39->8|39->8|39->8|39->8|39->8|40->9|41->10|41->10|41->10|41->11|41->11|41->11|42->12|42->12|42->12|43->13|45->15|45->15|45->15|46->16|46->16|46->16|47->17|48->18|50->20|50->20|50->20|51->21|51->21|51->21|52->23|52->23|52->23|53->24|53->24|53->24|53->24|54->25|54->25|54->25|55->26|55->26|55->26|57->29|57->29|57->29|58->30|58->30|58->30|58->30|59->31|59->31|59->31|60->32|60->32|60->32|62->35|63->36|64->37|64->37|64->37|65->38|65->38|65->38|66->39|66->39|66->39|67->40|67->40|67->40|67->40|67->40|67->40|67->40|68->41|69->42|70->43
                  -- GENERATED --
              */
          