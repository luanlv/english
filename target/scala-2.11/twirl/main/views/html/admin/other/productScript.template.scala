
package views.html.admin.other

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object productScript_Scope0 {
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

class productScript extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<script>
$(function() """),format.raw/*2.14*/("""{"""),format.raw/*2.15*/("""
  """),format.raw/*3.3*/("""CKEDITOR.replace('editor1');
"""),format.raw/*4.1*/("""}"""),format.raw/*4.2*/(""");


$(document).ready(function () """),format.raw/*7.31*/("""{"""),format.raw/*7.32*/("""
	"""),format.raw/*8.2*/("""var x = $('.view').length;

  $('#slide').popup("""),format.raw/*10.21*/("""{"""),format.raw/*10.22*/("""
      """),format.raw/*11.7*/("""focusdelay: 400,
      outline: true,
      vertical: 'top'
  """),format.raw/*14.3*/("""}"""),format.raw/*14.4*/(""");

	$(".slide_open").click(function()"""),format.raw/*16.35*/("""{"""),format.raw/*16.36*/("""
	 """),format.raw/*17.3*/("""$.ajax("""),format.raw/*17.10*/("""{"""),format.raw/*17.11*/("""
			"""),format.raw/*18.4*/("""type: "GET", // or GET
			url: "/image/list",
			success: function(data)"""),format.raw/*20.27*/("""{"""),format.raw/*20.28*/("""
			 """),format.raw/*21.5*/("""$(".list-images").html(data)
			"""),format.raw/*22.4*/("""}"""),format.raw/*22.5*/("""
    """),format.raw/*23.5*/("""}"""),format.raw/*23.6*/(""");
  """),format.raw/*24.3*/("""}"""),format.raw/*24.4*/(""");

  $(document).on('click', '.img-container', function()"""),format.raw/*26.55*/("""{"""),format.raw/*26.56*/("""
    """),format.raw/*27.5*/("""if(x<5)"""),format.raw/*27.12*/("""{"""),format.raw/*27.13*/("""
    """),format.raw/*28.5*/("""var smallUrl = $(this).find('img:first').attr('src');
			$('.images-view > div').append(
				'<img class="view" src="' + smallUrl + '" alt=""/>'
			);
			x +=1;
		"""),format.raw/*33.3*/("""}"""),format.raw/*33.4*/("""
	"""),format.raw/*34.2*/("""}"""),format.raw/*34.3*/(""");

	$(document).on('click', '.images-view > div > img', function()"""),format.raw/*36.64*/("""{"""),format.raw/*36.65*/("""
		"""),format.raw/*37.3*/("""$(this).remove();
		x-=1;
	"""),format.raw/*39.2*/("""}"""),format.raw/*39.3*/(""");

	$(document).on('click', '.slide_open', function()"""),format.raw/*41.51*/("""{"""),format.raw/*41.52*/("""
		"""),format.raw/*42.3*/("""var images = $('.image-wrap > div');
		$('.images-view').empty().append(images);
	"""),format.raw/*44.2*/("""}"""),format.raw/*44.3*/(""");

	$(document).on('click', '.imageOk', function()"""),format.raw/*46.48*/("""{"""),format.raw/*46.49*/("""
		"""),format.raw/*47.3*/("""var images = $('.images-view > div');
		$('.image-wrap').append(images);
		var z = 0;
		$('.listImage').empty();
		$('.image-wrap > div').children('img').each(function () """),format.raw/*51.59*/("""{"""),format.raw/*51.60*/("""
      """),format.raw/*52.7*/("""var smallUrl = $(this).attr('src');
      var listImage = $('.listImage');
			listImage.append('<input type="hidden" name="info.image['+ z +'].origin" class="" value="'+ smallUrl.replace('/small/', '/origin/') +'">');
			listImage.append('<input type="hidden" name="info.image['+ z +'].small" class="" value="'+ smallUrl +'">');
			listImage.append('<input type="hidden" name="info.image['+ z +'].thumb" class="" value="'+ smallUrl.replace('/small/', '/thumb/') +'">');
			z+=1;
		"""),format.raw/*58.3*/("""}"""),format.raw/*58.4*/(""");
	"""),format.raw/*59.2*/("""}"""),format.raw/*59.3*/(""");

  $(document).on('keypress', '.search-image', function (event) """),format.raw/*61.64*/("""{"""),format.raw/*61.65*/("""
         """),format.raw/*62.10*/("""if(event.which === 13)"""),format.raw/*62.32*/("""{"""),format.raw/*62.33*/("""
            """),format.raw/*63.13*/("""$.ajax("""),format.raw/*63.20*/("""{"""),format.raw/*63.21*/("""
							"""),format.raw/*64.8*/("""type: "GET", // or GET
							url: "/image/list" + "?name=" + $(this).val(),
							success: function(data)"""),format.raw/*66.31*/("""{"""),format.raw/*66.32*/("""
								 """),format.raw/*67.10*/("""$(".list-images").html(data)
							"""),format.raw/*68.8*/("""}"""),format.raw/*68.9*/("""
						"""),format.raw/*69.7*/("""}"""),format.raw/*69.8*/(""");
         """),format.raw/*70.10*/("""}"""),format.raw/*70.11*/("""
  """),format.raw/*71.3*/("""}"""),format.raw/*71.4*/(""");

	$(document).on('click', '.back', function (event) """),format.raw/*73.52*/("""{"""),format.raw/*73.53*/("""
        """),format.raw/*74.9*/("""$.ajax("""),format.raw/*74.16*/("""{"""),format.raw/*74.17*/("""
					"""),format.raw/*75.6*/("""type: "GET", // or GET
					url: "/image/list" + "?name=" + $(".search-image").val() + "&page=" + (parseInt($('.page').text())-1).toString(),
					success: function(data)"""),format.raw/*77.29*/("""{"""),format.raw/*77.30*/("""
						 """),format.raw/*78.8*/("""$(".list-images").html(data)
					"""),format.raw/*79.6*/("""}"""),format.raw/*79.7*/("""
				"""),format.raw/*80.5*/("""}"""),format.raw/*80.6*/(""");
  """),format.raw/*81.3*/("""}"""),format.raw/*81.4*/(""");

  $(document).on('click', '.next', function (event) """),format.raw/*83.53*/("""{"""),format.raw/*83.54*/("""
        """),format.raw/*84.9*/("""$.ajax("""),format.raw/*84.16*/("""{"""),format.raw/*84.17*/("""
					"""),format.raw/*85.6*/("""type: "GET", // or GET
					url: "/image/list" + "?name=" + $(".search-image").val() + "&page=" + (parseInt($('.page').text())+1).toString(),
					success: function(data)"""),format.raw/*87.29*/("""{"""),format.raw/*87.30*/("""
						 """),format.raw/*88.8*/("""$(".list-images").html(data)
					"""),format.raw/*89.6*/("""}"""),format.raw/*89.7*/("""
				"""),format.raw/*90.5*/("""}"""),format.raw/*90.6*/(""");
  """),format.raw/*91.3*/("""}"""),format.raw/*91.4*/(""");


  $(document).on('change', '#sku_id', function(even)"""),format.raw/*94.53*/("""{"""),format.raw/*94.54*/("""
      """),format.raw/*95.7*/("""var cate = $('#sku_id').find(":selected").val();
      $.ajax("""),format.raw/*96.14*/("""{"""),format.raw/*96.15*/("""
					"""),format.raw/*97.6*/("""type: "GET", // or GET
					url: "/api/getGroup/" + cate,
					success: function(data)"""),format.raw/*99.29*/("""{"""),format.raw/*99.30*/("""
						"""),format.raw/*100.7*/("""var group = $('#infogroup');
					  $('#infogroup option').remove();
					  data.forEach(function(item)"""),format.raw/*102.35*/("""{"""),format.raw/*102.36*/("""
					    """),format.raw/*103.10*/("""group.append("<option value='" + item + "'>" + item + "</option>");
					  """),format.raw/*104.8*/("""}"""),format.raw/*104.9*/(""");
					"""),format.raw/*105.6*/("""}"""),format.raw/*105.7*/("""
				"""),format.raw/*106.5*/("""}"""),format.raw/*106.6*/(""");
  """),format.raw/*107.3*/("""}"""),format.raw/*107.4*/(""");


  var templatePrice = function(num)"""),format.raw/*110.36*/("""{"""),format.raw/*110.37*/("""
    """),format.raw/*111.5*/("""var str = '<div id="p' + num + '">';
    str += '<div class="col-md-6">';
    str += '<input class="form-control" type="text" name="core.price['+ num +'].num"  value="" placeholder="Số lượng">';
    str += '</div>';
    str += '<div class="col-md-6">';
    str += '<input class="form-control" type="text" name="core.price[' + num + '].price"  value="" placeholder="Giá">';
    str += '</div>';
    str += '</div>';
    return str;
  """),format.raw/*120.3*/("""}"""),format.raw/*120.4*/(""";


  var priceCurrent = $(".priceCount").length;
  $(document).on('click', '#morePrice', function()"""),format.raw/*124.51*/("""{"""),format.raw/*124.52*/("""
    """),format.raw/*125.5*/("""if(priceCurrent < 5)"""),format.raw/*125.25*/("""{"""),format.raw/*125.26*/("""
	    """),format.raw/*126.6*/("""$('#priceContainer').append(templatePrice(priceCurrent));
	    priceCurrent += 1;
    """),format.raw/*128.5*/("""}"""),format.raw/*128.6*/("""
  """),format.raw/*129.3*/("""}"""),format.raw/*129.4*/(""");

 $(document).on('click', '#lessPrice', function()"""),format.raw/*131.50*/("""{"""),format.raw/*131.51*/("""
    """),format.raw/*132.5*/("""if(priceCurrent > 0)"""),format.raw/*132.25*/("""{"""),format.raw/*132.26*/("""
	    """),format.raw/*133.6*/("""var id = "#p" + priceCurrent;
	    console.log(id);
	    priceCurrent -= 1;
	    $(id).remove();
    """),format.raw/*137.5*/("""}"""),format.raw/*137.6*/("""
  """),format.raw/*138.3*/("""}"""),format.raw/*138.4*/(""");

"""),format.raw/*140.1*/("""}"""),format.raw/*140.2*/(""");
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
object productScript extends productScript_Scope0.productScript
              /*
                  -- GENERATED --
                  DATE: Sun Apr 17 11:11:27 ICT 2016
                  SOURCE: /mnt/ramdisk/mainapp/app/views/admin/other/productScript.scala.html
                  HASH: b41a1745018ded6aa904f3acd2a57f1363603197
                  MATRIX: 909->0|958->22|986->23|1015->26|1070->55|1097->56|1159->91|1187->92|1215->94|1291->142|1320->143|1354->150|1443->212|1471->213|1537->251|1566->252|1596->255|1631->262|1660->263|1691->267|1791->339|1820->340|1852->345|1911->377|1939->378|1971->383|1999->384|2031->389|2059->390|2145->448|2174->449|2206->454|2241->461|2270->462|2302->467|2492->630|2520->631|2549->633|2577->634|2672->701|2701->702|2731->705|2785->732|2813->733|2895->787|2924->788|2954->791|3063->873|3091->874|3170->925|3199->926|3229->929|3428->1100|3457->1101|3491->1108|3999->1589|4027->1590|4058->1594|4086->1595|4181->1662|4210->1663|4248->1673|4298->1695|4327->1696|4368->1709|4403->1716|4432->1717|4467->1725|4602->1832|4631->1833|4669->1843|4732->1879|4760->1880|4794->1887|4822->1888|4862->1900|4891->1901|4921->1904|4949->1905|5032->1960|5061->1961|5097->1970|5132->1977|5161->1978|5194->1984|5392->2154|5421->2155|5456->2163|5517->2197|5545->2198|5577->2203|5605->2204|5637->2209|5665->2210|5749->2266|5778->2267|5814->2276|5849->2283|5878->2284|5911->2290|6109->2460|6138->2461|6173->2469|6234->2503|6262->2504|6294->2509|6322->2510|6354->2515|6382->2516|6467->2573|6496->2574|6530->2581|6620->2643|6649->2644|6682->2650|6796->2736|6825->2737|6860->2744|6992->2847|7022->2848|7061->2858|7164->2933|7193->2934|7229->2942|7258->2943|7291->2948|7320->2949|7353->2954|7382->2955|7451->2995|7481->2996|7514->3001|7975->3434|8004->3435|8133->3535|8163->3536|8196->3541|8245->3561|8275->3562|8309->3568|8423->3654|8452->3655|8483->3658|8512->3659|8594->3712|8624->3713|8657->3718|8706->3738|8736->3739|8770->3745|8899->3846|8928->3847|8959->3850|8988->3851|9020->3855|9049->3856
                  LINES: 32->1|33->2|33->2|34->3|35->4|35->4|38->7|38->7|39->8|41->10|41->10|42->11|45->14|45->14|47->16|47->16|48->17|48->17|48->17|49->18|51->20|51->20|52->21|53->22|53->22|54->23|54->23|55->24|55->24|57->26|57->26|58->27|58->27|58->27|59->28|64->33|64->33|65->34|65->34|67->36|67->36|68->37|70->39|70->39|72->41|72->41|73->42|75->44|75->44|77->46|77->46|78->47|82->51|82->51|83->52|89->58|89->58|90->59|90->59|92->61|92->61|93->62|93->62|93->62|94->63|94->63|94->63|95->64|97->66|97->66|98->67|99->68|99->68|100->69|100->69|101->70|101->70|102->71|102->71|104->73|104->73|105->74|105->74|105->74|106->75|108->77|108->77|109->78|110->79|110->79|111->80|111->80|112->81|112->81|114->83|114->83|115->84|115->84|115->84|116->85|118->87|118->87|119->88|120->89|120->89|121->90|121->90|122->91|122->91|125->94|125->94|126->95|127->96|127->96|128->97|130->99|130->99|131->100|133->102|133->102|134->103|135->104|135->104|136->105|136->105|137->106|137->106|138->107|138->107|141->110|141->110|142->111|151->120|151->120|155->124|155->124|156->125|156->125|156->125|157->126|159->128|159->128|160->129|160->129|162->131|162->131|163->132|163->132|163->132|164->133|168->137|168->137|169->138|169->138|171->140|171->140
                  -- GENERATED --
              */
          