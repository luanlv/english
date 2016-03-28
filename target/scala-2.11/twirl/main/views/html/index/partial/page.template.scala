
package views.html.index.partial

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object page_Scope0 {
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

class page extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(info: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.16*/("""

"""),format.raw/*3.1*/("""Pagelet: """),_display_(/*3.11*/info))
      }
    }
  }

  def render(info:String): play.twirl.api.HtmlFormat.Appendable = apply(info)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (info) => apply(info)

  def ref: this.type = this

}


}

/**/
object page extends page_Scope0.page
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/index/partial/page.scala.html
                  HASH: 7a33298422d6fbc9a91aa84d97eadd4b712f9437
                  MATRIX: 811->1|920->15|948->17|984->27
                  LINES: 27->1|32->1|34->3|34->3
                  -- GENERATED --
              */
          