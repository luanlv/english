
package views.html.admin.field

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object formTextArea_Scope0 {
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

class formTextArea extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,Field,Int,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(label: String , field: Field, line: Int = 0)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.70*/("""

"""),format.raw/*3.1*/("""<div class="form-group">
	<label for=""""),_display_(/*4.15*/field/*4.20*/.id),format.raw/*4.23*/("""">"""),_display_(/*4.26*/label),format.raw/*4.31*/("""</label>
	<textarea
	class="form-control"
	"""),_display_(/*7.3*/if(line > 0)/*7.15*/{_display_(Seq[Any](format.raw/*7.16*/(""" """),format.raw/*7.17*/("""rows=""""),_display_(/*7.24*/line),format.raw/*7.28*/("""" """)))}),format.raw/*7.31*/("""
	"""),format.raw/*8.2*/("""name=""""),_display_(/*8.9*/field/*8.14*/.name),format.raw/*8.19*/(""""
	id=""""),_display_(/*9.7*/field/*9.12*/.id),format.raw/*9.15*/(""""
	placeholder="Enter ...">"""),_display_(/*10.27*/field/*10.32*/.value),format.raw/*10.38*/("""</textarea>
	"""),_display_(/*11.3*/errMsg(field)),format.raw/*11.16*/("""
"""),format.raw/*12.1*/("""</div>

"""))
      }
    }
  }

  def render(label:String,field:Field,line:Int,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(label,field,line)(ctx)

  def f:((String,Field,Int) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (label,field,line) => (ctx) => apply(label,field,line)(ctx)

  def ref: this.type = this

}


}

/**/
object formTextArea extends formTextArea_Scope0.formTextArea
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/admin/field/formTextArea.scala.html
                  HASH: ee53446e973ca6d1923466c2508b5a5042728a9f
                  MATRIX: 843->1|1006->69|1034->71|1099->110|1112->115|1135->118|1164->121|1189->126|1258->170|1278->182|1316->183|1344->184|1377->191|1401->195|1434->198|1462->200|1494->207|1507->212|1532->217|1565->225|1578->230|1601->233|1656->261|1670->266|1697->272|1737->286|1771->299|1799->300
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|35->4|35->4|38->7|38->7|38->7|38->7|38->7|38->7|38->7|39->8|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|42->11|42->11|43->12
                  -- GENERATED --
              */
          