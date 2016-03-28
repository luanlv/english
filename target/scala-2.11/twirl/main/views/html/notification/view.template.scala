
package views.html.notification

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object view_Scope0 {
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

class view extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template6[String,Option[String],Boolean,Boolean,String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: String, from: Option[String] = None, closable: Boolean = true, glow: Boolean = false, cssClass: String = "")(html: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.127*/("""

"""),format.raw/*3.1*/("""<div id=""""),_display_(/*3.11*/id),format.raw/*3.13*/("""" class="notification"""),_display_(/*3.35*/if(glow)/*3.43*/ {_display_(Seq[Any](format.raw/*3.45*/(""" """),format.raw/*3.46*/("""glowing """)))}),format.raw/*3.55*/(""" """),_display_(/*3.57*/cssClass),format.raw/*3.65*/("""">
  """),_display_(/*4.4*/if(closable)/*4.16*/ {_display_(Seq[Any](format.raw/*4.18*/("""
  """),format.raw/*5.68*/("""
  """)))}),format.raw/*6.4*/("""
  """),_display_(/*7.4*/from/*7.8*/.map/*7.12*/ { user =>_display_(Seq[Any](format.raw/*7.22*/("""
   """),format.raw/*8.4*/("""userIdLink(user, none)
  """)))}),format.raw/*9.4*/("""
  """),format.raw/*10.3*/("""<div class="inner">"""),_display_(/*10.23*/html),format.raw/*10.27*/("""</div>
</div>
"""))
      }
    }
  }

  def render(id:String,from:Option[String],closable:Boolean,glow:Boolean,cssClass:String,html:Html): play.twirl.api.HtmlFormat.Appendable = apply(id,from,closable,glow,cssClass)(html)

  def f:((String,Option[String],Boolean,Boolean,String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (id,from,closable,glow,cssClass) => (html) => apply(id,from,closable,glow,cssClass)(html)

  def ref: this.type = this

}


}

/**/
object view extends view_Scope0.view
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/notification/view.scala.html
                  HASH: 5514a323d155f5e766433634dd76bcd7eb4ee6f7
                  MATRIX: 853->1|1074->126|1102->128|1138->138|1160->140|1208->162|1224->170|1263->172|1291->173|1330->182|1358->184|1386->192|1417->198|1437->210|1476->212|1506->280|1539->284|1568->288|1579->292|1591->296|1638->306|1668->310|1723->336|1753->339|1800->359|1825->363
                  LINES: 27->1|32->1|34->3|34->3|34->3|34->3|34->3|34->3|34->3|34->3|34->3|34->3|35->4|35->4|35->4|36->5|37->6|38->7|38->7|38->7|38->7|39->8|40->9|41->10|41->10|41->10
                  -- GENERATED --
              */
          