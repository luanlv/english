
package views.html.admin.field

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object formNumber_Scope0 {
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

class formNumber extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,Field,Context,play.twirl.api.HtmlFormat.Appendable] {

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
	value=""""),_display_(/*10.10*/field/*10.15*/.value.getOrElse(0)),format.raw/*10.34*/(""""
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
object formNumber extends formNumber_Scope0.formNumber
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/admin/field/formNumber.scala.html
                  HASH: 8d2b782124aca936422697f9c05b177239a1415c
                  MATRIX: 835->1|983->54|1011->56|1076->95|1089->100|1112->103|1141->106|1166->111|1251->171|1264->176|1289->181|1322->189|1335->194|1358->197|1396->208|1410->213|1450->232|1507->263|1541->276|1569->277
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|35->4|35->4|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|43->12|43->12|44->13
                  -- GENERATED --
              */
          