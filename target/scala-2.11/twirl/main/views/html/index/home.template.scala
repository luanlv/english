
package views.html.index

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object home_Scope0 {
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

class home extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Context,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit ctx: Context):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),format.raw/*3.1*/("""<!doctype html>
<html lang="en">
	<head>
		<meta name="fragment" content="!">
		<meta charset="UTF-8">
		<link rel="dns-prefetch" href="static.luanlv.info">
		<link rel="shortcut icon" href=""""),_display_(/*9.36*/routes/*9.42*/.Assets.at("favicon.ico")),format.raw/*9.67*/("""">
		<title>Demo</title>
		"""),_display_(/*11.4*/views/*11.9*/.html.index.other.headjs()),format.raw/*11.35*/("""
		"""),_display_(/*12.4*/views/*12.9*/.html.index.other.spinner()),format.raw/*12.36*/("""
		"""),format.raw/*13.3*/("""</head>

	<body
	id=""""),_display_(/*16.7*/ctx/*16.10*/.userId.getOrElse("")),format.raw/*16.31*/(""""
	name=""""),_display_(/*17.9*/ctx/*17.12*/.name.getOrElse("")),format.raw/*17.31*/(""""
	mv = """"),_display_(/*18.9*/mVersion(ctx.userId)),format.raw/*18.29*/(""""
    avatar = """"),_display_(/*19.16*/ctx/*19.19*/.avatar.getOrElse("")),format.raw/*19.40*/(""""
	style="background: #e8eaed"
	>
		<div class="loaderWr">
			<div class="loaderPos">
				<div class="spinner">
					<div class="rect1"></div>
					<div class="rect2"></div>
					<div class="rect3"></div>
					<div class="rect4"></div>
					<div class="rect5"></div>
				</div>
			</div>
		</div>
		<div id="nav"> </div>
		<div id="count"></div>
		<div id="app"> </div>
		<div id="footer"></div>
		<div id="rightContainer"></div>

		<div class="red"></div>
		<script>
				var static = "";
				if(document.domain === "localhost")"""),format.raw/*42.40*/("""{"""),format.raw/*42.41*/("""
					"""),format.raw/*43.6*/("""static = "";
				"""),format.raw/*44.5*/("""}"""),format.raw/*44.6*/(""" """),format.raw/*44.7*/("""else """),format.raw/*44.12*/("""{"""),format.raw/*44.13*/("""
"""),format.raw/*45.1*/("""//					static = "http://static.venglish.net"
					static = ""
				"""),format.raw/*47.5*/("""}"""),format.raw/*47.6*/("""
				"""),format.raw/*48.5*/("""head.load(static +'"""),_display_(/*48.25*/cssTagName("custom.min.css")),format.raw/*48.53*/("""',
						"https://cdnjs.cloudflare.com/ajax/libs/jquery-powertip/1.2.0/css/jquery.powertip.min.css",
//			  "/assets/semantic/semantic.min.css?v=3");
						"https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.1.8/semantic.min.css");
				//"https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.1.6/semantic.min.css");
				head.load(static + '"""),_display_(/*53.26*/jsTagName("vendor/jquery.min.js")),format.raw/*53.59*/("""')
				head.load("https://cdnjs.cloudflare.com/ajax/libs/jquery-powertip/1.2.0/jquery.powertip.min.js")
						//head.load("/assets/javascripts/vendor/jquery.min.js")
						.js(static + '"""),_display_(/*56.22*/jsTagName("jquery.cookie.min.js")),format.raw/*56.55*/("""')
						.js(static + '"""),_display_(/*57.22*/jsTagName("jquery.storageapi.min.js")),format.raw/*57.59*/("""')
						.js("https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.1.8/semantic.min.js")
						.js(static + '"""),_display_(/*59.22*/jsTagName("jquery.textarea_autosize.js")),format.raw/*59.62*/("""')
						.js(static + '"""),_display_(/*60.22*/jsTagName("attrchange.js")),format.raw/*60.48*/("""')
						.js(static + '"""),_display_(/*61.22*/jsTagName("moment.min.js")),format.raw/*61.48*/("""')
						.js(static + '"""),_display_(/*62.22*/jsTagName("mithril.js")),format.raw/*62.45*/("""')
						.js(static + '"""),_display_(/*63.22*/jsTagName("mithril-storage.js")),format.raw/*63.53*/("""')
						.js(static + '"""),_display_(/*64.22*/jsTagName("main.js")),format.raw/*64.42*/("""')
						.js("""),format.raw/*65.11*/("""{"""),format.raw/*65.12*/("""app: static + '"""),_display_(/*65.28*/jsTagName("app2.js")),format.raw/*65.48*/("""'"""),format.raw/*65.49*/("""}"""),format.raw/*65.50*/(""")
				head.ready('app', function() """),format.raw/*66.34*/("""{"""),format.raw/*66.35*/("""
					"""),format.raw/*67.6*/("""var f1 = function()"""),format.raw/*67.25*/("""{"""),format.raw/*67.26*/("""
						"""),format.raw/*68.7*/("""if(typeof(initRoute) == "function" && typeof($) == "function" && $('.red').css('color') != 'rgb(0, 0, 0)')"""),format.raw/*68.113*/("""{"""),format.raw/*68.114*/("""
							"""),format.raw/*69.8*/("""initRoute();
							initComponent();
						"""),format.raw/*71.7*/("""}"""),format.raw/*71.8*/(""" """),format.raw/*71.9*/("""else """),format.raw/*71.14*/("""{"""),format.raw/*71.15*/("""
							"""),format.raw/*72.8*/("""setTimeout(f1, 10)
						"""),format.raw/*73.7*/("""}"""),format.raw/*73.8*/("""
					"""),format.raw/*74.6*/("""}"""),format.raw/*74.7*/(""";
					f1()
				"""),format.raw/*76.5*/("""}"""),format.raw/*76.6*/(""");

		</script>


		<script>
			(function(i,s,o,g,r,a,m)"""),format.raw/*82.28*/("""{"""),format.raw/*82.29*/("""i['GoogleAnalyticsObject']=r;i[r]=i[r]||function()"""),format.raw/*82.79*/("""{"""),format.raw/*82.80*/("""
					"""),format.raw/*83.6*/("""(i[r].q=i[r].q||[]).push(arguments)"""),format.raw/*83.41*/("""}"""),format.raw/*83.42*/(""",i[r].l=1*new Date();a=s.createElement(o),
				m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			"""),format.raw/*85.4*/("""}"""),format.raw/*85.5*/(""")(window,document,'script','//www.google-analytics.com/analytics.js','ga');

			ga('create', 'UA-69170503-1', 'auto');
			ga('send', 'pageview');
		</script>

	</body>
</html>"""))
      }
    }
  }

  def render(ctx:Context): play.twirl.api.HtmlFormat.Appendable = apply()(ctx)

  def f:(() => (Context) => play.twirl.api.HtmlFormat.Appendable) = () => (ctx) => apply()(ctx)

  def ref: this.type = this

}


}

/**/
object home extends home_Scope0.home
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/index/home.scala.html
                  HASH: 9f932f3598f228d20effc91284cd4fb4d9c12f8b
                  MATRIX: 804->1|924->26|952->28|1170->220|1184->226|1229->251|1283->279|1296->284|1343->310|1373->314|1386->319|1434->346|1464->349|1512->371|1524->374|1566->395|1602->405|1614->408|1654->427|1690->437|1731->457|1775->474|1787->477|1829->498|2385->1026|2414->1027|2447->1033|2491->1050|2519->1051|2547->1052|2580->1057|2609->1058|2637->1059|2730->1125|2758->1126|2790->1131|2837->1151|2886->1179|3256->1522|3310->1555|3524->1742|3578->1775|3629->1799|3687->1836|3824->1946|3885->1986|3936->2010|3983->2036|4034->2060|4081->2086|4132->2110|4176->2133|4227->2157|4279->2188|4330->2212|4371->2232|4412->2245|4441->2246|4484->2262|4525->2282|4554->2283|4583->2284|4646->2319|4675->2320|4708->2326|4755->2345|4784->2346|4818->2353|4953->2459|4983->2460|5018->2468|5088->2511|5116->2512|5144->2513|5177->2518|5206->2519|5241->2527|5293->2552|5321->2553|5354->2559|5382->2560|5425->2576|5453->2577|5537->2633|5566->2634|5644->2684|5673->2685|5706->2691|5769->2726|5798->2727|5955->2857|5983->2858
                  LINES: 27->1|32->1|34->3|40->9|40->9|40->9|42->11|42->11|42->11|43->12|43->12|43->12|44->13|47->16|47->16|47->16|48->17|48->17|48->17|49->18|49->18|50->19|50->19|50->19|73->42|73->42|74->43|75->44|75->44|75->44|75->44|75->44|76->45|78->47|78->47|79->48|79->48|79->48|84->53|84->53|87->56|87->56|88->57|88->57|90->59|90->59|91->60|91->60|92->61|92->61|93->62|93->62|94->63|94->63|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|100->69|102->71|102->71|102->71|102->71|102->71|103->72|104->73|104->73|105->74|105->74|107->76|107->76|113->82|113->82|113->82|113->82|114->83|114->83|114->83|116->85|116->85
                  -- GENERATED --
              */
          