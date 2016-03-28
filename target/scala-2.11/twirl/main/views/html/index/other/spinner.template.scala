
package views.html.index.other

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object spinner_Scope0 {
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

class spinner extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<style>
body"""),format.raw/*2.5*/("""{"""),format.raw/*2.6*/("""
"""),format.raw/*3.1*/("""overflow-y: scroll;
"""),format.raw/*4.1*/("""}"""),format.raw/*4.2*/("""
"""),format.raw/*5.1*/(""".spinner """),format.raw/*5.10*/("""{"""),format.raw/*5.11*/("""
"""),format.raw/*6.1*/("""margin: 300px auto;
width: 600px;
height: 80px;
text-align: center;
font-size: 10px;
"""),format.raw/*11.1*/("""}"""),format.raw/*11.2*/("""

"""),format.raw/*13.1*/(""".spinner > div """),format.raw/*13.16*/("""{"""),format.raw/*13.17*/("""
"""),format.raw/*14.1*/("""background-color: #2185D0;
height: 100%;
width: 6px;
display: inline-block;

-webkit-animation: sk-stretchdelay 1.2s infinite ease-in-out;
animation: sk-stretchdelay 1.2s infinite ease-in-out;
"""),format.raw/*21.1*/("""}"""),format.raw/*21.2*/("""

"""),format.raw/*23.1*/(""".spinner .rect2 """),format.raw/*23.17*/("""{"""),format.raw/*23.18*/("""
"""),format.raw/*24.1*/("""-webkit-animation-delay: -1.1s;
animation-delay: -1.1s;
"""),format.raw/*26.1*/("""}"""),format.raw/*26.2*/("""

"""),format.raw/*28.1*/(""".spinner .rect3 """),format.raw/*28.17*/("""{"""),format.raw/*28.18*/("""
"""),format.raw/*29.1*/("""-webkit-animation-delay: -1.0s;
animation-delay: -1.0s;
"""),format.raw/*31.1*/("""}"""),format.raw/*31.2*/("""

"""),format.raw/*33.1*/(""".spinner .rect4 """),format.raw/*33.17*/("""{"""),format.raw/*33.18*/("""
"""),format.raw/*34.1*/("""-webkit-animation-delay: -0.9s;
animation-delay: -0.9s;
"""),format.raw/*36.1*/("""}"""),format.raw/*36.2*/("""

"""),format.raw/*38.1*/(""".spinner .rect5 """),format.raw/*38.17*/("""{"""),format.raw/*38.18*/("""
"""),format.raw/*39.1*/("""-webkit-animation-delay: -0.8s;
animation-delay: -0.8s;
"""),format.raw/*41.1*/("""}"""),format.raw/*41.2*/("""

	"""),format.raw/*43.2*/("""@-webkit-keyframes sk-stretchdelay """),format.raw/*43.38*/("""{"""),format.raw/*43.39*/("""
"""),format.raw/*44.1*/("""0%, 40%, 100% """),format.raw/*44.15*/("""{"""),format.raw/*44.16*/(""" """),format.raw/*44.17*/("""-webkit-transform: scaleY(0.4) """),format.raw/*44.48*/("""}"""),format.raw/*44.49*/("""
"""),format.raw/*45.1*/("""20% """),format.raw/*45.5*/("""{"""),format.raw/*45.6*/(""" """),format.raw/*45.7*/("""-webkit-transform: scaleY(1.0) """),format.raw/*45.38*/("""}"""),format.raw/*45.39*/("""
"""),format.raw/*46.1*/("""}"""),format.raw/*46.2*/("""

	"""),format.raw/*48.2*/("""@keyframes sk-stretchdelay """),format.raw/*48.30*/("""{"""),format.raw/*48.31*/("""
"""),format.raw/*49.1*/("""0%, 40%, 100% """),format.raw/*49.15*/("""{"""),format.raw/*49.16*/("""
"""),format.raw/*50.1*/("""transform: scaleY(0.4);
-webkit-transform: scaleY(0.4);
"""),format.raw/*52.1*/("""}"""),format.raw/*52.2*/("""  """),format.raw/*52.4*/("""20% """),format.raw/*52.8*/("""{"""),format.raw/*52.9*/("""
"""),format.raw/*53.1*/("""transform: scaleY(1.0);
-webkit-transform: scaleY(1.0);
"""),format.raw/*55.1*/("""}"""),format.raw/*55.2*/("""
"""),format.raw/*56.1*/("""}"""),format.raw/*56.2*/("""
"""),format.raw/*57.1*/("""</style>"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object spinner extends spinner_Scope0.spinner
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/index/other/spinner.scala.html
                  HASH: 99b05a4d38a1834571646a1a2627b5dd31c20421
                  MATRIX: 897->0|935->12|962->13|989->14|1035->34|1062->35|1089->36|1125->45|1153->46|1180->47|1292->132|1320->133|1349->135|1392->150|1421->151|1449->152|1669->345|1697->346|1726->348|1770->364|1799->365|1827->366|1910->422|1938->423|1967->425|2011->441|2040->442|2068->443|2151->499|2179->500|2208->502|2252->518|2281->519|2309->520|2392->576|2420->577|2449->579|2493->595|2522->596|2550->597|2633->653|2661->654|2691->657|2754->693|2783->694|2811->695|2853->709|2882->710|2911->711|2970->742|2999->743|3027->744|3058->748|3086->749|3114->750|3173->781|3202->782|3230->783|3258->784|3288->787|3343->815|3372->816|3400->817|3442->831|3471->832|3499->833|3582->889|3610->890|3639->892|3670->896|3698->897|3726->898|3809->954|3837->955|3865->956|3893->957|3921->958
                  LINES: 32->1|33->2|33->2|34->3|35->4|35->4|36->5|36->5|36->5|37->6|42->11|42->11|44->13|44->13|44->13|45->14|52->21|52->21|54->23|54->23|54->23|55->24|57->26|57->26|59->28|59->28|59->28|60->29|62->31|62->31|64->33|64->33|64->33|65->34|67->36|67->36|69->38|69->38|69->38|70->39|72->41|72->41|74->43|74->43|74->43|75->44|75->44|75->44|75->44|75->44|75->44|76->45|76->45|76->45|76->45|76->45|76->45|77->46|77->46|79->48|79->48|79->48|80->49|80->49|80->49|81->50|83->52|83->52|83->52|83->52|83->52|84->53|86->55|86->55|87->56|87->56|88->57
                  -- GENERATED --
              */
          