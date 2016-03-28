
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object tmp_Scope0 {
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

class tmp extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*1.2*/cssAt("semantic/semantic.min.css")),format.raw/*1.36*/("""
"""),format.raw/*2.109*/("""
"""),format.raw/*3.1*/("""<script type="text/javascript"></script>
"""),_display_(/*4.2*/jsTag("mithril.js")),format.raw/*4.21*/("""
"""),format.raw/*5.101*/("""
"""),_display_(/*6.2*/jQueryTag),format.raw/*6.11*/("""
"""),format.raw/*7.101*/("""
"""),_display_(/*8.2*/jsAt("semantic/semantic.min.js")),format.raw/*8.34*/("""
"""),_display_(/*9.2*/jsTag("jquery.textarea_autosize.js")),format.raw/*9.38*/("""
"""),_display_(/*10.2*/jsTag("attrchange.js")),format.raw/*10.24*/("""
"""),_display_(/*11.2*/jsTag("main.js")),format.raw/*11.18*/("""
"""),_display_(/*12.2*/jsTag("app2.js")))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object tmp extends tmp_Scope0.tmp
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:35 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/tmp.scala.html
                  HASH: fe35d871b04aa47ff3a831cc21769912de5126c6
                  MATRIX: 877->1|931->35|960->144|987->145|1054->187|1093->206|1122->307|1149->309|1178->318|1207->419|1234->421|1286->453|1313->455|1369->491|1397->493|1440->515|1468->517|1505->533|1533->535
                  LINES: 32->1|32->1|33->2|34->3|35->4|35->4|36->5|37->6|37->6|38->7|39->8|39->8|40->9|40->9|41->10|41->10|42->11|42->11|43->12
                  -- GENERATED --
              */
          