
package views.html.index.other

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object header_Scope0 {
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

class header extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template7[_root_.scala.Function1[String, String],_root_.scala.Function1[String, String],String,String,String,Int,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(assetCDN: String => String,  map: String => String,  _sub: String = "", _kw: String = "", _sb: String = "", _li: Int = 12, _v: String = ""):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.142*/("""
"""),format.raw/*2.1*/("""<div class="header">
	<div class="h-top">
		<div class="hm-left">
		</div>
		<div class="hm-middle">
			<div class="hmm-left">
				<div class="hmml-top">
					<form class="search_bar" action=""""),_display_(/*9.40*/if(_sub == "")/*9.54*/{_display_(Seq[Any](format.raw/*9.55*/("""/tim-kiem.html""")))}/*9.71*/else/*9.76*/{_display_(Seq[Any](format.raw/*9.77*/("""/san-pham/"""),_display_(/*9.88*/_sub),format.raw/*9.92*/("""/list.html""")))}),format.raw/*9.103*/("""" method="GET">
						<div class="search_dropdown">
							<span>"""),_display_(/*11.15*/if( _sub==""  )/*11.30*/{_display_(Seq[Any](format.raw/*11.31*/(""" """),format.raw/*11.32*/("""All """)))}/*11.38*/else/*11.43*/{_display_(Seq[Any](format.raw/*11.44*/(""" """),_display_(/*11.46*/map(_sub))))}),format.raw/*11.56*/("""</span>
							<ul class="select-search">
								<li """),_display_(/*13.14*/if( _sub=="")/*13.27*/{_display_(Seq[Any](format.raw/*13.28*/(""" """),format.raw/*13.29*/("""class="selected" """)))}),format.raw/*13.47*/(""" """),format.raw/*13.48*/("""value="">All</li>
								<li """),_display_(/*14.14*/if( _sub == "may-nap-adapter")/*14.44*/{_display_(Seq[Any](format.raw/*14.45*/(""" """),format.raw/*14.46*/("""class="selected"""")))}),format.raw/*14.63*/(""" """),format.raw/*14.64*/("""value="may-nap-adapter">Máy Nạp & Adapter</li>
								<li """),_display_(/*15.14*/if( _sub == "board-phat-trien")/*15.45*/{_display_(Seq[Any](format.raw/*15.46*/(""" """),format.raw/*15.47*/("""class="selected"""")))}),format.raw/*15.64*/(""" """),format.raw/*15.65*/("""value="board-phat-trien">Board Phát triển</li>
								<li """),_display_(/*16.14*/if( _sub == "sensor-transducer")/*16.46*/{_display_(Seq[Any](format.raw/*16.47*/(""" """),format.raw/*16.48*/("""class="selected"""")))}),format.raw/*16.65*/(""" """),format.raw/*16.66*/("""value="sensor-transducer">Sensors, Transducers</li>
								<li """),_display_(/*17.14*/if( _sub == "memory-ic")/*17.38*/{_display_(Seq[Any](format.raw/*17.39*/(""" """),format.raw/*17.40*/("""class="selected"""")))}),format.raw/*17.57*/(""" """),format.raw/*17.58*/("""value="memory-ic">Memory ICs</li>
								<li """),_display_(/*18.14*/if( _sub == "led")/*18.32*/{_display_(Seq[Any](format.raw/*18.33*/(""" """),format.raw/*18.34*/("""class="selected"""")))}),format.raw/*18.51*/(""" """),format.raw/*18.52*/("""value="led">LEDs</li>
								<li """),_display_(/*19.14*/if( _sub == "lcd-display")/*19.40*/{_display_(Seq[Any](format.raw/*19.41*/(""" """),format.raw/*19.42*/("""class="selected"""")))}),format.raw/*19.59*/(""" """),format.raw/*19.60*/("""value="lcd-display">LCDs Display</li>
							</ul>
						</div>
						<input type="text" name="_kw" value=""""),_display_(/*22.45*/_kw),format.raw/*22.48*/(""""/>
						<button type="submit" value="Search">Search</button>
					</form>
				</div>
				<div class="hmml-bot">
					Ví dụ: Bộ Hẹn Giờ, Nguồn, Cảm Biến, WiFi, Bluetooth ...
				</div>
			</div>
			<div class="hmm-right">

			<div class="hmm-submenu">
				<span class="iconMenu ra-5 bo">&#9776; Menu</span>
				<div class="hmm-cart">
					<a  href="">
						<div class="cartWr ra-5">
							<span class="cart-icon"></span>
							<div class="cart-meta">
								<div class="cart-number">0</div>
								<div class="cart-name">Cart</div>
							</div>
						</div>
					</a>
				</div>


				<div class="hmm-user">
					<div class="userWr ra-5">
						<span class="user-icon"></span>
						<div class="user-meta">
							<span class="user-registor"><a href="">Đăng ký</a></span>
							<span class="user-login"><a href="">Đăng nhập</a></span>
						</div>
					</div>
				</div>

				<div class="hmm-phone">
					<div class="phoneWr ra-5">
						<span class="phone-icon"></span>
						<div class="phone-meta">
							<span class="phone-up">0978.715.123</span>
							<span class="phone-bot">01666.555.336</span>
						</div>
					</div>
				</div>

			</div>

			</div>

		</div>

	</div>


</div>

<div class="sub-nav sa">
	<div class="sn-left">
		<div class="nsl-t"></div>
		<div class="nsl-b">
		</div>
	</div>

	<div class="sn-right">
		<a href="#">Sản Phẩm Mới</a>
		<a href="#">Sản Phẩm Bán Chạy</a>
		<a href="#">Sản Phẩm Nổi Bật</a>
		<a href="#">Sản Phẩm Khuyến Mãi</a>
		<a href="#" class="f-r slh"  onclick="return false;">Liên hệ</a>
		<a href="#" class="f-r sol" style="float:right">Hướng dẫn mua hàng online</a>
	</div>
</div>"""))
      }
    }
  }

  def render(assetCDN:_root_.scala.Function1[String, String],map:_root_.scala.Function1[String, String],_sub:String,_kw:String,_sb:String,_li:Int,_v:String): play.twirl.api.HtmlFormat.Appendable = apply(assetCDN,map,_sub,_kw,_sb,_li,_v)

  def f:((_root_.scala.Function1[String, String],_root_.scala.Function1[String, String],String,String,String,Int,String) => play.twirl.api.HtmlFormat.Appendable) = (assetCDN,map,_sub,_kw,_sb,_li,_v) => apply(assetCDN,map,_sub,_kw,_sb,_li,_v)

  def ref: this.type = this

}


}

/**/
object header extends header_Scope0.header
              /*
                  -- GENERATED --
                  DATE: Sat Mar 26 00:44:36 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/index/other/header.scala.html
                  HASH: d53d2d83d5f90c43163da641777d1b95f02101ad
                  MATRIX: 916->1|1152->141|1179->142|1398->335|1420->349|1458->350|1491->366|1503->371|1541->372|1578->383|1602->387|1644->398|1737->464|1761->479|1800->480|1829->481|1853->487|1866->492|1905->493|1934->495|1968->505|2050->560|2072->573|2111->574|2140->575|2189->593|2218->594|2276->625|2315->655|2354->656|2383->657|2431->674|2460->675|2547->735|2587->766|2626->767|2655->768|2703->785|2732->786|2819->846|2860->878|2899->879|2928->880|2976->897|3005->898|3097->963|3130->987|3169->988|3198->989|3246->1006|3275->1007|3349->1054|3376->1072|3415->1073|3444->1074|3492->1091|3521->1092|3583->1127|3618->1153|3657->1154|3686->1155|3734->1172|3763->1173|3898->1281|3922->1284
                  LINES: 27->1|32->1|33->2|40->9|40->9|40->9|40->9|40->9|40->9|40->9|40->9|40->9|42->11|42->11|42->11|42->11|42->11|42->11|42->11|42->11|42->11|44->13|44->13|44->13|44->13|44->13|44->13|45->14|45->14|45->14|45->14|45->14|45->14|46->15|46->15|46->15|46->15|46->15|46->15|47->16|47->16|47->16|47->16|47->16|47->16|48->17|48->17|48->17|48->17|48->17|48->17|49->18|49->18|49->18|49->18|49->18|49->18|50->19|50->19|50->19|50->19|50->19|50->19|53->22|53->22
                  -- GENERATED --
              */
          