
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/ramdisk/mainapp/conf/routes
// @DATE:Sun Apr 17 11:11:24 ICT 2016

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:4
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:78
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:78
    def at: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.at",
      """
        function(file) {
        
          if (file == """ + implicitly[JavascriptLiteral[String]].to("google5106a805f9682e1e.html") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "google5106a805f9682e1e.html"})
          }
        
          if (file == """ + implicitly[JavascriptLiteral[String]].to("robots.txt") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "robots.txt"})
          }
        
          if (file == """ + implicitly[JavascriptLiteral[String]].to("sitemap.xml") + """) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sitemap.xml"})
          }
        
          if (true) {
            return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
          }
        
        }
      """
    )
  
  }

  // @LINE:14
  class ReverseChat(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def chatRoom: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Chat.chatRoom",
      """
        function(id) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "chatroom/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
        }
      """
    )
  
    // @LINE:14
    def chatRooms: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Chat.chatRooms",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "chatroom"})
        }
      """
    )
  
  }

  // @LINE:55
  class ReverseImageCtrl(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:56
    def upload: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ImageCtrl.upload",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "upload/image"})
        }
      """
    )
  
    // @LINE:55
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ImageCtrl.get",
      """
        function(size,uuid) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getimage/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("size", encodeURIComponent(size)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uuid", encodeURIComponent(uuid))})
        }
      """
    )
  
  }

  // @LINE:39
  class ReverseAuth(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:48
    def passwordResetSent: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordResetSent",
      """
        function(email) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset/sent/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("email", encodeURIComponent(email))})
        }
      """
    )
  
    // @LINE:46
    def passwordReset: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordReset",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset"})
        }
      """
    )
  
    // @LINE:44
    def checkYourEmail: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.checkYourEmail",
      """
        function(name) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup/check-your-email/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
        }
      """
    )
  
    // @LINE:49
    def passwordResetConfirm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordResetConfirm",
      """
        function(token) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset/confirm/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:42
    def signup: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signup",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
        }
      """
    )
  
    // @LINE:47
    def passwordResetApply: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordResetApply",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset/send"})
        }
      """
    )
  
    // @LINE:41
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.logout",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
        }
      """
    )
  
    // @LINE:50
    def passwordResetConfirmApply: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordResetConfirmApply",
      """
        function(token) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset/confirm/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:43
    def signupPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signupPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
        }
      """
    )
  
    // @LINE:51
    def setFingerprint: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.setFingerprint",
      """
        function(hash,ms) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "set-fingerprint/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("hash", encodeURIComponent(hash)) + "/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("ms", ms)})
        }
      """
    )
  
    // @LINE:45
    def signupConfirmEmail: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signupConfirmEmail",
      """
        function(token) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup/confirm/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:40
    def authenticate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.authenticate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
    // @LINE:39
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.login",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
  }

  // @LINE:35
  class ReverseMonitor(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:37
    def status: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Monitor.status",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "status"})
        }
      """
    )
  
    // @LINE:35
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Monitor.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "monitor"})
        }
      """
    )
  
    // @LINE:36
    def websocket: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Monitor.websocket",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "monitor/socket"})
        }
      """
    )
  
  }

  // @LINE:59
  class ReverseRelation(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:66
    def unfriend: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.unfriend",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/unfriend/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:65
    def friend: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.friend",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/friend/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:60
    def request: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.request",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/request/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:69
    def unblock: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.unblock",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/unblock/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:62
    def unrequest: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.unrequest",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/unrequest/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:68
    def block: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.block",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/block/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:61
    def unfollow: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.unfollow",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/unfollow/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:63
    def reject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.reject",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/reject/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:59
    def follow: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.follow",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/follow/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
  }

  // @LINE:4
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def post: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.post",
      """
        function(postId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "post/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("postId", encodeURIComponent(postId))})
        }
      """
    )
  
    // @LINE:19
    def user: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.user",
      """
        function(username) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "@/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("username", encodeURIComponent(username))})
        }
      """
    )
  
    // @LINE:16
    def json: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.json",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "json"})
        }
      """
    )
  
    // @LINE:4
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.index",
      """
        function() {
        
          if (true) {
            return _wA({method:"GET", url:"""" + _prefix + """"})
          }
        
        }
      """
    )
  
  }

  // @LINE:20
  class ReverseUser(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def showMini: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.User.showMini",
      """
        function(username) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "@/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("username", encodeURIComponent(username)) + "/mini"})
        }
      """
    )
  
  }

  // @LINE:33
  class ReverseMain(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def websocket: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Main.websocket",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "socket"})
        }
      """
    )
  
  }

  // @LINE:9
  class ReverseAPI(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:72
    def updateInformation: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.updateInformation",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "settings/updateInfo"})
        }
      """
    )
  
    // @LINE:12
    def getQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getQuestion",
      """
        function(questionId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "qa/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("questionId", encodeURIComponent(questionId))})
        }
      """
    )
  
    // @LINE:30
    def voteAnswer: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.voteAnswer",
      """
        function(answerId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "vote/answer/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("answerId", encodeURIComponent(answerId))})
        }
      """
    )
  
    // @LINE:28
    def unlikePost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.unlikePost",
      """
        function(postId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "unlike/post/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("postId", encodeURIComponent(postId))})
        }
      """
    )
  
    // @LINE:11
    def getAllQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getAllQuestion",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "qa/allquestion"})
        }
      """
    )
  
    // @LINE:71
    def getSelfInformation: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getSelfInformation",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "settings"})
        }
      """
    )
  
    // @LINE:27
    def likePost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.likePost",
      """
        function(postId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "like/post/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("postId", encodeURIComponent(postId))})
        }
      """
    )
  
    // @LINE:22
    def doPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.doPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "post"})
        }
      """
    )
  
    // @LINE:23
    def getPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getPost",
      """
        function(username) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "post/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("username", encodeURIComponent(username))})
        }
      """
    )
  
    // @LINE:25
    def viewQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.viewQuestion",
      """
        function(questionId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewquestion/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("questionId", encodeURIComponent(questionId))})
        }
      """
    )
  
    // @LINE:24
    def viewPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.viewPost",
      """
        function(postId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewpost/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("postId", encodeURIComponent(postId))})
        }
      """
    )
  
    // @LINE:9
    def doAsk: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.doAsk",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "qa/new"})
        }
      """
    )
  
    // @LINE:74
    def getInformationUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getInformationUser",
      """
        function(user) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/getUser/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("user", encodeURIComponent(user))})
        }
      """
    )
  
    // @LINE:29
    def vote: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.vote",
      """
        function(questionId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "vote/question/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("questionId", encodeURIComponent(questionId))})
        }
      """
    )
  
  }


}