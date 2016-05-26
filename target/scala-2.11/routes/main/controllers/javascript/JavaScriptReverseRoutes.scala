
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/ramdisk/mainapp/conf/routes
// @DATE:Thu May 26 05:20:56 ICT 2016

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:4
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:83
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:83
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

  // @LINE:17
  class ReverseChat(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def chatRoom: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Chat.chatRoom",
      """
        function(id) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "chatroom/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
        }
      """
    )
  
    // @LINE:17
    def chatRooms: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Chat.chatRooms",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "chatroom"})
        }
      """
    )
  
  }

  // @LINE:58
  class ReverseImageCtrl(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:59
    def upload: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ImageCtrl.upload",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "upload/image"})
        }
      """
    )
  
    // @LINE:58
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ImageCtrl.get",
      """
        function(size,uuid) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getimage/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("size", encodeURIComponent(size)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uuid", encodeURIComponent(uuid))})
        }
      """
    )
  
  }

  // @LINE:42
  class ReverseAuth(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:51
    def passwordResetSent: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordResetSent",
      """
        function(email) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset/sent/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("email", encodeURIComponent(email))})
        }
      """
    )
  
    // @LINE:49
    def passwordReset: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordReset",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset"})
        }
      """
    )
  
    // @LINE:47
    def checkYourEmail: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.checkYourEmail",
      """
        function(name) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup/check-your-email/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
        }
      """
    )
  
    // @LINE:52
    def passwordResetConfirm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordResetConfirm",
      """
        function(token) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset/confirm/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:45
    def signup: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signup",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
        }
      """
    )
  
    // @LINE:50
    def passwordResetApply: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordResetApply",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset/send"})
        }
      """
    )
  
    // @LINE:44
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.logout",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
        }
      """
    )
  
    // @LINE:53
    def passwordResetConfirmApply: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.passwordResetConfirmApply",
      """
        function(token) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "password/reset/confirm/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:46
    def signupPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signupPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
        }
      """
    )
  
    // @LINE:54
    def setFingerprint: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.setFingerprint",
      """
        function(hash,ms) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "set-fingerprint/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("hash", encodeURIComponent(hash)) + "/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("ms", ms)})
        }
      """
    )
  
    // @LINE:48
    def signupConfirmEmail: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signupConfirmEmail",
      """
        function(token) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup/confirm/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:43
    def authenticate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.authenticate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
    // @LINE:42
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.login",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
  }

  // @LINE:38
  class ReverseMonitor(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def status: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Monitor.status",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "status"})
        }
      """
    )
  
    // @LINE:38
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Monitor.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "monitor"})
        }
      """
    )
  
    // @LINE:39
    def websocket: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Monitor.websocket",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "monitor/socket"})
        }
      """
    )
  
  }

  // @LINE:62
  class ReverseRelation(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:69
    def unfriend: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.unfriend",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/unfriend/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:68
    def friend: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.friend",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/friend/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:63
    def request: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.request",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/request/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:72
    def unblock: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.unblock",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/unblock/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:65
    def unrequest: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.unrequest",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/unrequest/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:71
    def block: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.block",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/block/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:64
    def unfollow: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.unfollow",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/unfollow/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:66
    def reject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Relation.reject",
      """
        function(userId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rel/reject/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:62
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
  
    // @LINE:22
    def user: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.user",
      """
        function(username) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "@/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("username", encodeURIComponent(username))})
        }
      """
    )
  
    // @LINE:19
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

  // @LINE:23
  class ReverseUser(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:23
    def showMini: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.User.showMini",
      """
        function(username) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "@/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("username", encodeURIComponent(username)) + "/mini"})
        }
      """
    )
  
  }

  // @LINE:36
  class ReverseMain(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:36
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

  
    // @LINE:75
    def updateInformation: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.updateInformation",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "settings/updateInfo"})
        }
      """
    )
  
    // @LINE:15
    def getQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getQuestion",
      """
        function(questionId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "qa/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("questionId", encodeURIComponent(questionId))})
        }
      """
    )
  
    // @LINE:33
    def voteAnswer: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.voteAnswer",
      """
        function(answerId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "vote/answer/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("answerId", encodeURIComponent(answerId))})
        }
      """
    )
  
    // @LINE:31
    def unlikePost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.unlikePost",
      """
        function(postId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "unlike/post/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("postId", encodeURIComponent(postId))})
        }
      """
    )
  
    // @LINE:14
    def getAllQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getAllQuestion",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "qa/allquestion"})
        }
      """
    )
  
    // @LINE:79
    def getListFriend: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getListFriend",
      """
        function(userId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/listfriend/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:12
    def hotQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.hotQuestion",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "qa/hotquestion"})
        }
      """
    )
  
    // @LINE:74
    def getSelfInformation: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getSelfInformation",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "settings"})
        }
      """
    )
  
    // @LINE:30
    def likePost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.likePost",
      """
        function(postId) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "like/post/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("postId", encodeURIComponent(postId))})
        }
      """
    )
  
    // @LINE:11
    def newQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.newQuestion",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "qa/newquestion"})
        }
      """
    )
  
    // @LINE:25
    def doPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.doPost",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "post"})
        }
      """
    )
  
    // @LINE:26
    def getPost: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getPost",
      """
        function(username) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "post/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("username", encodeURIComponent(username))})
        }
      """
    )
  
    // @LINE:28
    def viewQuestion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.viewQuestion",
      """
        function(questionId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewquestion/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("questionId", encodeURIComponent(questionId))})
        }
      """
    )
  
    // @LINE:27
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
  
    // @LINE:77
    def getInformationUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getInformationUser",
      """
        function(user) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/getUser/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("user", encodeURIComponent(user))})
        }
      """
    )
  
    // @LINE:78
    def getListFollower: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.API.getListFollower",
      """
        function(userId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/listfollow/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("userId", encodeURIComponent(userId))})
        }
      """
    )
  
    // @LINE:32
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