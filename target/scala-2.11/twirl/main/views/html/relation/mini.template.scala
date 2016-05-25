
package views.html.relation

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object mini_Scope0 {
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

class mini extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template10[String,Boolean,Boolean,Option[lila.relation.Relation],Option[lila.relation.Relation],Option[lila.relation.Relation],Boolean,Int,Int,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userId: String, blocked: Boolean, followable: Boolean, relation: Option[lila.relation.Relation] = None, makeFriend: Option[lila.relation.Relation] = None, friend: Option[lila.relation.Relation] = None, isSelf: Boolean = false, nbFollower : Int = 0, nbFriends: Int = 0)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.294*/("""

"""),_display_(/*3.2*/if(!isSelf)/*3.13*/ {_display_(Seq[Any](format.raw/*3.15*/("""

  """),_display_(/*5.4*/relation/*5.12*/ match/*5.18*/ {/*6.5*/case None if followable && !blocked =>/*6.43*/ {_display_(Seq[Any](format.raw/*6.45*/("""
      """),format.raw/*7.117*/("""
      """),format.raw/*8.49*/("""
      """),format.raw/*9.17*/("""
      """),format.raw/*10.15*/("""
      """),format.raw/*11.7*/("""<a class="tiny ui  basic animated relation button hint--bottom hover_text custom" tabindex="0" href=""""),_display_(/*11.109*/routes/*11.115*/.Relation.follow(userId)),format.raw/*11.139*/("""?mini=1">
        <span class="visible content">"""),_display_(/*12.40*/nbFollower),format.raw/*12.50*/(""" """),format.raw/*12.51*/("""Follower</span>
        <span class="hidden content">
          <i class="thumbs outline up icon"></i>
          Follow
        </span>
      </a>
    """)))}/*19.5*/case Some(true) =>/*19.23*/ {_display_(Seq[Any](format.raw/*19.25*/("""
      """),format.raw/*20.119*/("""
      """),format.raw/*21.41*/("""
      """),format.raw/*22.20*/("""
      """),format.raw/*23.15*/("""
      """),format.raw/*24.7*/("""<a class="tiny ui blue basic animated relation button hint--bottom hover_text custom" tabindex="0" href=""""),_display_(/*24.113*/routes/*24.119*/.Relation.unfollow(userId)),format.raw/*24.145*/("""?mini=1">
        <span class="visible content">"""),_display_(/*25.40*/nbFollower),format.raw/*25.50*/(""" """),format.raw/*25.51*/("""Follower</span>
        <span class="hidden content">
          <i class="thumbs up icon"></i>
          Following
        </span>
      </a>
    """)))}/*32.5*/case Some(false) =>/*32.24*/ {_display_(Seq[Any](format.raw/*32.26*/("""
      """),format.raw/*33.7*/("""<a class="relation button hint--bottom custom" data-hint=""""),_display_(/*33.66*/trans/*33.71*/.unblock()),format.raw/*33.81*/("""" class="hover_text" href=""""),_display_(/*33.109*/routes/*33.115*/.Relation.unblock(userId)),format.raw/*33.140*/("""?mini=1">
        <span data-icon="k">&nbsp; """),_display_(/*34.37*/trans/*34.42*/.blocked()),format.raw/*34.52*/("""</span>
      </a>
    """)))}/*37.5*/case _ =>/*37.14*/ {_display_(Seq[Any](format.raw/*37.16*/("""
    """)))}}),format.raw/*39.4*/("""

  """),_display_(/*41.4*/friend/*41.10*/ match/*41.16*/ {/*43.5*/case None if followable && !blocked =>/*43.43*/ {_display_(Seq[Any](format.raw/*43.45*/("""
      """),_display_(/*44.8*/makeFriend/*44.18*/ match/*44.24*/ {/*45.9*/case None if followable && !blocked =>/*45.47*/ {_display_(Seq[Any](format.raw/*45.49*/("""
          """),format.raw/*46.11*/("""<a class="tiny ui  basic animated relation button hint--bottom hover_text custom" tabindex="0" href=""""),_display_(/*46.113*/routes/*46.119*/.Relation.request(userId)),format.raw/*46.144*/("""?mini=1">
            <span class="visible content">"""),_display_(/*47.44*/nbFriends),format.raw/*47.53*/(""" """),format.raw/*47.54*/("""Friends</span>
            <span class="hidden content">
              <i class="thumbs up icon"></i>
              Add Friend
            </span>
          </a>
        """)))}/*54.9*/case Some(true) =>/*54.27*/ {_display_(Seq[Any](format.raw/*54.29*/("""
          """),format.raw/*55.11*/("""<a class="tiny ui olive basic animated relation button hint--bottom hover_text custom" tabindex="0" href=""""),_display_(/*55.118*/routes/*55.124*/.Relation.unrequest(userId)),format.raw/*55.151*/("""?mini=1">
            <span class="visible content">"""),_display_(/*56.44*/nbFriends),format.raw/*56.53*/(""" """),format.raw/*56.54*/("""Friends</span>
            <span class="hidden content">
              <i class="thumbs up icon"></i>
              Cancel Request
            </span>
          </a>
        """)))}/*63.9*/case _ =>/*63.18*/ {_display_(Seq[Any](format.raw/*63.20*/("""
        """)))}}),format.raw/*65.8*/("""
    """)))}/*68.5*/case Some(true) =>/*68.23*/ {_display_(Seq[Any](format.raw/*68.25*/("""
      """),format.raw/*69.7*/("""<a class="tiny ui blue basic animated relation button hint--bottom hover_text custom unfriend" tabindex="0"
        href=""""),_display_(/*70.16*/routes/*70.22*/.Relation.unfriend(userId)),format.raw/*70.48*/("""?mini=1"
      >
        <span class="visible content">"""),_display_(/*72.40*/nbFriends),format.raw/*72.49*/(""" """),format.raw/*72.50*/("""Friends</span>
        <span class="hidden content">
          <i class="remove user icon"></i>
          Unfriend
        </span>
      </a>
    """)))}/*80.5*/case _ =>/*80.14*/ {_display_(Seq[Any](format.raw/*80.16*/("""
    """)))}}),format.raw/*82.4*/("""


""")))}/*85.3*/else/*85.8*/{_display_(Seq[Any](format.raw/*85.9*/("""
  """),format.raw/*86.3*/("""<a class="tiny ui  basic animated  relation button hint--bottom hover_text custom" tabindex="0" href="#">
    <span class="visible content">"""),_display_(/*87.36*/nbFollower),format.raw/*87.46*/(""" """),format.raw/*87.47*/("""Folowers</span>
    <span class="hidden content">
      View Followers
    </span>
  </a>

  <a class="tiny ui  basic animated relation button hint--bottom hover_text custom" tabindex="0" href="#">
    <span class="visible content">"""),_display_(/*94.36*/nbFriends),format.raw/*94.45*/(""" """),format.raw/*94.46*/("""Friends</span>
    <span class="hidden content">
      View Friends
    </span>
  </a>

""")))}))
      }
    }
  }

  def render(userId:String,blocked:Boolean,followable:Boolean,relation:Option[lila.relation.Relation],makeFriend:Option[lila.relation.Relation],friend:Option[lila.relation.Relation],isSelf:Boolean,nbFollower:Int,nbFriends:Int,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(userId,blocked,followable,relation,makeFriend,friend,isSelf,nbFollower,nbFriends)(ctx)

  def f:((String,Boolean,Boolean,Option[lila.relation.Relation],Option[lila.relation.Relation],Option[lila.relation.Relation],Boolean,Int,Int) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (userId,blocked,followable,relation,makeFriend,friend,isSelf,nbFollower,nbFriends) => (ctx) => apply(userId,blocked,followable,relation,makeFriend,friend,isSelf,nbFollower,nbFriends)(ctx)

  def ref: this.type = this

}


}

/**/
object mini extends mini_Scope0.mini
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:26 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/relation/mini.scala.html
                  HASH: fafae4836317c58c891527347bd2d3e0a05e8ba8
                  MATRIX: 940->1|1328->293|1356->296|1375->307|1414->309|1444->314|1460->322|1474->328|1483->335|1529->373|1568->375|1603->492|1637->541|1671->558|1706->573|1740->580|1870->682|1886->688|1932->712|2008->761|2039->771|2068->772|2238->929|2265->947|2305->949|2341->1068|2376->1109|2411->1129|2446->1144|2480->1151|2614->1257|2630->1263|2678->1289|2754->1338|2785->1348|2814->1349|2979->1501|3007->1520|3047->1522|3081->1529|3167->1588|3181->1593|3212->1603|3268->1631|3284->1637|3331->1662|3404->1708|3418->1713|3449->1723|3491->1752|3509->1761|3549->1763|3586->1773|3617->1778|3632->1784|3647->1790|3657->1798|3704->1836|3744->1838|3778->1846|3797->1856|3812->1862|3822->1873|3869->1911|3909->1913|3948->1924|4078->2026|4094->2032|4141->2057|4221->2110|4251->2119|4280->2120|4469->2300|4496->2318|4536->2320|4575->2331|4710->2438|4726->2444|4775->2471|4855->2524|4885->2533|4914->2534|5107->2718|5125->2727|5165->2729|5206->2747|5230->2759|5257->2777|5297->2779|5331->2786|5481->2909|5496->2915|5543->2941|5626->2997|5656->3006|5685->3007|5850->3160|5868->3169|5908->3171|5945->3181|5967->3186|5979->3191|6017->3192|6047->3195|6215->3336|6246->3346|6275->3347|6535->3580|6565->3589|6594->3590
                  LINES: 27->1|32->1|34->3|34->3|34->3|36->5|36->5|36->5|36->6|36->6|36->6|37->7|38->8|39->9|40->10|41->11|41->11|41->11|41->11|42->12|42->12|42->12|48->19|48->19|48->19|49->20|50->21|51->22|52->23|53->24|53->24|53->24|53->24|54->25|54->25|54->25|60->32|60->32|60->32|61->33|61->33|61->33|61->33|61->33|61->33|61->33|62->34|62->34|62->34|64->37|64->37|64->37|65->39|67->41|67->41|67->41|67->43|67->43|67->43|68->44|68->44|68->44|68->45|68->45|68->45|69->46|69->46|69->46|69->46|70->47|70->47|70->47|76->54|76->54|76->54|77->55|77->55|77->55|77->55|78->56|78->56|78->56|84->63|84->63|84->63|85->65|86->68|86->68|86->68|87->69|88->70|88->70|88->70|90->72|90->72|90->72|96->80|96->80|96->80|97->82|100->85|100->85|100->85|101->86|102->87|102->87|102->87|109->94|109->94|109->94
                  -- GENERATED --
              */
          