
package views.html.site

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object message_Scope0 {
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

class message extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,Boolean,Html,Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, back: Boolean = true)(message: Html)(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.77*/("""

"""),_display_(/*3.2*/site/*3.6*/.layout(title = title)/*3.28*/ {_display_(Seq[Any](format.raw/*3.30*/("""

"""),format.raw/*5.1*/("""<div class="content_box small_box">
  <div class="head">
    <h1>"""),_display_(/*7.10*/title),format.raw/*7.15*/("""</h1>
  </div>
  <br />
  <br />
  <p>
    """),_display_(/*12.6*/message),format.raw/*12.13*/("""
  """),format.raw/*13.3*/("""</p>
  <br />
  """),_display_(/*15.4*/if(back)/*15.12*/ {_display_(Seq[Any](format.raw/*15.14*/("""
  """),format.raw/*16.3*/("""<script>
    document.write('<a href="' + document.referrer + '">Go Back</a>');
  </script>
  """)))}),format.raw/*19.4*/("""
"""),format.raw/*20.1*/("""</div>
""")))}),format.raw/*21.2*/("""
"""))
      }
    }
  }

  def render(title:String,back:Boolean,message:Html,ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply(title,back)(message)(ctx)

  def f:((String,Boolean) => (Html) => (Context) => play.twirl.api.HtmlFormat.Appendable) = (title,back) => (message) => (ctx) => apply(title,back)(message)(ctx)

  def ref: this.type = this

}


}

/**/
object message extends message_Scope0.message
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/site/message.scala.html
                  HASH: ccae3b88f395703fd7c5f3fb17d68b32c636945a
                  MATRIX: 829->1|999->76|1027->79|1038->83|1068->105|1107->107|1135->109|1227->175|1252->180|1322->224|1350->231|1380->234|1423->251|1440->259|1480->261|1510->264|1635->359|1663->360|1701->368
                  LINES: 27->1|32->1|34->3|34->3|34->3|34->3|36->5|38->7|38->7|43->12|43->12|44->13|46->15|46->15|46->15|47->16|50->19|51->20|52->21
                  -- GENERATED --
              */
          