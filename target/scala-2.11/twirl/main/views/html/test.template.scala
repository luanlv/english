
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object test_Scope0 {
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

     object test_Scope1 {
import play.api.libs.json.JsObject
import play.api.libs.json.JsNull
import play.api.libs.json.JsArray

class test extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[JsObject],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(listMenu: List[JsObject]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.28*/("""

		"""),_display_(/*6.4*/{
			def makeA(href: String, str: String): String = "<a href=\"" + href + "\" class=\"\">" + str + "</a>"

			def render(listMenu: List[JsObject]): String = {
				val x = for(o <- listMenu) yield {
					val sub = if((o \ "children").getOrElse(JsArray()) != JsArray()){
						"<ul>" +
							render((o\"children").as[List[JsObject]]) +
							"</ul>"
					} else {
						""
					}

					"<li>" +
					"<div class=\"icon\"></div>" +
						makeA((o\"http").as[String], (o\"title").as[String]) +
						sub +
					"</li>"
				}

				x.foldLeft(""){(x, y) => x + y}
			}
			Html("<ul>" + render(listMenu) + "</ul>")
		}),format.raw/*29.4*/("""







"""))
      }
    }
  }

  def render(listMenu:List[JsObject]): play.twirl.api.HtmlFormat.Appendable = apply(listMenu)

  def f:((List[JsObject]) => play.twirl.api.HtmlFormat.Appendable) = (listMenu) => apply(listMenu)

  def ref: this.type = this

}


}
}

/**/
object test extends test_Scope0.test_Scope1.test
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:25 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/test.scala.html
                  HASH: 9df9ccceef74abba0d26104c450936ccc2be8134
                  MATRIX: 934->106|1055->132|1085->137|1718->750
                  LINES: 32->4|37->4|39->6|62->29
                  -- GENERATED --
              */
          