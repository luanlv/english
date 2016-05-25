
package views.html.admin.field

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object formInfo_Scope0 {
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

class formInfo extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[String,Field,List[String],Boolean,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(label: String , field: Field, list: List[String], zero: Boolean = false)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.98*/("""

"""),format.raw/*3.1*/("""<div class="form-group">
	<label for=""""),_display_(/*4.15*/field/*4.20*/.id),format.raw/*4.23*/("""">"""),_display_(/*4.26*/label),format.raw/*4.31*/("""</label>
	<select name=""""),_display_(/*5.17*/field/*5.22*/.name),format.raw/*5.27*/("""" id=""""),_display_(/*5.34*/field/*5.39*/.id),format.raw/*5.42*/("""" class="form-control">
		"""),_display_(/*6.4*/if(zero)/*6.12*/{_display_(Seq[Any](format.raw/*6.13*/(""" """),format.raw/*6.14*/("""<option value="">Select</option> """)))}),format.raw/*6.48*/("""
		"""),_display_(/*7.4*/for(item <- list) yield /*7.21*/{_display_(Seq[Any](format.raw/*7.22*/("""<option value=""""),_display_(/*7.38*/item),format.raw/*7.42*/("""" """),_display_(/*7.45*/if(field.value == Option(item))/*7.76*/{_display_(Seq[Any](format.raw/*7.77*/(""" """),format.raw/*7.78*/("""selected """)))}),format.raw/*7.88*/(""">"""),_display_(/*7.90*/item),format.raw/*7.94*/("""</option>""")))}),format.raw/*7.104*/("""
	"""),format.raw/*8.2*/("""</select>
</div>"""))
      }
    }
  }

  def render(label:String,field:Field,list:List[String],zero:Boolean,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(label,field,list,zero)(ctx)

  def f:((String,Field,List[String],Boolean) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (label,field,list,zero) => (ctx) => apply(label,field,list,zero)(ctx)

  def ref: this.type = this

}


}

/**/
object formInfo extends formInfo_Scope0.formInfo
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/admin/field/formInfo.scala.html
                  HASH: 4a98fba855598ad58221675fe215e48b3e7ed460
                  MATRIX: 852->1|1043->97|1071->99|1136->138|1149->143|1172->146|1201->149|1226->154|1277->179|1290->184|1315->189|1348->196|1361->201|1384->204|1436->231|1452->239|1490->240|1518->241|1582->275|1611->279|1643->296|1681->297|1723->313|1747->317|1776->320|1815->351|1853->352|1881->353|1921->363|1949->365|1973->369|2014->379|2042->381
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|35->4|35->4|36->5|36->5|36->5|36->5|36->5|36->5|37->6|37->6|37->6|37->6|37->6|38->7|38->7|38->7|38->7|38->7|38->7|38->7|38->7|38->7|38->7|38->7|38->7|38->7|39->8
                  -- GENERATED --
              */
          