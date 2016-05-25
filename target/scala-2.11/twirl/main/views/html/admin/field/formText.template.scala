
package views.html.admin.field

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object formText_Scope0 {
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

class formText extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,Field,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(label: String , field: Field)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.55*/("""

"""),format.raw/*3.1*/("""<div class="form-group">
	<label for=""""),_display_(/*4.15*/field/*4.20*/.id),format.raw/*4.23*/("""">"""),_display_(/*4.26*/label),format.raw/*4.31*/("""</label>
	<input
	class="form-control"
	type="text"
	name=""""),_display_(/*8.9*/field/*8.14*/.name),format.raw/*8.19*/(""""
	id=""""),_display_(/*9.7*/field/*9.12*/.id),format.raw/*9.15*/(""""
	value=""""),_display_(/*10.10*/field/*10.15*/.value),format.raw/*10.21*/(""""
	placeholder="Enter ..."/>
	"""),_display_(/*12.3*/errMsg(field)),format.raw/*12.16*/("""
"""),format.raw/*13.1*/("""</div>"""))
      }
    }
  }

  def render(label:String,field:Field,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(label,field)(ctx)

  def f:((String,Field) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (label,field) => (ctx) => apply(label,field)(ctx)

  def ref: this.type = this

}


}

/**/
object formText extends formText_Scope0.formText
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/admin/field/formText.scala.html
                  HASH: 5d7ad95d52bd5bf609ba867671f2341dc1119e90
                  MATRIX: 831->1|979->54|1007->56|1072->95|1085->100|1108->103|1137->106|1162->111|1247->171|1260->176|1285->181|1318->189|1331->194|1354->197|1392->208|1406->213|1433->219|1490->250|1524->263|1552->264
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|35->4|35->4|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|44->13
                  -- GENERATED --
              */
          