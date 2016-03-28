
package views.html.user

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

class mini extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template9[User,Boolean,Boolean,Option[lila.relation.Relation],Option[lila.relation.Relation],Option[lila.relation.Relation],Int,Int,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(u: User, blocked: Boolean, followable: Boolean, rel: Option[lila.relation.Relation], makeFriend: Option[lila.relation.Relation], friend: Option[lila.relation.Relation], nbFollower: Int, nbFriends: Int)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.227*/("""
"""),format.raw/*2.1*/("""<div class="mini">

  <div class="ui header">
    """),_display_(/*5.6*/{
      if(u.avatar.length > 0) {
        Html(s"<img src=/getimage/thumb/${u.avatar} />")
      } else {
        <img src="/assets/avatar/2.jpg"/>
      }
    }),format.raw/*11.6*/("""
    """),format.raw/*12.5*/("""<div class="content">
      <a class="route" href="""),_display_(/*13.30*/{"/@/" + u.username}),format.raw/*13.50*/(""">"""),_display_(/*13.52*/u/*13.53*/.name),format.raw/*13.58*/("""</a>
      <div class="sub header"><a class="route" href="""),_display_(/*14.54*/{"/@/" + u.username}),format.raw/*14.74*/(""">@"""),_display_(/*14.78*/{u.username}),format.raw/*14.90*/("""</a></div>
    </div>
  </div>


"""),_display_(/*19.2*/ctx/*19.5*/.userId.map/*19.16*/ { myId =>_display_(Seq[Any](format.raw/*19.26*/("""

    """),format.raw/*21.5*/("""<table class="actions">
      <tbody>
        <tr>
          """),_display_(/*24.12*/if(!blocked)/*24.24*/ {_display_(Seq[Any](format.raw/*24.26*/("""
            """),format.raw/*25.21*/("""
              """),format.raw/*26.85*/("""
                """),format.raw/*27.55*/("""
              """),format.raw/*28.23*/("""
            """),format.raw/*29.22*/("""
            """),format.raw/*30.21*/("""
              """),format.raw/*31.85*/("""
                """),format.raw/*32.53*/("""
              """),format.raw/*33.23*/("""
            """),format.raw/*34.22*/("""
          """)))}),format.raw/*35.12*/("""
          """),format.raw/*36.11*/("""<td class="relation_actions">"""),_display_(/*36.41*/relation/*36.49*/.mini(u.id, blocked, followable, rel, makeFriend, friend,  myId == u.id, nbFollower, nbFriends)),format.raw/*36.144*/("""</td>
        </tr>
      </tbody>
    </table>
""")))}),format.raw/*40.2*/("""
"""),format.raw/*41.1*/("""</div>"""))
      }
    }
  }

  def render(u:User,blocked:Boolean,followable:Boolean,rel:Option[lila.relation.Relation],makeFriend:Option[lila.relation.Relation],friend:Option[lila.relation.Relation],nbFollower:Int,nbFriends:Int,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(u,blocked,followable,rel,makeFriend,friend,nbFollower,nbFriends)(ctx)

  def f:((User,Boolean,Boolean,Option[lila.relation.Relation],Option[lila.relation.Relation],Option[lila.relation.Relation],Int,Int) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (u,blocked,followable,rel,makeFriend,friend,nbFollower,nbFriends) => (ctx) => apply(u,blocked,followable,rel,makeFriend,friend,nbFollower,nbFriends)(ctx)

  def ref: this.type = this

}


}

/**/
object mini extends mini_Scope0.mini
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/user/mini.scala.html
                  HASH: 68ae37ffdca807a9f32061124af16add130f7494
                  MATRIX: 925->1|1246->226|1273->227|1349->278|1530->439|1562->444|1640->495|1681->515|1710->517|1720->518|1746->523|1831->581|1872->601|1902->605|1935->617|1995->651|2006->654|2026->665|2074->675|2107->681|2196->743|2217->755|2257->757|2298->778|2341->863|2386->918|2429->941|2470->963|2511->984|2554->1069|2599->1122|2642->1145|2683->1167|2726->1179|2765->1190|2822->1220|2839->1228|2956->1323|3035->1372|3063->1373
                  LINES: 27->1|32->1|33->2|36->5|42->11|43->12|44->13|44->13|44->13|44->13|44->13|45->14|45->14|45->14|45->14|50->19|50->19|50->19|50->19|52->21|55->24|55->24|55->24|56->25|57->26|58->27|59->28|60->29|61->30|62->31|63->32|64->33|65->34|66->35|67->36|67->36|67->36|67->36|71->40|72->41
                  -- GENERATED --
              */
          