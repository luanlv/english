
// @GENERATOR:play-routes-compiler
// @SOURCE:/mnt/ramdisk/mainapp/conf/routes
// @DATE:Thu May 26 05:20:56 ICT 2016

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:4
package controllers {

  // @LINE:83
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:83
    def at(file:String): Call = {
    
      (file: @unchecked) match {
      
        // @LINE:83
        case (file) if file == "google5106a805f9682e1e.html" =>
          implicit val _rrc = new ReverseRouteContext(Map(("path", "/public"), ("file", "google5106a805f9682e1e.html")))
          Call("GET", _prefix + { _defaultPrefix } + "google5106a805f9682e1e.html")
      
        // @LINE:84
        case (file) if file == "robots.txt" =>
          implicit val _rrc = new ReverseRouteContext(Map(("path", "/public"), ("file", "robots.txt")))
          Call("GET", _prefix + { _defaultPrefix } + "robots.txt")
      
        // @LINE:85
        case (file) if file == "sitemap.xml" =>
          implicit val _rrc = new ReverseRouteContext(Map(("path", "/public"), ("file", "sitemap.xml")))
          Call("GET", _prefix + { _defaultPrefix } + "sitemap.xml")
      
        // @LINE:86
        case (file)  =>
          implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
          Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
      
      }
    
    }
  
  }

  // @LINE:17
  class ReverseChat(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def chatRoom(id:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "chatroom/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
    }
  
    // @LINE:17
    def chatRooms(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "chatroom")
    }
  
  }

  // @LINE:58
  class ReverseImageCtrl(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:59
    def upload(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "upload/image")
    }
  
    // @LINE:58
    def get(size:String, uuid:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "getimage/" + implicitly[PathBindable[String]].unbind("size", dynamicString(size)) + "/" + implicitly[PathBindable[String]].unbind("uuid", dynamicString(uuid)))
    }
  
  }

  // @LINE:42
  class ReverseAuth(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:51
    def passwordResetSent(email:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "password/reset/sent/" + implicitly[PathBindable[String]].unbind("email", dynamicString(email)))
    }
  
    // @LINE:49
    def passwordReset(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "password/reset")
    }
  
    // @LINE:47
    def checkYourEmail(name:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signup/check-your-email/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)))
    }
  
    // @LINE:52
    def passwordResetConfirm(token:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "password/reset/confirm/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:45
    def signup(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signup")
    }
  
    // @LINE:50
    def passwordResetApply(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "password/reset/send")
    }
  
    // @LINE:44
    def logout(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "logout")
    }
  
    // @LINE:53
    def passwordResetConfirmApply(token:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "password/reset/confirm/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:46
    def signupPost(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "signup")
    }
  
    // @LINE:54
    def setFingerprint(hash:String, ms:Int): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "set-fingerprint/" + implicitly[PathBindable[String]].unbind("hash", dynamicString(hash)) + "/" + implicitly[PathBindable[Int]].unbind("ms", ms))
    }
  
    // @LINE:48
    def signupConfirmEmail(token:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signup/confirm/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:43
    def authenticate(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
    // @LINE:42
    def login(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "login")
    }
  
  }

  // @LINE:38
  class ReverseMonitor(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def status(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "status")
    }
  
    // @LINE:38
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "monitor")
    }
  
    // @LINE:39
    def websocket(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "monitor/socket")
    }
  
  }

  // @LINE:62
  class ReverseRelation(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:69
    def unfriend(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/unfriend/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:68
    def friend(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/friend/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:63
    def request(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/request/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:72
    def unblock(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/unblock/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:65
    def unrequest(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/unrequest/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:71
    def block(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/block/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:64
    def unfollow(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/unfollow/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:66
    def reject(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "rel/reject/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:62
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
  
    // @LINE:22
    def user(username:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "@/" + implicitly[PathBindable[String]].unbind("username", dynamicString(username)))
    }
  
    // @LINE:19
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

  // @LINE:23
  class ReverseUser(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:23
    def showMini(username:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "@/" + implicitly[PathBindable[String]].unbind("username", dynamicString(username)) + "/mini")
    }
  
  }

  // @LINE:36
  class ReverseMain(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:36
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

  
    // @LINE:75
    def updateInformation(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "settings/updateInfo")
    }
  
    // @LINE:15
    def getQuestion(questionId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "qa/" + implicitly[PathBindable[String]].unbind("questionId", dynamicString(questionId)))
    }
  
    // @LINE:33
    def voteAnswer(answerId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "vote/answer/" + implicitly[PathBindable[String]].unbind("answerId", dynamicString(answerId)))
    }
  
    // @LINE:31
    def unlikePost(postId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "unlike/post/" + implicitly[PathBindable[String]].unbind("postId", dynamicString(postId)))
    }
  
    // @LINE:14
    def getAllQuestion(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "qa/allquestion")
    }
  
    // @LINE:79
    def getListFriend(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/listfriend/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:12
    def hotQuestion(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "qa/hotquestion")
    }
  
    // @LINE:74
    def getSelfInformation(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "settings")
    }
  
    // @LINE:30
    def likePost(postId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "like/post/" + implicitly[PathBindable[String]].unbind("postId", dynamicString(postId)))
    }
  
    // @LINE:11
    def newQuestion(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "qa/newquestion")
    }
  
    // @LINE:25
    def doPost(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "post")
    }
  
    // @LINE:26
    def getPost(username:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "post/" + implicitly[PathBindable[String]].unbind("username", dynamicString(username)))
    }
  
    // @LINE:28
    def viewQuestion(questionId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "viewquestion/" + implicitly[PathBindable[String]].unbind("questionId", dynamicString(questionId)))
    }
  
    // @LINE:27
    def viewPost(postId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "viewpost/" + implicitly[PathBindable[String]].unbind("postId", dynamicString(postId)))
    }
  
    // @LINE:9
    def doAsk(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "qa/new")
    }
  
    // @LINE:77
    def getInformationUser(user:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/getUser/" + implicitly[PathBindable[String]].unbind("user", dynamicString(user)))
    }
  
    // @LINE:78
    def getListFollower(userId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/listfollow/" + implicitly[PathBindable[String]].unbind("userId", dynamicString(userId)))
    }
  
    // @LINE:32
    def vote(questionId:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "vote/question/" + implicitly[PathBindable[String]].unbind("questionId", dynamicString(questionId)))
    }
  
  }


}