
package views.html.index.other

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object cssDone_Scope0 {
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

class cssDone extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<script >
	var next = 0;
	function CSSDone(how) """),format.raw/*3.24*/("""{"""),format.raw/*3.25*/("""
		"""),format.raw/*4.3*/("""logme('zOMG, CSS #' + next + ' is done: ' + how);
	"""),format.raw/*5.2*/("""}"""),format.raw/*5.3*/("""

	"""),format.raw/*7.2*/("""function loadCSS() """),format.raw/*7.21*/("""{"""),format.raw/*7.22*/("""
		"""),format.raw/*8.3*/("""var url = "/assets/semantic/semantic.min.css?v=3",
		head = document.getElementsByTagName('head')[0];
		link = document.createElement('link');

		link.type = "text/css";
		link.rel = "stylesheet";
		link.href = url;

		// MAGIC
		// #1
		link.onload = function () """),format.raw/*18.29*/("""{"""),format.raw/*18.30*/("""
			"""),format.raw/*19.4*/("""CSSDone('onload listener');
		"""),format.raw/*20.3*/("""}"""),format.raw/*20.4*/("""
		"""),format.raw/*21.3*/("""// #2
		if (link.addEventListener) """),format.raw/*22.30*/("""{"""),format.raw/*22.31*/("""
			"""),format.raw/*23.4*/("""link.addEventListener('load', function() """),format.raw/*23.45*/("""{"""),format.raw/*23.46*/("""
				"""),format.raw/*24.5*/("""CSSDone("DOM's load event");
			"""),format.raw/*25.4*/("""}"""),format.raw/*25.5*/(""", false);
		"""),format.raw/*26.3*/("""}"""),format.raw/*26.4*/("""
		"""),format.raw/*27.3*/("""// #3
		link.onreadystatechange = function() """),format.raw/*28.40*/("""{"""),format.raw/*28.41*/("""
			"""),format.raw/*29.4*/("""var state = link.readyState;
			if (state === 'loaded' || state === 'complete') """),format.raw/*30.52*/("""{"""),format.raw/*30.53*/("""
				"""),format.raw/*31.5*/("""link.onreadystatechange = null;
				CSSDone("onreadystatechange");
			"""),format.raw/*33.4*/("""}"""),format.raw/*33.5*/("""
		"""),format.raw/*34.3*/("""}"""),format.raw/*34.4*/(""";

		var cssnum = document.styleSheets.length;
		var ti = setInterval(function() """),format.raw/*37.35*/("""{"""),format.raw/*37.36*/("""
			"""),format.raw/*38.4*/("""if (document.styleSheets.length > cssnum) """),format.raw/*38.46*/("""{"""),format.raw/*38.47*/("""
				"""),format.raw/*39.5*/("""CSSDone('listening to styleSheets.length change');
				console.log("doadload complete!")
				clearInterval(ti);
			"""),format.raw/*42.4*/("""}"""),format.raw/*42.5*/("""
		"""),format.raw/*43.3*/("""}"""),format.raw/*43.4*/(""", 10);

		head.appendChild(link);


		var isFF = /Firefox/.test(navigator.userAgent);
		if (!isFF) """),format.raw/*49.14*/("""{"""),format.raw/*49.15*/("""
			"""),format.raw/*50.4*/("""return;
		"""),format.raw/*51.3*/("""}"""),format.raw/*51.4*/("""

		"""),format.raw/*53.3*/("""var style = document.createElement('style');
		style.textContent = '@import "' + url + '"';

		var fi = setInterval(function() """),format.raw/*56.35*/("""{"""),format.raw/*56.36*/("""
			"""),format.raw/*57.4*/("""try """),format.raw/*57.8*/("""{"""),format.raw/*57.9*/("""
				"""),format.raw/*58.5*/("""style.sheet.cssRules;
				CSSDone('listening to @import-ed cssRules');
				clearInterval(fi);
			"""),format.raw/*61.4*/("""}"""),format.raw/*61.5*/(""" """),format.raw/*61.6*/("""catch (e)"""),format.raw/*61.15*/("""{"""),format.raw/*61.16*/("""}"""),format.raw/*61.17*/("""
		"""),format.raw/*62.3*/("""}"""),format.raw/*62.4*/(""", 10);

		head.appendChild(style);

	"""),format.raw/*66.2*/("""}"""),format.raw/*66.3*/("""


	"""),format.raw/*69.2*/("""loadCSS()
</script>"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object cssDone extends cssDone_Scope0.cssDone
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/index/other/cssDone.scala.html
                  HASH: a32cc86a7ad746b47296f13c876d1fd40fef184c
                  MATRIX: 897->0|972->48|1000->49|1029->52|1106->103|1133->104|1162->107|1208->126|1236->127|1265->130|1557->394|1586->395|1617->399|1674->429|1702->430|1732->433|1795->468|1824->469|1855->473|1924->514|1953->515|1985->520|2044->552|2072->553|2111->565|2139->566|2169->569|2242->614|2271->615|2302->619|2410->699|2439->700|2471->705|2568->775|2596->776|2626->779|2654->780|2763->861|2792->862|2823->866|2893->908|2922->909|2954->914|3096->1029|3124->1030|3154->1033|3182->1034|3309->1133|3338->1134|3369->1138|3406->1148|3434->1149|3465->1153|3620->1281|3649->1282|3680->1286|3711->1290|3739->1291|3771->1296|3895->1394|3923->1395|3951->1396|3988->1405|4017->1406|4046->1407|4076->1410|4104->1411|4168->1448|4196->1449|4227->1453
                  LINES: 32->1|34->3|34->3|35->4|36->5|36->5|38->7|38->7|38->7|39->8|49->18|49->18|50->19|51->20|51->20|52->21|53->22|53->22|54->23|54->23|54->23|55->24|56->25|56->25|57->26|57->26|58->27|59->28|59->28|60->29|61->30|61->30|62->31|64->33|64->33|65->34|65->34|68->37|68->37|69->38|69->38|69->38|70->39|73->42|73->42|74->43|74->43|80->49|80->49|81->50|82->51|82->51|84->53|87->56|87->56|88->57|88->57|88->57|89->58|92->61|92->61|92->61|92->61|92->61|92->61|93->62|93->62|97->66|97->66|100->69
                  -- GENERATED --
              */
          