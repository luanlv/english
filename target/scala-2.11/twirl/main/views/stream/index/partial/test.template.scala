
package views.stream.index.partial

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object test_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.stream._
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

class test extends BaseScalaTemplate[com.ybrikman.ping.scalaapi.bigpipe.HtmlStreamFormat.Appendable,Format[com.ybrikman.ping.scalaapi.bigpipe.HtmlStreamFormat.Appendable]](com.ybrikman.ping.scalaapi.bigpipe.HtmlStreamFormat) with play.twirl.api.Template3[TestBigPipe,List[Pagelet],Pagelet,com.ybrikman.ping.scalaapi.bigpipe.HtmlStreamFormat.Appendable] {

  /**/
  def apply/*1.2*/(bigPipe: TestBigPipe, listPage: List[Pagelet], page: Pagelet):com.ybrikman.ping.scalaapi.bigpipe.HtmlStreamFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.64*/("""

"""),_display_(/*3.2*/HtmlStream/*3.12*/.fromHtml(views.html.index.other.import_t())),format.raw/*3.56*/("""

"""),_display_(/*5.2*/bigPipe/*5.9*/.render/*5.16*/ { pagelet =>_display_(Seq[Any](format.raw/*5.29*/("""

	 """),_display_(/*7.4*/pagelet(page.id)),format.raw/*7.20*/("""

	"""),format.raw/*9.2*/("""-----------------------------------------

	"""),_display_(/*11.3*/for(page <- listPage) yield /*11.24*/ {_display_(Seq[Any](format.raw/*11.26*/("""
		"""),_display_(/*12.4*/pagelet(page.id)),format.raw/*12.20*/("""
	""")))}),format.raw/*13.3*/("""

	"""),_display_(/*15.3*/HtmlStream/*15.13*/.fromHtml(views.html.index.other.import_b())),format.raw/*15.57*/("""

""")))}),format.raw/*17.2*/("""

"""))
      }
    }
  }

  def render(bigPipe:TestBigPipe,listPage:List[Pagelet],page:Pagelet): com.ybrikman.ping.scalaapi.bigpipe.HtmlStreamFormat.Appendable = apply(bigPipe,listPage,page)

  def f:((TestBigPipe,List[Pagelet],Pagelet) => com.ybrikman.ping.scalaapi.bigpipe.HtmlStreamFormat.Appendable) = (bigPipe,listPage,page) => apply(bigPipe,listPage,page)

  def ref: this.type = this

}


}

/**/
object test extends test_Scope0.test
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/index/partial/test.scala.stream
                  HASH: 845fb7458a05f28518c8b64adf97e4097f977a2a
                  MATRIX: 946->1|1129->63|1157->66|1175->76|1239->120|1267->123|1281->130|1296->137|1346->150|1376->155|1412->171|1441->174|1512->219|1549->240|1589->242|1619->246|1656->262|1689->265|1719->269|1738->279|1803->323|1836->326
                  LINES: 27->1|32->1|34->3|34->3|34->3|36->5|36->5|36->5|36->5|38->7|38->7|40->9|42->11|42->11|42->11|43->12|43->12|44->13|46->15|46->15|46->15|48->17
                  -- GENERATED --
              */
          