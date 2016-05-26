
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/ramdisk/mainapp/conf/routes
// @DATE:Thu May 26 05:20:56 ICT 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

object Routes extends Routes

class Routes extends GeneratedRouter {

  import ReverseRouteContext.empty

  override val errorHandler: play.api.http.HttpErrorHandler = play.api.http.LazyHttpErrorHandler

  private var _prefix = "/"

  def withPrefix(prefix: String): Routes = {
    _prefix = prefix
    router.RoutesPrefix.setPrefix(prefix)
    
    this
  }

  def prefix: String = _prefix

  lazy val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation: Seq[(String, String, String)] = List(
    ("""GET""", prefix, """controllers.Application.index"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """post/$postId<[^/]+>""", """controllers.Application.post(postId:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """qa""", """controllers.Application.index"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """qa/new""", """controllers.Application.index"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """qa/new""", """controllers.API.doAsk"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """qa/newquestion""", """controllers.API.newQuestion"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """qa/hotquestion""", """controllers.API.hotQuestion"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """qa/allquestion""", """controllers.API.getAllQuestion"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """qa/$questionId<[^/]+>""", """controllers.API.getQuestion(questionId:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """chatroom""", """controllers.Chat.chatRooms"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """chatroom/$id<[^/]+>""", """controllers.Chat.chatRoom(id:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """json""", """controllers.Application.json"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """settings""", """controllers.Application.index"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """@/$username<[^/]+>""", """controllers.Application.user(username:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """@/$username<[^/]+>/mini""", """controllers.User.showMini(username:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """post""", """controllers.API.doPost"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """post/$username<[^/]+>""", """controllers.API.getPost(username:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewpost/$postId<[^/]+>""", """controllers.API.viewPost(postId:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewquestion/$questionId<[^/]+>""", """controllers.API.viewQuestion(questionId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """like/post/$postId<[^/]+>""", """controllers.API.likePost(postId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """unlike/post/$postId<[^/]+>""", """controllers.API.unlikePost(postId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """vote/question/$questionId<[^/]+>""", """controllers.API.vote(questionId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """vote/answer/$answerId<[^/]+>""", """controllers.API.voteAnswer(answerId:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """socket""", """controllers.Main.websocket"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """monitor""", """controllers.Monitor.index"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """monitor/socket""", """controllers.Monitor.websocket"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """status""", """controllers.Monitor.status"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""", """controllers.Auth.login"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""", """controllers.Auth.authenticate"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""", """controllers.Auth.logout"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""", """controllers.Auth.signup"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""", """controllers.Auth.signupPost"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup/check-your-email/$name<[^/]+>""", """controllers.Auth.checkYourEmail(name:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup/confirm/$token<[^/]+>""", """controllers.Auth.signupConfirmEmail(token:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """password/reset""", """controllers.Auth.passwordReset"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """password/reset/send""", """controllers.Auth.passwordResetApply"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """password/reset/sent/$email<[^/]+>""", """controllers.Auth.passwordResetSent(email:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """password/reset/confirm/$token<[^/]+>""", """controllers.Auth.passwordResetConfirm(token:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """password/reset/confirm/$token<[^/]+>""", """controllers.Auth.passwordResetConfirmApply(token:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """set-fingerprint/$hash<[^/]+>/$ms<[^/]+>""", """controllers.Auth.setFingerprint(hash:String, ms:Int)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getimage/$size<[^/]+>/$uuid<[^/]+>""", """@controllers.ImageCtrl@.get(size:String, uuid:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """upload/image""", """@controllers.ImageCtrl@.upload"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/follow/$userId<[^/]+>""", """controllers.Relation.follow(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/request/$userId<[^/]+>""", """controllers.Relation.request(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/unfollow/$userId<[^/]+>""", """controllers.Relation.unfollow(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/unrequest/$userId<[^/]+>""", """controllers.Relation.unrequest(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/reject/$userId<[^/]+>""", """controllers.Relation.reject(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/friend/$userId<[^/]+>""", """controllers.Relation.friend(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/unfriend/$userId<[^/]+>""", """controllers.Relation.unfriend(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/block/$userId<[^/]+>""", """controllers.Relation.block(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rel/unblock/$userId<[^/]+>""", """controllers.Relation.unblock(userId:String)"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """settings""", """controllers.API.getSelfInformation"""),
    ("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """settings/updateInfo""", """controllers.API.updateInformation"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/getUser/$user<[^/]+>""", """controllers.API.getInformationUser(user:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/listfollow/$userId<[^/]+>""", """controllers.API.getListFollower(userId:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/listfriend/$userId<[^/]+>""", """controllers.API.getListFriend(userId:String)"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """google5106a805f9682e1e.html""", """controllers.Assets.at(path:String = "/public", file:String = "google5106a805f9682e1e.html")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """robots.txt""", """controllers.Assets.at(path:String = "/public", file:String = "robots.txt")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sitemap.xml""", """controllers.Assets.at(path:String = "/public", file:String = "sitemap.xml")"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:4
  private[this] lazy val controllers_Application_index0_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index0_invoker = createInvoker(
    controllers.Application.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:5
  private[this] lazy val controllers_Application_post1_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("post/"), DynamicPart("postId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Application_post1_invoker = createInvoker(
    controllers.Application.post(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "post",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """post/$postId<[^/]+>"""
    )
  )

  // @LINE:6
  private[this] lazy val controllers_Application_index2_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qa")))
  )
  private[this] lazy val controllers_Application_index2_invoker = createInvoker(
    controllers.Application.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """qa"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_Application_index3_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qa/new")))
  )
  private[this] lazy val controllers_Application_index3_invoker = createInvoker(
    controllers.Application.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """qa/new"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_API_doAsk4_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qa/new")))
  )
  private[this] lazy val controllers_API_doAsk4_invoker = createInvoker(
    controllers.API.doAsk,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "doAsk",
      Nil,
      "POST",
      """POST /qa/answer            controllers.API.doAnswer""",
      this.prefix + """qa/new"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_API_newQuestion5_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qa/newquestion")))
  )
  private[this] lazy val controllers_API_newQuestion5_invoker = createInvoker(
    controllers.API.newQuestion,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "newQuestion",
      Nil,
      "GET",
      """""",
      this.prefix + """qa/newquestion"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_API_hotQuestion6_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qa/hotquestion")))
  )
  private[this] lazy val controllers_API_hotQuestion6_invoker = createInvoker(
    controllers.API.hotQuestion,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "hotQuestion",
      Nil,
      "GET",
      """""",
      this.prefix + """qa/hotquestion"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_API_getAllQuestion7_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qa/allquestion")))
  )
  private[this] lazy val controllers_API_getAllQuestion7_invoker = createInvoker(
    controllers.API.getAllQuestion,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "getAllQuestion",
      Nil,
      "GET",
      """""",
      this.prefix + """qa/allquestion"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_API_getQuestion8_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qa/"), DynamicPart("questionId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_getQuestion8_invoker = createInvoker(
    controllers.API.getQuestion(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "getQuestion",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """qa/$questionId<[^/]+>"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Chat_chatRooms9_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("chatroom")))
  )
  private[this] lazy val controllers_Chat_chatRooms9_invoker = createInvoker(
    controllers.Chat.chatRooms,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Chat",
      "chatRooms",
      Nil,
      "GET",
      """""",
      this.prefix + """chatroom"""
    )
  )

  // @LINE:18
  private[this] lazy val controllers_Chat_chatRoom10_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("chatroom/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Chat_chatRoom10_invoker = createInvoker(
    controllers.Chat.chatRoom(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Chat",
      "chatRoom",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """chatroom/$id<[^/]+>"""
    )
  )

  // @LINE:19
  private[this] lazy val controllers_Application_json11_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("json")))
  )
  private[this] lazy val controllers_Application_json11_invoker = createInvoker(
    controllers.Application.json,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "json",
      Nil,
      "GET",
      """""",
      this.prefix + """json"""
    )
  )

  // @LINE:20
  private[this] lazy val controllers_Application_index12_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("settings")))
  )
  private[this] lazy val controllers_Application_index12_invoker = createInvoker(
    controllers.Application.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """settings"""
    )
  )

  // @LINE:22
  private[this] lazy val controllers_Application_user13_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("@/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Application_user13_invoker = createInvoker(
    controllers.Application.user(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "user",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """@/$username<[^/]+>"""
    )
  )

  // @LINE:23
  private[this] lazy val controllers_User_showMini14_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("@/"), DynamicPart("username", """[^/]+""",true), StaticPart("/mini")))
  )
  private[this] lazy val controllers_User_showMini14_invoker = createInvoker(
    controllers.User.showMini(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.User",
      "showMini",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """@/$username<[^/]+>/mini"""
    )
  )

  // @LINE:25
  private[this] lazy val controllers_API_doPost15_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("post")))
  )
  private[this] lazy val controllers_API_doPost15_invoker = createInvoker(
    controllers.API.doPost,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "doPost",
      Nil,
      "POST",
      """""",
      this.prefix + """post"""
    )
  )

  // @LINE:26
  private[this] lazy val controllers_API_getPost16_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("post/"), DynamicPart("username", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_getPost16_invoker = createInvoker(
    controllers.API.getPost(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "getPost",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """post/$username<[^/]+>"""
    )
  )

  // @LINE:27
  private[this] lazy val controllers_API_viewPost17_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("viewpost/"), DynamicPart("postId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_viewPost17_invoker = createInvoker(
    controllers.API.viewPost(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "viewPost",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """viewpost/$postId<[^/]+>"""
    )
  )

  // @LINE:28
  private[this] lazy val controllers_API_viewQuestion18_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("viewquestion/"), DynamicPart("questionId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_viewQuestion18_invoker = createInvoker(
    controllers.API.viewQuestion(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "viewQuestion",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """viewquestion/$questionId<[^/]+>"""
    )
  )

  // @LINE:30
  private[this] lazy val controllers_API_likePost19_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("like/post/"), DynamicPart("postId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_likePost19_invoker = createInvoker(
    controllers.API.likePost(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "likePost",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """like/post/$postId<[^/]+>"""
    )
  )

  // @LINE:31
  private[this] lazy val controllers_API_unlikePost20_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("unlike/post/"), DynamicPart("postId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_unlikePost20_invoker = createInvoker(
    controllers.API.unlikePost(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "unlikePost",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """unlike/post/$postId<[^/]+>"""
    )
  )

  // @LINE:32
  private[this] lazy val controllers_API_vote21_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("vote/question/"), DynamicPart("questionId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_vote21_invoker = createInvoker(
    controllers.API.vote(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "vote",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """vote/question/$questionId<[^/]+>"""
    )
  )

  // @LINE:33
  private[this] lazy val controllers_API_voteAnswer22_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("vote/answer/"), DynamicPart("answerId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_voteAnswer22_invoker = createInvoker(
    controllers.API.voteAnswer(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "voteAnswer",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """vote/answer/$answerId<[^/]+>"""
    )
  )

  // @LINE:36
  private[this] lazy val controllers_Main_websocket23_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("socket")))
  )
  private[this] lazy val controllers_Main_websocket23_invoker = createInvoker(
    controllers.Main.websocket,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Main",
      "websocket",
      Nil,
      "GET",
      """""",
      this.prefix + """socket"""
    )
  )

  // @LINE:38
  private[this] lazy val controllers_Monitor_index24_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("monitor")))
  )
  private[this] lazy val controllers_Monitor_index24_invoker = createInvoker(
    controllers.Monitor.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Monitor",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """monitor"""
    )
  )

  // @LINE:39
  private[this] lazy val controllers_Monitor_websocket25_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("monitor/socket")))
  )
  private[this] lazy val controllers_Monitor_websocket25_invoker = createInvoker(
    controllers.Monitor.websocket,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Monitor",
      "websocket",
      Nil,
      "GET",
      """""",
      this.prefix + """monitor/socket"""
    )
  )

  // @LINE:40
  private[this] lazy val controllers_Monitor_status26_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("status")))
  )
  private[this] lazy val controllers_Monitor_status26_invoker = createInvoker(
    controllers.Monitor.status,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Monitor",
      "status",
      Nil,
      "GET",
      """""",
      this.prefix + """status"""
    )
  )

  // @LINE:42
  private[this] lazy val controllers_Auth_login27_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_Auth_login27_invoker = createInvoker(
    controllers.Auth.login,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "login",
      Nil,
      "GET",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:43
  private[this] lazy val controllers_Auth_authenticate28_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_Auth_authenticate28_invoker = createInvoker(
    controllers.Auth.authenticate,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "authenticate",
      Nil,
      "POST",
      """""",
      this.prefix + """login"""
    )
  )

  // @LINE:44
  private[this] lazy val controllers_Auth_logout29_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("logout")))
  )
  private[this] lazy val controllers_Auth_logout29_invoker = createInvoker(
    controllers.Auth.logout,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "logout",
      Nil,
      "GET",
      """""",
      this.prefix + """logout"""
    )
  )

  // @LINE:45
  private[this] lazy val controllers_Auth_signup30_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup")))
  )
  private[this] lazy val controllers_Auth_signup30_invoker = createInvoker(
    controllers.Auth.signup,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "signup",
      Nil,
      "GET",
      """""",
      this.prefix + """signup"""
    )
  )

  // @LINE:46
  private[this] lazy val controllers_Auth_signupPost31_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup")))
  )
  private[this] lazy val controllers_Auth_signupPost31_invoker = createInvoker(
    controllers.Auth.signupPost,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "signupPost",
      Nil,
      "POST",
      """""",
      this.prefix + """signup"""
    )
  )

  // @LINE:47
  private[this] lazy val controllers_Auth_checkYourEmail32_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup/check-your-email/"), DynamicPart("name", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_checkYourEmail32_invoker = createInvoker(
    controllers.Auth.checkYourEmail(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "checkYourEmail",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """signup/check-your-email/$name<[^/]+>"""
    )
  )

  // @LINE:48
  private[this] lazy val controllers_Auth_signupConfirmEmail33_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup/confirm/"), DynamicPart("token", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_signupConfirmEmail33_invoker = createInvoker(
    controllers.Auth.signupConfirmEmail(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "signupConfirmEmail",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """signup/confirm/$token<[^/]+>"""
    )
  )

  // @LINE:49
  private[this] lazy val controllers_Auth_passwordReset34_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("password/reset")))
  )
  private[this] lazy val controllers_Auth_passwordReset34_invoker = createInvoker(
    controllers.Auth.passwordReset,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "passwordReset",
      Nil,
      "GET",
      """""",
      this.prefix + """password/reset"""
    )
  )

  // @LINE:50
  private[this] lazy val controllers_Auth_passwordResetApply35_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("password/reset/send")))
  )
  private[this] lazy val controllers_Auth_passwordResetApply35_invoker = createInvoker(
    controllers.Auth.passwordResetApply,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "passwordResetApply",
      Nil,
      "POST",
      """""",
      this.prefix + """password/reset/send"""
    )
  )

  // @LINE:51
  private[this] lazy val controllers_Auth_passwordResetSent36_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("password/reset/sent/"), DynamicPart("email", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_passwordResetSent36_invoker = createInvoker(
    controllers.Auth.passwordResetSent(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "passwordResetSent",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """password/reset/sent/$email<[^/]+>"""
    )
  )

  // @LINE:52
  private[this] lazy val controllers_Auth_passwordResetConfirm37_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("password/reset/confirm/"), DynamicPart("token", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_passwordResetConfirm37_invoker = createInvoker(
    controllers.Auth.passwordResetConfirm(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "passwordResetConfirm",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """password/reset/confirm/$token<[^/]+>"""
    )
  )

  // @LINE:53
  private[this] lazy val controllers_Auth_passwordResetConfirmApply38_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("password/reset/confirm/"), DynamicPart("token", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_passwordResetConfirmApply38_invoker = createInvoker(
    controllers.Auth.passwordResetConfirmApply(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "passwordResetConfirmApply",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """password/reset/confirm/$token<[^/]+>"""
    )
  )

  // @LINE:54
  private[this] lazy val controllers_Auth_setFingerprint39_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("set-fingerprint/"), DynamicPart("hash", """[^/]+""",true), StaticPart("/"), DynamicPart("ms", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_setFingerprint39_invoker = createInvoker(
    controllers.Auth.setFingerprint(fakeValue[String], fakeValue[Int]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "setFingerprint",
      Seq(classOf[String], classOf[Int]),
      "POST",
      """""",
      this.prefix + """set-fingerprint/$hash<[^/]+>/$ms<[^/]+>"""
    )
  )

  // @LINE:58
  private[this] lazy val controllers_ImageCtrl_get40_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getimage/"), DynamicPart("size", """[^/]+""",true), StaticPart("/"), DynamicPart("uuid", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ImageCtrl_get40_invoker = createInvoker(
    play.api.Play.maybeApplication.map(_.injector).getOrElse(play.api.inject.NewInstanceInjector).instanceOf(classOf[controllers.ImageCtrl]).get(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ImageCtrl",
      "get",
      Seq(classOf[String], classOf[String]),
      "GET",
      """ Images
GET      //static.luanlv.info/getimage/:size/:uuid        @controllers.ImageCtrl.get(size: String, uuid: String)""",
      this.prefix + """getimage/$size<[^/]+>/$uuid<[^/]+>"""
    )
  )

  // @LINE:59
  private[this] lazy val controllers_ImageCtrl_upload41_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("upload/image")))
  )
  private[this] lazy val controllers_ImageCtrl_upload41_invoker = createInvoker(
    play.api.Play.maybeApplication.map(_.injector).getOrElse(play.api.inject.NewInstanceInjector).instanceOf(classOf[controllers.ImageCtrl]).upload,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ImageCtrl",
      "upload",
      Nil,
      "POST",
      """""",
      this.prefix + """upload/image"""
    )
  )

  // @LINE:62
  private[this] lazy val controllers_Relation_follow42_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/follow/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_follow42_invoker = createInvoker(
    controllers.Relation.follow(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "follow",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/follow/$userId<[^/]+>"""
    )
  )

  // @LINE:63
  private[this] lazy val controllers_Relation_request43_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/request/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_request43_invoker = createInvoker(
    controllers.Relation.request(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "request",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/request/$userId<[^/]+>"""
    )
  )

  // @LINE:64
  private[this] lazy val controllers_Relation_unfollow44_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/unfollow/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_unfollow44_invoker = createInvoker(
    controllers.Relation.unfollow(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "unfollow",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/unfollow/$userId<[^/]+>"""
    )
  )

  // @LINE:65
  private[this] lazy val controllers_Relation_unrequest45_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/unrequest/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_unrequest45_invoker = createInvoker(
    controllers.Relation.unrequest(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "unrequest",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/unrequest/$userId<[^/]+>"""
    )
  )

  // @LINE:66
  private[this] lazy val controllers_Relation_reject46_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/reject/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_reject46_invoker = createInvoker(
    controllers.Relation.reject(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "reject",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/reject/$userId<[^/]+>"""
    )
  )

  // @LINE:68
  private[this] lazy val controllers_Relation_friend47_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/friend/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_friend47_invoker = createInvoker(
    controllers.Relation.friend(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "friend",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/friend/$userId<[^/]+>"""
    )
  )

  // @LINE:69
  private[this] lazy val controllers_Relation_unfriend48_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/unfriend/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_unfriend48_invoker = createInvoker(
    controllers.Relation.unfriend(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "unfriend",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/unfriend/$userId<[^/]+>"""
    )
  )

  // @LINE:71
  private[this] lazy val controllers_Relation_block49_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/block/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_block49_invoker = createInvoker(
    controllers.Relation.block(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "block",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/block/$userId<[^/]+>"""
    )
  )

  // @LINE:72
  private[this] lazy val controllers_Relation_unblock50_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rel/unblock/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Relation_unblock50_invoker = createInvoker(
    controllers.Relation.unblock(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Relation",
      "unblock",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """rel/unblock/$userId<[^/]+>"""
    )
  )

  // @LINE:74
  private[this] lazy val controllers_API_getSelfInformation51_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("settings")))
  )
  private[this] lazy val controllers_API_getSelfInformation51_invoker = createInvoker(
    controllers.API.getSelfInformation,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "getSelfInformation",
      Nil,
      "POST",
      """""",
      this.prefix + """settings"""
    )
  )

  // @LINE:75
  private[this] lazy val controllers_API_updateInformation52_route: Route.ParamsExtractor = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("settings/updateInfo")))
  )
  private[this] lazy val controllers_API_updateInformation52_invoker = createInvoker(
    controllers.API.updateInformation,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "updateInformation",
      Nil,
      "POST",
      """""",
      this.prefix + """settings/updateInfo"""
    )
  )

  // @LINE:77
  private[this] lazy val controllers_API_getInformationUser53_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/getUser/"), DynamicPart("user", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_getInformationUser53_invoker = createInvoker(
    controllers.API.getInformationUser(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "getInformationUser",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """api/getUser/$user<[^/]+>"""
    )
  )

  // @LINE:78
  private[this] lazy val controllers_API_getListFollower54_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/listfollow/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_getListFollower54_invoker = createInvoker(
    controllers.API.getListFollower(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "getListFollower",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """api/listfollow/$userId<[^/]+>"""
    )
  )

  // @LINE:79
  private[this] lazy val controllers_API_getListFriend55_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/listfriend/"), DynamicPart("userId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_API_getListFriend55_invoker = createInvoker(
    controllers.API.getListFriend(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.API",
      "getListFriend",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """api/listfriend/$userId<[^/]+>"""
    )
  )

  // @LINE:83
  private[this] lazy val controllers_Assets_at56_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("google5106a805f9682e1e.html")))
  )
  private[this] lazy val controllers_Assets_at56_invoker = createInvoker(
    controllers.Assets.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """API
 Assets""",
      this.prefix + """google5106a805f9682e1e.html"""
    )
  )

  // @LINE:84
  private[this] lazy val controllers_Assets_at57_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("robots.txt")))
  )
  private[this] lazy val controllers_Assets_at57_invoker = createInvoker(
    controllers.Assets.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """robots.txt"""
    )
  )

  // @LINE:85
  private[this] lazy val controllers_Assets_at58_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sitemap.xml")))
  )
  private[this] lazy val controllers_Assets_at58_invoker = createInvoker(
    controllers.Assets.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """sitemap.xml"""
    )
  )

  // @LINE:86
  private[this] lazy val controllers_Assets_at59_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at59_invoker = createInvoker(
    controllers.Assets.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """assets/$file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:4
    case controllers_Application_index0_route(params) =>
      call { 
        controllers_Application_index0_invoker.call(controllers.Application.index)
      }
  
    // @LINE:5
    case controllers_Application_post1_route(params) =>
      call(params.fromPath[String]("postId", None)) { (postId) =>
        controllers_Application_post1_invoker.call(controllers.Application.post(postId))
      }
  
    // @LINE:6
    case controllers_Application_index2_route(params) =>
      call { 
        controllers_Application_index2_invoker.call(controllers.Application.index)
      }
  
    // @LINE:7
    case controllers_Application_index3_route(params) =>
      call { 
        controllers_Application_index3_invoker.call(controllers.Application.index)
      }
  
    // @LINE:9
    case controllers_API_doAsk4_route(params) =>
      call { 
        controllers_API_doAsk4_invoker.call(controllers.API.doAsk)
      }
  
    // @LINE:11
    case controllers_API_newQuestion5_route(params) =>
      call { 
        controllers_API_newQuestion5_invoker.call(controllers.API.newQuestion)
      }
  
    // @LINE:12
    case controllers_API_hotQuestion6_route(params) =>
      call { 
        controllers_API_hotQuestion6_invoker.call(controllers.API.hotQuestion)
      }
  
    // @LINE:14
    case controllers_API_getAllQuestion7_route(params) =>
      call { 
        controllers_API_getAllQuestion7_invoker.call(controllers.API.getAllQuestion)
      }
  
    // @LINE:15
    case controllers_API_getQuestion8_route(params) =>
      call(params.fromPath[String]("questionId", None)) { (questionId) =>
        controllers_API_getQuestion8_invoker.call(controllers.API.getQuestion(questionId))
      }
  
    // @LINE:17
    case controllers_Chat_chatRooms9_route(params) =>
      call { 
        controllers_Chat_chatRooms9_invoker.call(controllers.Chat.chatRooms)
      }
  
    // @LINE:18
    case controllers_Chat_chatRoom10_route(params) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_Chat_chatRoom10_invoker.call(controllers.Chat.chatRoom(id))
      }
  
    // @LINE:19
    case controllers_Application_json11_route(params) =>
      call { 
        controllers_Application_json11_invoker.call(controllers.Application.json)
      }
  
    // @LINE:20
    case controllers_Application_index12_route(params) =>
      call { 
        controllers_Application_index12_invoker.call(controllers.Application.index)
      }
  
    // @LINE:22
    case controllers_Application_user13_route(params) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_Application_user13_invoker.call(controllers.Application.user(username))
      }
  
    // @LINE:23
    case controllers_User_showMini14_route(params) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_User_showMini14_invoker.call(controllers.User.showMini(username))
      }
  
    // @LINE:25
    case controllers_API_doPost15_route(params) =>
      call { 
        controllers_API_doPost15_invoker.call(controllers.API.doPost)
      }
  
    // @LINE:26
    case controllers_API_getPost16_route(params) =>
      call(params.fromPath[String]("username", None)) { (username) =>
        controllers_API_getPost16_invoker.call(controllers.API.getPost(username))
      }
  
    // @LINE:27
    case controllers_API_viewPost17_route(params) =>
      call(params.fromPath[String]("postId", None)) { (postId) =>
        controllers_API_viewPost17_invoker.call(controllers.API.viewPost(postId))
      }
  
    // @LINE:28
    case controllers_API_viewQuestion18_route(params) =>
      call(params.fromPath[String]("questionId", None)) { (questionId) =>
        controllers_API_viewQuestion18_invoker.call(controllers.API.viewQuestion(questionId))
      }
  
    // @LINE:30
    case controllers_API_likePost19_route(params) =>
      call(params.fromPath[String]("postId", None)) { (postId) =>
        controllers_API_likePost19_invoker.call(controllers.API.likePost(postId))
      }
  
    // @LINE:31
    case controllers_API_unlikePost20_route(params) =>
      call(params.fromPath[String]("postId", None)) { (postId) =>
        controllers_API_unlikePost20_invoker.call(controllers.API.unlikePost(postId))
      }
  
    // @LINE:32
    case controllers_API_vote21_route(params) =>
      call(params.fromPath[String]("questionId", None)) { (questionId) =>
        controllers_API_vote21_invoker.call(controllers.API.vote(questionId))
      }
  
    // @LINE:33
    case controllers_API_voteAnswer22_route(params) =>
      call(params.fromPath[String]("answerId", None)) { (answerId) =>
        controllers_API_voteAnswer22_invoker.call(controllers.API.voteAnswer(answerId))
      }
  
    // @LINE:36
    case controllers_Main_websocket23_route(params) =>
      call { 
        controllers_Main_websocket23_invoker.call(controllers.Main.websocket)
      }
  
    // @LINE:38
    case controllers_Monitor_index24_route(params) =>
      call { 
        controllers_Monitor_index24_invoker.call(controllers.Monitor.index)
      }
  
    // @LINE:39
    case controllers_Monitor_websocket25_route(params) =>
      call { 
        controllers_Monitor_websocket25_invoker.call(controllers.Monitor.websocket)
      }
  
    // @LINE:40
    case controllers_Monitor_status26_route(params) =>
      call { 
        controllers_Monitor_status26_invoker.call(controllers.Monitor.status)
      }
  
    // @LINE:42
    case controllers_Auth_login27_route(params) =>
      call { 
        controllers_Auth_login27_invoker.call(controllers.Auth.login)
      }
  
    // @LINE:43
    case controllers_Auth_authenticate28_route(params) =>
      call { 
        controllers_Auth_authenticate28_invoker.call(controllers.Auth.authenticate)
      }
  
    // @LINE:44
    case controllers_Auth_logout29_route(params) =>
      call { 
        controllers_Auth_logout29_invoker.call(controllers.Auth.logout)
      }
  
    // @LINE:45
    case controllers_Auth_signup30_route(params) =>
      call { 
        controllers_Auth_signup30_invoker.call(controllers.Auth.signup)
      }
  
    // @LINE:46
    case controllers_Auth_signupPost31_route(params) =>
      call { 
        controllers_Auth_signupPost31_invoker.call(controllers.Auth.signupPost)
      }
  
    // @LINE:47
    case controllers_Auth_checkYourEmail32_route(params) =>
      call(params.fromPath[String]("name", None)) { (name) =>
        controllers_Auth_checkYourEmail32_invoker.call(controllers.Auth.checkYourEmail(name))
      }
  
    // @LINE:48
    case controllers_Auth_signupConfirmEmail33_route(params) =>
      call(params.fromPath[String]("token", None)) { (token) =>
        controllers_Auth_signupConfirmEmail33_invoker.call(controllers.Auth.signupConfirmEmail(token))
      }
  
    // @LINE:49
    case controllers_Auth_passwordReset34_route(params) =>
      call { 
        controllers_Auth_passwordReset34_invoker.call(controllers.Auth.passwordReset)
      }
  
    // @LINE:50
    case controllers_Auth_passwordResetApply35_route(params) =>
      call { 
        controllers_Auth_passwordResetApply35_invoker.call(controllers.Auth.passwordResetApply)
      }
  
    // @LINE:51
    case controllers_Auth_passwordResetSent36_route(params) =>
      call(params.fromPath[String]("email", None)) { (email) =>
        controllers_Auth_passwordResetSent36_invoker.call(controllers.Auth.passwordResetSent(email))
      }
  
    // @LINE:52
    case controllers_Auth_passwordResetConfirm37_route(params) =>
      call(params.fromPath[String]("token", None)) { (token) =>
        controllers_Auth_passwordResetConfirm37_invoker.call(controllers.Auth.passwordResetConfirm(token))
      }
  
    // @LINE:53
    case controllers_Auth_passwordResetConfirmApply38_route(params) =>
      call(params.fromPath[String]("token", None)) { (token) =>
        controllers_Auth_passwordResetConfirmApply38_invoker.call(controllers.Auth.passwordResetConfirmApply(token))
      }
  
    // @LINE:54
    case controllers_Auth_setFingerprint39_route(params) =>
      call(params.fromPath[String]("hash", None), params.fromPath[Int]("ms", None)) { (hash, ms) =>
        controllers_Auth_setFingerprint39_invoker.call(controllers.Auth.setFingerprint(hash, ms))
      }
  
    // @LINE:58
    case controllers_ImageCtrl_get40_route(params) =>
      call(params.fromPath[String]("size", None), params.fromPath[String]("uuid", None)) { (size, uuid) =>
        controllers_ImageCtrl_get40_invoker.call(play.api.Play.maybeApplication.map(_.injector).getOrElse(play.api.inject.NewInstanceInjector).instanceOf(classOf[controllers.ImageCtrl]).get(size, uuid))
      }
  
    // @LINE:59
    case controllers_ImageCtrl_upload41_route(params) =>
      call { 
        controllers_ImageCtrl_upload41_invoker.call(play.api.Play.maybeApplication.map(_.injector).getOrElse(play.api.inject.NewInstanceInjector).instanceOf(classOf[controllers.ImageCtrl]).upload)
      }
  
    // @LINE:62
    case controllers_Relation_follow42_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_follow42_invoker.call(controllers.Relation.follow(userId))
      }
  
    // @LINE:63
    case controllers_Relation_request43_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_request43_invoker.call(controllers.Relation.request(userId))
      }
  
    // @LINE:64
    case controllers_Relation_unfollow44_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_unfollow44_invoker.call(controllers.Relation.unfollow(userId))
      }
  
    // @LINE:65
    case controllers_Relation_unrequest45_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_unrequest45_invoker.call(controllers.Relation.unrequest(userId))
      }
  
    // @LINE:66
    case controllers_Relation_reject46_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_reject46_invoker.call(controllers.Relation.reject(userId))
      }
  
    // @LINE:68
    case controllers_Relation_friend47_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_friend47_invoker.call(controllers.Relation.friend(userId))
      }
  
    // @LINE:69
    case controllers_Relation_unfriend48_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_unfriend48_invoker.call(controllers.Relation.unfriend(userId))
      }
  
    // @LINE:71
    case controllers_Relation_block49_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_block49_invoker.call(controllers.Relation.block(userId))
      }
  
    // @LINE:72
    case controllers_Relation_unblock50_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_Relation_unblock50_invoker.call(controllers.Relation.unblock(userId))
      }
  
    // @LINE:74
    case controllers_API_getSelfInformation51_route(params) =>
      call { 
        controllers_API_getSelfInformation51_invoker.call(controllers.API.getSelfInformation)
      }
  
    // @LINE:75
    case controllers_API_updateInformation52_route(params) =>
      call { 
        controllers_API_updateInformation52_invoker.call(controllers.API.updateInformation)
      }
  
    // @LINE:77
    case controllers_API_getInformationUser53_route(params) =>
      call(params.fromPath[String]("user", None)) { (user) =>
        controllers_API_getInformationUser53_invoker.call(controllers.API.getInformationUser(user))
      }
  
    // @LINE:78
    case controllers_API_getListFollower54_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_API_getListFollower54_invoker.call(controllers.API.getListFollower(userId))
      }
  
    // @LINE:79
    case controllers_API_getListFriend55_route(params) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_API_getListFriend55_invoker.call(controllers.API.getListFriend(userId))
      }
  
    // @LINE:83
    case controllers_Assets_at56_route(params) =>
      call(Param[String]("path", Right("/public")), Param[String]("file", Right("google5106a805f9682e1e.html"))) { (path, file) =>
        controllers_Assets_at56_invoker.call(controllers.Assets.at(path, file))
      }
  
    // @LINE:84
    case controllers_Assets_at57_route(params) =>
      call(Param[String]("path", Right("/public")), Param[String]("file", Right("robots.txt"))) { (path, file) =>
        controllers_Assets_at57_invoker.call(controllers.Assets.at(path, file))
      }
  
    // @LINE:85
    case controllers_Assets_at58_route(params) =>
      call(Param[String]("path", Right("/public")), Param[String]("file", Right("sitemap.xml"))) { (path, file) =>
        controllers_Assets_at58_invoker.call(controllers.Assets.at(path, file))
      }
  
    // @LINE:86
    case controllers_Assets_at59_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at59_invoker.call(controllers.Assets.at(path, file))
      }
  }
}