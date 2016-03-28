
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/ramdisk/mainapp/conf/routes
// @DATE:Sat Mar 26 00:44:34 ICT 2016

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:4
package controllers {

  // @LINE:78
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:78
    def at(file:String): Call = {
    
      (file: @unchecked) match {
      
        // @LINE:78
        case (file) if file == "google5106a805f9682e1e.html" =>
          implicit val _rrc = new ReverseRouteContext(Map(("path", "/public"), ("file", "google5106a805f9682e1e.html")))
          Call("GET", _prefix + { _defaultPrefix } + "google5106a805f9682e1e.html")
      
        // @LINE:79
        case (file) if file == "robots.txt" =>
          implicit val _rrc = new ReverseRouteContext(Map(("path", "/public"), ("file", "robots.txt")))
          Call("GET", _prefix + { _defaultPrefix } + "robots.txt")
      
        // @LINE:80
        case (file) if file == "sitemap.xml" =>
          implicit val _rrc = new ReverseRouteContext(Map(("path", "/public"), ("file", "sitemap.xml")))
          Call("GET", _prefix + { _defaultPrefix } + "sitemap.xml")
      
        // @LINE:81
        case (file)  =>
          implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
          Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
      
      }
    
    }
  
  }

  // @LINE:14
  class ReverseChat(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def chatRoom(id:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "chatroom/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
    }
  
    // @LINE:14
    def chatRooms(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "chatroom")
    }
  
  }

  // @LINE:55
  class ReverseImageCtrl(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:56
    def upload(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "upload/image")
    }
  
    // @LINE:55
    def get(size:String, uuid:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "getimage/" + implicitly[PathBindable[String]].unbind("size", dynamicString(size)) + "/" + implicitly[PathBindable[String]].unbind("uuid", dynamicString(uuid)))
    }
  
  }

  // @LINE:39
  class ReverseAuth(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:48
    def passwordResetSent(email:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "password/reset/sent/" + implicitly[PathBindable[String]].unbind("email", dynamicString(email)))
    }
  
    // @LINE:46
    def passwordReset(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "password/reset")
    }
  
    // @LINE:44
    def checkYourEmail(name:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signup/check-your-email/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)))
    }
  
    // @LINE:49
    def passwordResetConfirm(token:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "password/reset/confirm/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:42
    def signup(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signup")
    }
  
    // @LINE:47
    def passwordResetApply(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "password/reset/send")
    }
  
    // @LINE:41
    def logout(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "logout")
    }
  
    // @LINE:50
    def passwordResetConfirmApply(token:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "password/reset/confirm/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:43
    def signupPost(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "signup")
    }
  
    // @LINE:51
    def setFingerprint(hash:String, ms:Int): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "set-fingerprint/" + implicitly[PathBindable[String]].unbind("hash", dynamicString(hash)) + "/" + implicitly[PathBindable[Int]].unbind("ms", ms))
    }
  
    // @LINE:45
    def signupConfirmEmail(token:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signup/confirm/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:40
    def authenticate(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
    // @LINE:39
    def login(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "login")
    }
  
  }

  // @LINE:35
  class ReverseMonitor(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:37
    def status(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "status")
    }
  
    // @LINE:35
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "monitor")
    }
  
    // @LINE:36
    def websocket(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "monitor/socket")
    }
  
  }

  // @LINE:59
  class ReverseRelation(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:66
    def unfriend(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/unfriend/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:65
    def friend(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/friend/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:60
    def request(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/request/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:69
    def unblock(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/unblock/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:62
    def unrequest(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/unrequest/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:68
    def block(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/block/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:61
    def unfollow(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/unfollow/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:63
    def reject(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/reject/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:59
    def follow(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/follow/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
  }

  // @LINE:4
  class ReverseApplication(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def post(postId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "post/" + implicitly[PathBindable[String]].unbind("postId", dynamicString(postId)))
    }
  
    // @LINE:19
    def user(username:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "@/" + implicitly[PathBindable[String]].unbind("username", dynamicString(username)))
    }
  
    // @LINE:16
    def json(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "json")
    }
  
    // @LINE:4
    def index(): Call = {
    
      () match {
      
        // @LINE:4
        case ()  =>
          import ReverseRouteContext.empty
          Call("GET", _prefix)
      
      }
    
    }
  
  }

  // @LINE:20
  class ReverseUser(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def showMini(username:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "@/" + implicitly[PathBindable[String]].unbind("username", dynamicString(username)) + "/mini")
    }
  
  }

  // @LINE:33
  class ReverseMain(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def websocket(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "socket")
    }
  
  }

  // @LINE:9
  class ReverseAPI(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:72
    def updateInformation(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "settings/updateInfo")
    }
  
    // @LINE:12
    def getQuestion(questionId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "qa/" + implicitly[PathBindable[String]].unbind("questionId", dynamicString(questionId)))
    }
  
    // @LINE:30
    def voteAnswer(answerId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "vote/answer/" + implicitly[PathBindable[String]].unbind("answerId", dynamicString(answerId)))
    }
  
    // @LINE:28
    def unlikePost(postId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "unlike/post/" + implicitly[PathBindable[String]].unbind("postId", dynamicString(postId)))
    }
  
    // @LINE:11
    def getAllQuestion(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "qa/allquestion")
    }
  
    // @LINE:71
    def getSelfInformation(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "settings")
    }
  
    // @LINE:27
    def likePost(postId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "like/post/" + implicitly[PathBindable[String]].unbind("postId", dynamicString(postId)))
    }
  
    // @LINE:22
    def doPost(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "post")
    }
  
    // @LINE:23
    def getPost(username:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "post/" + implicitly[PathBindable[String]].unbind("username", dynamicString(username)))
    }
  
    // @LINE:25
    def viewQuestion(questionId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "viewquestion/" + implicitly[PathBindable[String]].unbind("questionId", dynamicString(questionId)))
    }
  
    // @LINE:24
    def viewPost(postId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "viewpost/" + implicitly[PathBindable[String]].unbind("postId", dynamicString(postId)))
    }
  
    // @LINE:9
    def doAsk(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "qa/new")
    }
  
    // @LINE:74
    def getInformationUser(user:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/getUser/" + implicitly[PathBindable[String]].unbind("user", dynamicString(user)))
    }
  
    // @LINE:29
    def vote(questionId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "vote/question/" + implicitly[PathBindable[String]].unbind("questionId", dynamicString(questionId)))
    }
  
  }


}