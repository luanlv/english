
package views.html.index.other

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object import_t_Scope0 {
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

class import_t extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<meta name="viewport" content="width = device-width, initial-scale = 1.0">

<link rel="shortcut icon" type="image/png" href='"""),_display_(/*3.51*/routes/*3.57*/.Assets.at("favicon.png")),format.raw/*3.82*/("""'>
"""),_display_(/*4.2*/cssTag("main.css")),format.raw/*4.20*/("""
"""),format.raw/*5.1*/("""<link rel="stylesheet" href='"""),_display_(/*5.31*/routes/*5.37*/.Assets.at("photoswipe/photoswipe.css")),format.raw/*5.76*/("""'>
<link rel="stylesheet" href='"""),_display_(/*6.31*/routes/*6.37*/.Assets.at("photoswipe/default-skin/default-skin.css")),format.raw/*6.91*/("""'>
"""),_display_(/*7.2*/jsTag("big.js")),format.raw/*7.17*/("""

"""),format.raw/*9.88*/("""
"""),format.raw/*10.89*/("""
"""),format.raw/*11.95*/("""
"""),format.raw/*12.89*/("""
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
object import_t extends import_t_Scope0.import_t
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:26 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/index/other/import_t.scala.html
                  HASH: 459eaa23b17004db74da332002af6ea4197db1d6
                  MATRIX: 899->0|1051->126|1065->132|1110->157|1139->161|1177->179|1204->180|1260->210|1274->216|1333->255|1392->288|1406->294|1480->348|1509->352|1544->367|1573->456|1602->545|1631->640|1660->729
                  LINES: 32->1|34->3|34->3|34->3|35->4|35->4|36->5|36->5|36->5|36->5|37->6|37->6|37->6|38->7|38->7|40->9|41->10|42->11|43->12
                  -- GENERATED --
              */
          