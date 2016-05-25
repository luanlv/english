
package views.html.admin.field

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object formTextWithoutLabel_Scope0 {
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

class formTextWithoutLabel extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Field,String,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(field: Field, placeHolder: String)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.60*/("""

	"""),format.raw/*3.2*/("""<input
	class="form-control"
	type="text"
	name=""""),_display_(/*6.9*/field/*6.14*/.name),format.raw/*6.19*/(""""
	id=""""),_display_(/*7.7*/field/*7.12*/.id),format.raw/*7.15*/(""""
	value=""""),_display_(/*8.10*/field/*8.15*/.value),format.raw/*8.21*/(""""
	placeholder=""""),_display_(/*9.16*/placeHolder),format.raw/*9.27*/(""""/>
	"""),_display_(/*10.3*/errMsg(field)))
      }
    }
  }

  def render(field:Field,placeHolder:String,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(field,placeHolder)(ctx)

  def f:((Field,String) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (field,placeHolder) => (ctx) => apply(field,placeHolder)(ctx)

  def ref: this.type = this

}


}

/**/
object formTextWithoutLabel extends formTextWithoutLabel_Scope0.formTextWithoutLabel
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/admin/field/formTextWithoutLabel.scala.html
                  HASH: 0721523b2a8bbbda15132e6def6dcef454980f42
                  MATRIX: 855->1|1008->59|1037->62|1112->112|1125->117|1150->122|1183->130|1196->135|1219->138|1256->149|1269->154|1295->160|1338->177|1369->188|1401->194
                  LINES: 27->1|32->1|34->3|37->6|37->6|37->6|38->7|38->7|38->7|39->8|39->8|39->8|40->9|40->9|41->10
                  -- GENERATED --
              */
          