
package views.html.monitor

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object monitor_Scope0 {
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

class monitor extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
  <head>
    <title>Monitor</title>
    <meta name="viewport" content="width=device-width, user-scalable=no" />
    <link rel="shortcut icon" href=""""),_display_(/*7.38*/routes/*7.44*/.Assets.at("favicon.ico")),format.raw/*7.69*/("""" type="image/x-icon" />
    """),_display_(/*8.6*/cssTag("monitor.css")),format.raw/*8.27*/("""
  """),format.raw/*9.3*/("""</head>
  <body>
    <a class="back" href="#">&lt; back to Website</a>
    <h1>Reactor <span class="down">DOWN</span><span class="up">OPERATIONAL</span></h1>
    <div id="monitors" class="clearfix">
    </div>
    <script>
      window.document.getElementById("shutdown").onclick = function() """),format.raw/*16.71*/("""{"""),format.raw/*16.72*/("""
        """),format.raw/*17.9*/("""alert("Was worth trying. I guess.");
      """),format.raw/*18.7*/("""}"""),format.raw/*18.8*/(""";
    </script>
  </body>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
  """),_display_(/*22.4*/jsTag("deps.min.js")),format.raw/*22.24*/("""
	"""),format.raw/*23.2*/("""<script src="http://code.highcharts.com/4.1.4/highcharts.js"></script>
  """),_display_(/*24.4*/jsAt("vendor/highcharts4/themes/gray.js")),format.raw/*24.45*/("""
  """),_display_(/*25.4*/jsTag("monitor2.js")),format.raw/*25.24*/("""
"""),format.raw/*26.1*/("""</html>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object monitor extends monitor_Scope0.monitor
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:26 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/monitor/monitor.scala.html
                  HASH: b2dee3fad8d5ccc8ee99b710c55d7cc843cf6b97
                  MATRIX: 804->1|900->3|927->4|1125->176|1139->182|1184->207|1239->237|1280->258|1309->261|1630->554|1659->555|1695->564|1765->607|1793->608|1937->726|1978->746|2007->748|2107->822|2169->863|2199->867|2240->887|2268->888
                  LINES: 27->1|32->1|33->2|38->7|38->7|38->7|39->8|39->8|40->9|47->16|47->16|48->17|49->18|49->18|53->22|53->22|54->23|55->24|55->24|56->25|56->25|57->26
                  -- GENERATED --
              */
          