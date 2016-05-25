
package views.html.notification

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object deploy_Scope0 {
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

class deploy extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(on: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.14*/("""

"""),_display_(/*3.2*/notification/*3.14*/.view("deploy_" + on, closable = false, glow = true)/*3.66*/ {_display_(Seq[Any](format.raw/*3.68*/("""
"""),_display_(/*4.2*/on/*4.4*/ match/*4.10*/ {/*5.1*/case "pre" =>/*5.14*/ {_display_(Seq[Any](format.raw/*5.16*/("""
"""),format.raw/*6.1*/("""<p class="is3" data-icon="j">
  The site will be updated in a few minutes! Fasten your seatbelt.
</p>
""")))}/*10.1*/case "post" =>/*10.15*/ {_display_(Seq[Any](format.raw/*10.17*/("""
"""),format.raw/*11.1*/("""<p class="is3" data-icon="j">
  The site is being updated! Brace for impact.
</p>
""")))}}),format.raw/*15.2*/("""
""")))}),format.raw/*16.2*/("""
"""))
      }
    }
  }

  def render(on:String): play.twirl.api.HtmlFormat.Appendable = apply(on)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (on) => apply(on)

  def ref: this.type = this

}


}

/**/
object deploy extends deploy_Scope0.deploy
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:26 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/notification/deploy.scala.html
                  HASH: d8ac6b55e4dbd4069a837b2320beea5ad78308a7
                  MATRIX: 814->1|921->13|949->16|969->28|1029->80|1068->82|1095->84|1104->86|1118->92|1127->95|1148->108|1187->110|1214->111|1335->215|1358->229|1398->231|1426->232|1540->317|1572->319
                  LINES: 27->1|32->1|34->3|34->3|34->3|34->3|35->4|35->4|35->4|35->5|35->5|35->5|36->6|39->10|39->10|39->10|40->11|43->15|44->16
                  -- GENERATED --
              */
          