
package views.html.relation

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object actions_Scope0 {
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

class actions extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template6[String,Option[lila.relation.Relation],Boolean,Boolean,Boolean,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userId: String, relation: Option[lila.relation.Relation], followable: Boolean, blocked: Boolean, signup: Boolean = false)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.147*/("""

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

  def render(userId:String,relation:Option[lila.relation.Relation],followable:Boolean,blocked:Boolean,signup:Boolean,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(userId,relation,followable,blocked,signup)(ctx)

  def f:((String,Option[lila.relation.Relation],Boolean,Boolean,Boolean) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (userId,relation,followable,blocked,signup) => (ctx) => apply(userId,relation,followable,blocked,signup)(ctx)

  def ref: this.type = this

}


}

/**/
object actions extends actions_Scope0.actions
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/relation/actions.scala.html
                  HASH: c24541c8d73ef1f7cad194972a58043324fa327a
                  MATRIX: 875->1|1116->146|1144->148|1202->181|1212->184|1231->195|1278->205|1307->209|1333->227|1372->229|1402->234|1422->246|1461->248|1492->253|1533->268|1546->273|1584->291|1645->327|1671->333|1799->435|1812->440|1849->457|1912->495|1938->501|2047->581|2078->586|2095->594|2110->600|2120->607|2141->619|2181->621|2214->628|2249->654|2289->656|2323->663|2414->727|2429->733|2474->757|2521->777|2535->782|2565->791|2635->831|2668->837|2757->899|2772->905|2816->928|2862->947|2876->952|2905->960|2962->1004|2989->1022|3029->1024|3062->1030|3134->1075|3149->1081|3196->1107|3272->1156|3286->1161|3319->1173|3401->1228|3415->1233|3447->1244|3488->1272|3516->1291|3556->1293|3589->1299|3661->1344|3676->1350|3722->1375|3798->1424|3812->1429|3843->1439|3925->1494|3939->1499|3970->1509|4024->1537|4058->1541|4079->1544|4098->1554|4138->1556|4168->1560|4187->1570|4227->1572|4258->1577|4272->1582|4320->1609|4351->1613|4388->1623|4403->1629|4435->1640|4480->1658|4494->1663|4522->1670|4560->1678|4593->1681|4621->1682
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|35->4|36->5|36->5|36->5|37->6|37->6|37->6|38->7|38->7|38->7|38->7|38->7|38->7|39->8|39->8|39->8|39->8|39->8|40->9|41->10|41->10|41->10|41->11|41->11|41->11|42->12|42->12|42->12|43->13|45->15|45->15|45->15|46->16|46->16|46->16|47->17|48->18|50->20|50->20|50->20|51->21|51->21|51->21|52->23|52->23|52->23|53->24|53->24|53->24|53->24|54->25|54->25|54->25|55->26|55->26|55->26|57->29|57->29|57->29|58->30|58->30|58->30|58->30|59->31|59->31|59->31|60->32|60->32|60->32|62->35|63->36|64->37|64->37|64->37|65->38|65->38|65->38|66->39|66->39|66->39|67->40|67->40|67->40|67->40|67->40|67->40|67->40|68->41|69->42|70->43
                  -- GENERATED --
              */
          