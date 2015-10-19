(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = api || {};

api.data = "hello"

api.rd = function(name){
  //console.log("Debug: " + name)
};

api.requestWithFeedback = function(args) {
  var data = m.prop();
  var completed = m.prop(false)
  var complete = function() {
    completed(true)
  }
  args.background = true
  args.config = function(xhr) {
    xhr.timeout = 4000
    xhr.ontimeout = function() {
      complete()
      rd.dashboard(function(){ console.log("json error");m.redraw()})
    }
  };
  return {
    request: m.request(args).then(data).then(function(){
      complete()
      rd.dashboard(function(){ console.log("json ok");m.redraw()})
    }),
    data: data,
    ready: completed
  }
};


api.scrollBottom = function(element, isInit, context) {
  if(!isInit){
    element.scrollTop = element.scrollHeight;
  }
  var addLegth = (element.scrollHeight - context.prevHeight) || 0;
  if( element.scrollHeight - element.clientHeight - element.scrollTop < addLegth +  + 10 )
    element.scrollTop = element.scrollHeight;
  context.prevHeight = element.scrollHeight
}

api.setsVal = function(callback) {
  return function(e) {
    m.withAttr("value", callback)(e);
    m.redraw.strategy("none")
  }
}



api.markRead = function(rank){
  if(wsCtrl.data.chat[rank].read === false){
    if(wsCtrl.data.chat[rank].chat[wsCtrl.data.chat[rank].chat.length - 1].f.id == wsCtrl.data.chat[rank].user.id) {
      wsCtrl.send(wsCtrl.sendData("mr", {
        uid: wsCtrl.data.chat[rank].user.id,
        mv: wsCtrl.data.chat[rank].chat[wsCtrl.data.chat[rank].chat.length - 1].mv
      }));
    }
    wsCtrl.data.chat[rank].read = true;
  }
};

api.focusById = function(uid){
  setTimeout(function focusComment(){
    if(document.getElementById(uid) != undefined){
      document.getElementById(uid).focus();
    } else {
      setTimeout(focusComment, 100)
    }
  }, 100);
};

module.exports = api;
},{"../ws/_wsCtrl.js":10}],2:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');

var Chat = {
  controller: function(){
    api.rd("Controller: Chat");
    var ctrl = this;
    m.redraw.strategy("diff");
    ctrl.showChatDock = true;
    ctrl.toggleChatDock = function(){
      ctrl.showChatDock = !ctrl.showChatDock;
    };
    ctrl.makechat = function(user) {
      rd.right(function(){
        var pos = wsCtrl.getPosChat(user);
        wsCtrl.data.chat[pos].display = true;
        wsCtrl.data.chat[pos].hide = false;
        api.focusById(wsCtrl.data.chat[pos].user.id);
        m.redraw()
      })
    };

    ctrl.toggleChat = function(num){
      wsCtrl.data.chat[num].hide = !wsCtrl.data.chat[num].hide;
    };

    ctrl.stopChat = function(num){
      wsCtrl.data.chat[num].display = false;
      wsCtrl.data.chat[num].input('');
    };

    ctrl.add = function (num) {
      var input = wsCtrl.data.chat[num].input().trim();
      //input = input.replace(/\n/g, '');
      if (input) {
        wsCtrl.send(wsCtrl.sendData("m", {to: wsCtrl.data.chat[num].user.id, mes: input}));

        //wsCtrl.data.chat[num].chat.push({f: "u0", mes: input});
        //wsCtrl.data.chat[num].chat.push({f: "u1", mes: "received: " + input});
      }
      wsCtrl.data.chat[num].input('');
    };

    ctrl.save = function(e) {
      if (e.keyCode == 13) {
        //this causes a redraw, since event handlers active auto-redrawing by default
        ctrl.add()
      }
      else {
        //we don't care about other keys, so don't redraw
        m.redraw.strategy("none")
      }
    };

  },
  view: function(ctrl){
    api.rd("redraw Chat: " + redraw.right);
    //if(wsCtrl.data.chat[0] != undefined) console.log(wsCtrl.data.chat);
    redraw.right++;
    return (
        {tag: "div", attrs: {}, children: [
          ctrl.showChatDock?({tag: "div", attrs: {id:"user-list"}, children: [
            {tag: "div", attrs: {className:"ui segment pad0"}, children: [
            {tag: "div", attrs: {className:"ui top attached inverted header chat-title", 
                 onclick:function(){rd.right(ctrl.toggleChatDock())}
            }, children: ["Chat"]}, 
              wsCtrl.data.userOnline.map(function(user){
                  return {tag: "div", attrs: {className:"userOnline", 
                              config:function(el){
                                  $(el).click(function(){
                                    if(wsCtrl.userId.length > 0 && wsCtrl.userId !== user.id) ctrl.makechat(user)
                                  });
                                }
                              
                          }, children: [user.name + ((wsCtrl.userId.length > 0 && wsCtrl.userId == user.id)?" (you)":"")]}
                  }), 
            {tag: "div", attrs: {className:"ui search"}, children: [
              {tag: "div", attrs: {className:"ui left icon input"}, children: [
                {tag: "input", attrs: {className:"search-friend", type:"text", placeholder:"Search"}}, 
                  {tag: "i", attrs: {className:"search icon"}}
              ]}
            ]}
            ]}
          ]}):({tag: "div", attrs: {id:"user-list"}, children: [
            {tag: "div", attrs: {className:"ui top attached inverted header chat-title", 
                 onclick:function(){rd.right(ctrl.toggleChatDock())}
            }, children: ["Chat"]}
          ]}), 
          {tag: "div", attrs: {id:"dock-bot"}, children: [
            wsCtrl.data.chat.map(function(chat, rank){
                return (!chat.display)?"":(
                    {tag: "div", attrs: {className:"chatWr " + (chat.hide?"w2":"w1")}, children: [
                      {tag: "div", attrs: {className:"ui top attached blue inverted header chat-title2" + (chat.read?"":" unread"), 
                           style:!chat.hide?"display: none":"", 
                           onclick:function(){rd.right(ctrl.toggleChat(rank))}
                      }, children: [chat.user.name, 
                        {tag: "i", attrs: {className:"remove icon close-chat", 
                           onclick:function(){rd.right(ctrl.stopChat(rank))}
                        }}
                      ]}, 

                      {tag: "div", attrs: {className:"raised chatboxWr", 
                           style:chat.hide?"display: none":""}, children: [" ", {tag: "div", attrs: {className:"ui segment noSha"}, children: [
                          {tag: "div", attrs: {className:"ui top attached inverted header chat-title" + (chat.read?"":" unread"), 
                               onclick:function(){rd.right(ctrl.toggleChat(rank))}
                          }, children: [
                            chat.user.name, 
                            {tag: "i", attrs: {className:"setting icon setting-chat"}}, 
                            {tag: "i", attrs: {className:"remove icon close-chat", 
                               onclick:function(){rd.right(ctrl.stopChat(rank))}
                            }}
                          ]}, 
                        {tag: "div", attrs: {className:"ui form"}, children: [
                          {tag: "div", attrs: {className:"field"}, children: [

                            {tag: "div", attrs: {className:"chat-box", 
                                 config:api.scrollBottom, 
                                 onclick:local(['nav', 'right'],function(){api.markRead(rank)})
                            }, children: [
                              chat.chat.map(function(item, num){
                                  return {tag: "div", attrs: {}, children: [
                                    chat.init?({tag: "div", attrs: {className:"loading_chat"}, children: ["\"Loading previous ...\""]}):"", 
                                    {tag: "div", attrs: {className:(item.f.id == wsCtrl.userId)?"comment-left": "comment-right"}, children: [
                                      {tag: "div", attrs: {className:"mes"}, children: [item.mes]}
                                    ]}
                                  ]}
                                  })
                            ]}, 

                            {tag: "textarea", attrs: {className:"auto-size new-comment", id:chat.user.id, rows:"1", 
                                      onfocus:function(){rd.right(function(){api.markRead(rank)})}, 
                                      config:function (element, isInit, ctx) {
                                          if(!isInit) {
                                            $(element).on('input', function(){
                                              wsCtrl.data.chat[rank].input($(element).val())
                                            })
                                            $(element).textareaAutoSize();
                                            $(element).attrchange({
                                              //trackValues: true,
                                              callback: function (event) {
                                                var prev = element.previousSibling;
                                                var $prev = $(prev);
                                                if ($prev.scrollTop() + $prev.innerHeight() >= element.previousSibling.scrollHeight) {
                                                  $prev.css('height', 273 - $(element).outerHeight());
                                                  prev.scrollTop = prev.scrollHeight;
                                                } else {
                                                  $prev.css('height', 273 - $(element).outerHeight())
                                                }
                                              }
                                            });
                                          }
                                          element.value = wsCtrl.data.chat[rank].input();
                                          if(element.value.length<1){
                                            $(element).css('height', '30px')
                                          }
                                        }, 
                                      
                                      onkeypress:function(e){
                                            if(e.keyCode == 13 && !e.shiftKey) {
                                              m.redraw.strategy("none");
                                              //if(wsCtrl.data.chat[rank].input() != undefined) console.log(wsCtrl.data.chat[rank].input().length + "=====")
                                              if (wsCtrl.data.chat[rank].input().length < 1) {
                                                //console.log("length < 1")
                                                return false;
                                              } else {
                                                var source = e.target || e.srcElement;
                                                var prev = source.previousSibling;
                                                prev.scrollTop = prev.scrollHeight;
                                                ctrl.add(rank);
                                                //$(source).val = '';
                                                return false;
                                              }
                                            }else{
                                              m.redraw.strategy("none");
                                              if(e.keyCode == 13 && e.shiftKey && wsCtrl.data.chat[rank].input().length < 1){
                                                return false;
                                              }
                                            }
                                        }
                                      
                            }, children: [wsCtrl.data.chat[rank].input()]}
                          ]}
                        ]}
                      ]}]}
                    ]}
                    )
                })
          ]}
        ]}
    )
  }
};

module.exports = Chat;

},{"../ws/_wsCtrl.js":10,"./api.msx":1}],3:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');
var initData = {}
//initData.dashboard = {}
//initData.dashboard.data = {}

var Dashboard = {
  controller: function() {
    console.log("controller dashboard!")
    var ctrl = this;
    ctrl.server = initData.dashboard || {server: false};
    ctrl.request = (!ctrl.server.server)? api.requestWithFeedback({method: "GET", url: "/json"}) : {
      ready: function(){return true},
      data: m.prop(initData.dashboard.data)
    };
    ctrl.server.server = false;
    rd.dashboard()
  },
  view: function(ctrl) {
    api.rd("dashBoard:" + redraw.dashboard);
    redraw.dashboard++;
    if(!ctrl.request.ready()) {
      return m('div', "LOADINGGG !!!")
    } else {
      return {tag: "div", attrs: {}, children: [
        ctrl.request.data().data
      ]}
    }
  }
};


module.exports = Dashboard;
},{"../ws/_wsCtrl.js":10,"./api.msx":1}],4:[function(require,module,exports){
window.Nav = require('./nav.msx');
window.Home = require('./home.msx');
window.Dashboard = require('./dashboard.msx');

window.route = function( sub ){
  return {
    controller : function(){
      m.redraw.strategy( 'diff' );

      return new sub.controller();
    },
    view : sub.view
  }
}

window.target = [];
window.tenant = function(id, module) {
  target.push(id);
  return {
    controller: module.controller,
    view: function(ctrl) {
      if(target.indexOf(id) != -1 || id == "all"){
        target.splice(target.indexOf(id), 1);
        return module.view(ctrl);
      } else {
        return {subtree: "retain"}
      }
    }
  }
};

window.local = function(id, callback) {
  return function(e) {
    id.map(function(component){
      if(window.target.indexOf(component) < 0) window.target.push(component)
    });
    if(callback == undefined) callback = function(){};
    callback.call(this, e)
  }
};

window.rd = {
  nav: function(callback){
    local(['nav'], callback).call()
  },
  home: function(callback){
    local(['home'], callback).call()
  },
  dashboard: function(callback){
    local(['dashboard'], callback).call()
  },
  app: function(callback){
    local(['app'], callback).call()
  },
  right: function(callback){
    local(['right'], callback).call()
  },
  all: function(callback){
    local(["home", "dashboard", "nav", "app", "right"], callback).call()
  }
};



//var listId = [];
//var flag = true;



window.Chat = require('./chat.msx');

window.Loading = {
  controller: function(){

  },
  view: function(){
    console.log("render loading!!");
    return m('', 'LOADING')
  }
};

vis(function(){
  if(vis()){
    console.log("visible")
    rd.all(function(){m.redraw()});
  }
});

var Count = {
  controller: function(){},
  view: function(ctrl){
    return {tag: "div", attrs: {className:"count"}, children: [
      "redraw: ", JSON.stringify(redraw), 
      {tag: "div", attrs: {}, children: ["NAV : ", redraw.nav]}, 
      {tag: "div", attrs: {}, children: ["HOME : ", redraw.home]}, 
      {tag: "div", attrs: {}, children: ["DASHBOARD : ", redraw.dashboard]}, 
      {tag: "div", attrs: {}, children: ["RIGHT : ", redraw.right]}, 
      {tag: "div", attrs: {}, children: ["APP : ", redraw.app]}
    ]}
  }
};


window.initComponent = function() {
  m.mount(document.getElementById('nav'), tenant('nav', window.Nav));
  //m.mount(document.getElementById('app'), tenant('all', window.Loading));
  //m.mount(document.getElementById('count'), Count);
  m.mount(document.getElementById('rightContainer'), tenant('right', window.Chat));
}
},{"./chat.msx":2,"./dashboard.msx":3,"./home.msx":5,"./nav.msx":9}],5:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');
var div = [{id: "div 1", v: 1},{id: "div 2", v: 5} ,{id:"div 3", v: 10}];

var Home = {
  controller: function() {
    api.rd("Controller: Home");
    var ctrl = this;
    ctrl.divs = m.prop([]);
    ctrl.divs(div);
    ctrl.add = function(input){
      ctrl.inputPost('')
    };
    ctrl.inputPost = m.prop('');
    rd.home(function(){m.redraw()});
  },
  view: function(ctrl) {
    api.rd("home:" + redraw.home);
    console.log("rendering home!");
    redraw.home++;
    return (
        {tag: "div", attrs: {className:"ui grid main-content sha2"}, children: [
          {tag: "div", attrs: {className:"eleven wide column main-left border-right"}, children: [
              {tag: "div", attrs: {className:"ui form postWr postContainer"}, children: [
                {tag: "div", attrs: {className:"field"}, children: [
                  {tag: "textarea", attrs: {className:"auto-size new-post", 
                            rows:"2", 
                            placeholder:"What's on your mind?", 
                            config:function (element, isInit, ctx) {
                                        if(!isInit) {
                                          $(element).textareaAutoSize();
                                          $(element).on('input', function(){
                                              ctrl.inputPost($(element).val())
                                          })
                                        }
                                        element.value = ctrl.inputPost();
                                        if(element.value.length<1){

                                          //$(element).css('height', '41px')
                                        }
                                      }, 
                                    
                            onkeypress:function(e){
                                                if(e.keyCode == 13 && !e.shiftKey) {
                                                  m.redraw.strategy("none");
                                                  if (ctrl.inputPost().length < 1) {
                                                    return false;
                                                  }
                                                }else{
                                                  m.redraw.strategy("none");
                                                  if(e.keyCode == 13 && e.shiftKey && ctrl.inputPost().length < 1){
                                                    return false;
                                                  }
                                                }
                                            }
                                        
                  }, children: [ctrl.inputPost()]}
                ]}, 
                {tag: "div", attrs: {className:"post"}, children: [
                  {tag: "button", attrs: {className:"ui blue mini right floated button", 
                       onclick:function(e){
                        m.redraw.strategy("none");
                        ctrl.add(ctrl.inputPost())
                        //$('.new-post').attr('disabled', true);
                        $('.postWr').addClass('loading');

                       }
                  }, children: ["Post"]}, 
                  {tag: "span", attrs: {className:"clear"}}
                ]}
              ]}, 

              ctrl.divs().map(function(item){
                  return {tag: "div", attrs: {className:"ui postContainer postDemo", 
                              id:item.id, 
                              v:item.v}, children: [item.id]}
                  })
          ]}, 
          {tag: "div", attrs: {className:"three wide column"}, children: ["right"]}
        ]}
    )
  }
};

module.exports = Home;
},{"../ws/_wsCtrl.js":10,"./api.msx":1}],6:[function(require,module,exports){
var wsCtrl = require('../../ws/_wsCtrl.js');
var api = require('.././api.msx');


var LoginButton = function(ctrl){ return(
    {tag: "div", attrs: {className:"border-left login-button"}, children: [
      {tag: "a", attrs: {className:"item", 
         onclick:function(){rd.nav(ctrl.toggleLogin())}
      }, children: [
        {tag: "i", attrs: {className:"large icon user"}}, 
        "Login"
      ]}, 
      {tag: "div", attrs: {className:"notifyWr"}, children: [
        ctrl.displayLogin?(
        {tag: "div", attrs: {className:"inLogin", 
             config:function(el, isInit, ctx){
               if(!isInit){
                 function loginClick(){
                    if(!$(el).is(':hover') && !$('.login-button').is(':hover')){
                      ctrl.displayLogin = false;
                      $(document).unbind("click", loginClick);
                      rd.nav(function(){m.redraw()})
                    }
                 }
                 $(document).on('click', loginClick)
               }
             }
        }, children: [
          {tag: "div", attrs: {className:"corner-right"}, children: [{tag: "div", attrs: {className:"tr"}}]}, 

          {tag: "div", attrs: {className:"ui segment notify-content sha3"}, children: [

            {tag: "form", attrs: {className:"ui small form", action:"/login?referrer=", method:"POST"}, children: [
              {tag: "div", attrs: {className:"field"}, children: [
                {tag: "div", attrs: {className:"ui left icon input"}, children: [
                  {tag: "i", attrs: {className:"user icon"}}, 
                  {tag: "input", attrs: {type:"text", pattern:"^[\\w-]+$", required:"required", name:"username", id:"username", placeholder:"User name"}}
                ]}
              ]}, 
              {tag: "div", attrs: {className:"field"}, children: [
                {tag: "div", attrs: {className:"ui left icon input"}, children: [
                  {tag: "i", attrs: {className:"lock icon"}}, 
                  {tag: "input", attrs: {type:"password", required:"required", name:"password", id:"password", placeholder:"Password"}}
                ]}
              ]}, 
              {tag: "div", attrs: {className:"ui right floated tiny buttons"}, children: [
                {tag: "button", attrs: {className:"ui positive button", type:"submit"}, children: ["Login"]}, 
                {tag: "div", attrs: {className:"or", "data-text":"Or"}}, 
                {tag: "a", attrs: {className:"ui teal button"}, children: ["Signup"]}
              ]}
            ]}
          ]}
        ]}):""
      ]}
    ]}
)}

module.exports = LoginButton;
},{"../../ws/_wsCtrl.js":10,".././api.msx":1}],7:[function(require,module,exports){
var wsCtrl = require('../../ws/_wsCtrl.js');
var api = require('.././api.msx');

var MessageButton = function(ctrl){ return (
    {tag: "div", attrs: {}, children: [
      {tag: "a", attrs: {className:"item nofity border-left message-button", 
         onclick:function(){rd.nav(ctrl.displayNofity());}
      }, children: [
        {tag: "a", attrs: {href:"javascript:void(0)"}, children: [{tag: "i", attrs: {className:"large mail icon"}}]}, 
        (wsCtrl.data.notify.n>0)?({tag: "div", attrs: {className:"floating ui red label num-label"}, children: [wsCtrl.data.notify.n]}):""
      ]}, 
      {tag: "div", attrs: {className:"notifyWr"}, children: [
        !wsCtrl.data.notify.display?"":(
        {tag: "div", attrs: {className:"inNotify"}, children: [
          {tag: "div", attrs: {className:"corner-right"}, children: [{tag: "div", attrs: {className:"tr"}}]}, 
          {tag: "div", attrs: {className:"ui raised  attracted segment notify-content sha3 pad0"}, children: [
            {tag: "div", attrs: {className:"ui top attracted label tran"}, children: [
              "Inbox(", wsCtrl.data.notify.n, ")"
            ]}, 
            !wsCtrl.data.notify.init?"LOADING":(
            {tag: "div", attrs: {}, children: [
              
                  wsCtrl.data.notify.notifyMessage.map(function(mes){
                      return (
                      {tag: "div", attrs: {className:"notifyMes", 
                           config:
                                    function(el, isInit, ctx){
                                      if(!isInit){
                                         function mesClick(){
                                            if(!$(el).is(':hover') && !$('.message-button').is(':hover')){
                                              wsCtrl.data.notify.display = false;
                                              $(document).unbind("click", mesClick);
                                              rd.nav(function(){m.redraw()})
                                            }
                                         }
                                         $(document).on('click', mesClick)
                                      }

                                      $(el).click(function(){
                                        local(['nav', 'right'], function(){
                                          wsCtrl.data.notify.display = !wsCtrl.data.notify.display;
                                          var pos = wsCtrl.getPosChat(mes.user);
                                          if(wsCtrl.data.chat[pos].display == false){
                                            wsCtrl.data.chat[pos].display = true;
                                            wsCtrl.data.chat[pos].hide = false;
                                          } else if(wsCtrl.data.chat[pos].hide == true){
                                            wsCtrl.data.chat[pos].hide = false;
                                          } else {
                                          }
                                          if(mes.n > 0) wsCtrl.data.chat[pos].read = false;
                                          api.focusById(wsCtrl.data.chat[pos].user.id);
                                          m.redraw();
                                        }).call()
                                      });
                                    }
                                 
                      }, children: [
                        {tag: "div", attrs: {className:"notifyName"}, children: [
                          mes.user.name
                        ]}, 
                        {tag: "div", attrs: {className:"mesNumber"}, children: [
                          "(", mes.n, ")"
                        ]}, 
                        {tag: "div", attrs: {className:"lastMes"}, children: [
                          mes.lm.mes
                        ]}
                      ]}
                          )
                      })
                  
            ]}
                )
          ]}
        ]}
            )
      ]}
    ]}
) }

module.exports = MessageButton;
},{"../../ws/_wsCtrl.js":10,".././api.msx":1}],8:[function(require,module,exports){
var wsCtrl = require('../../ws/_wsCtrl.js');
var api = require('.././api.msx');

var UserButton = function(ctrl){ return (
    {tag: "div", attrs: {}, children: [
      {tag: "a", attrs: {href:"javascript:void(0)", className:"item user-button", 
         onclick:function(){rd.nav(ctrl.toggleUser())}
      }, children: [
        {tag: "i", attrs: {className:"large user icon"}}, 
        wsCtrl.userName
      ]}, 
      {tag: "div", attrs: {className:"notifyWr"}, children: [
        ctrl.displayUser?(
        {tag: "div", attrs: {className:"inUser", 
             config:function(el, isInit, ctx){
               if(!isInit){
                 function userClick(){
                    if(!$(el).is(':hover') && !$('.user-button').is(':hover')){
                      ctrl.displayUser = false;
                      $(document).unbind("click", userClick);
                      rd.nav(function(){m.redraw()})
                    }
                 }
                 $(document).on('click', userClick)
               }
             }
        }, children: [
          {tag: "div", attrs: {className:"corner-right"}, children: [{tag: "div", attrs: {className:"tr"}}]}, 

          {tag: "div", attrs: {className:"ui  attracted segment notify-content sha3"}, children: [
            {tag: "a", attrs: {href:"/logout", class:"ui small button"}, children: [
              {tag: "i", attrs: {class:"sign out icon"}}, 
              "Logout"
            ]}
          ]}
        ]}):""
      ]}
    ]}
) }

module.exports = UserButton;
},{"../../ws/_wsCtrl.js":10,".././api.msx":1}],9:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');

var UserButton = require('./menu_button/User.msx');
var LoginButton = require('./menu_button/Login.msx');
var MessageButton = require('./menu_button/Message.msx');

var Nav = {
  controller: function(){
    api.rd("Controller: nav");
    var ctrl = this;
    ctrl.ping = m.prop(0);
    ctrl.userNumber = m.prop(0);
    setInterval(function(){
      rd.nav(function(){ctrl.ping(wsCtrl.ping);ctrl.userNumber(wsCtrl.total); m.redraw();})
    }, 1000);
    ctrl.displayUser = false;
    ctrl.toggleUser = function(){
      ctrl.displayUser = !ctrl.displayUser
    };
    ctrl.displayLogin = false;
    ctrl.toggleLogin = function(){
      ctrl.displayLogin = !ctrl.displayLogin
    };
    ctrl.displayNofity = function(){
      //if(wsCtrl.data.notify.notifyMessage.length < 1){
      if(wsCtrl.data.notify.display == false ) {
        wsCtrl.data.notify.init = false;
        wsCtrl.send(wsCtrl.sendData("gnm", 0))
      }
      //}
      wsCtrl.data.notify.n = 0;
      wsCtrl.data.notify.display = !wsCtrl.data.notify.display
    };

  },
  view: function(ctrl){
    api.rd("nav: " + redraw.nav);
    redraw.nav++;
    return (
        {tag: "div", attrs: {className:"ui top small blue inverted fixed  menu sha"}, children: [
          {tag: "a", attrs: {href:"/", 
             className:((m.route() == "/")?"active":"") + " item route-button", 
             config:m.route
          }, children: [{tag: "i", attrs: {className:"large icon home users-icon"}}]}, 
          {tag: "a", attrs: {href:"/dashboard", 
             className:((m.route() == "/dashboard")?"active":"") + " item route-button", 
             config:m.route
          }, children: [
            {tag: "i", attrs: {className:"large icon newspaper"}}, 
            "Articles"
          ]}, 
          {tag: "a", attrs: {className:"item route-button"}, children: [
            {tag: "i", attrs: {className:"large icon comments"}}, 
            "Chat rooms"
          ]}, 
          {tag: "div", attrs: {className:"ui category search item"}, children: [
            {tag: "div", attrs: {className:"ui icon input"}, children: [
              {tag: "input", attrs: {className:"", type:"text", placeholder:"Search ..."}}, 
                {tag: "i", attrs: {className:"search link icon"}}
            ]}, 
            {tag: "div", attrs: {className:"results"}}
          ]}, 


           (wsCtrl.userId.length>0)?({tag: "div", attrs: {className:"right menu"}, children: [

             {tag: "div", attrs: {className:"item"}, children: [
               {tag: "i", attrs: {className:"large icon users"}}, 
               ctrl.userNumber()?((ctrl.userNumber() == 1)?(ctrl.userNumber() + " User"):(ctrl.userNumber() + " Users")):"? User(s)"
             ]}, 
             {tag: "div", attrs: {className:"item"}, children: [
               {tag: "i", attrs: {className:"large " + ((ctrl.ping()<500)?"teal":((ctrl.ping()<1500)?"yellow":"red")) + " icon feed"}}, 
               {tag: "div", attrs: {className:"bold " + ((ctrl.ping()>4000)?"red":((ctrl.ping()>500)?"yellow":""))}, children: [(ctrl.ping()>4000)?"Reconnecting...":((ctrl.ping() >0)?(ctrl.ping() + " ms"):"? ms")]}
             ]}, 

            {tag: "a", attrs: {href:"javascript:void(0)", className:"item"}, children: [
              {tag: "i", attrs: {className:"large icon add user users-icon"}}, 
              {tag: "div", attrs: {className:"floating ui red label num-label"}, children: ["2"]}
            ]}, 
            MessageButton(ctrl), 
            UserButton(ctrl)
          ]}):(
              {tag: "div", attrs: {className:"right menu"}, children: [
                {tag: "div", attrs: {className:"item"}, children: [
                  {tag: "i", attrs: {className:"large icon users"}}, 
                  ctrl.userNumber()?((ctrl.userNumber() == 1)?(ctrl.userNumber() + " User"):(ctrl.userNumber() + " Users")):"? User(s)"
                ]}, 
                {tag: "div", attrs: {className:"item"}, children: [
                  {tag: "i", attrs: {className:"large " + ((ctrl.ping()<500)?"teal":((ctrl.ping()<1500)?"yellow":"red")) + " icon feed"}}, 
                  {tag: "div", attrs: {className:"bold " + ((ctrl.ping()>4000)?"red":((ctrl.ping()>500)?"yellow":""))}, children: [(ctrl.ping()>4000)?"Reconnecting...":((ctrl.ping() >0)?(ctrl.ping() + " ms"):"? ms")]}
                ]}, 
                LoginButton(ctrl)
              ]}
              )

        ]}
    )
  }
};

module.exports = Nav;
},{"../ws/_wsCtrl.js":10,"./api.msx":1,"./menu_button/Login.msx":6,"./menu_button/Message.msx":7,"./menu_button/User.msx":8}],10:[function(require,module,exports){
var wsCtrl = {}
wsCtrl.userId = document.body.getAttribute("id");
var userId = wsCtrl.userId;
wsCtrl.userName = document.body.getAttribute("name");
var userName = wsCtrl.userName
wsCtrl.mVersion = parseInt(document.body.getAttribute("mv"));
var mVersion = wsCtrl.mVersion
wsCtrl.mRVersion = parseInt(document.body.getAttribute("mv"));
var mRVersion = wsCtrl.mRVersion

var prevTime;

wsCtrl.ping = 0;
wsCtrl.total = 0;

window.redraw = {
  nav: 0,
  home: 0,
  dashboard: 0,
  right: 0,
  app: 0
};

wsCtrl.data = {
  userOnline: [],
  user: {},
  chat: [],
  notify: {
    n: 0,
    notifyMessage: [],
    init: false,
    display: false
  }
};
var data = wsCtrl.data;

//var ws = new WebSocket("ws://188.166.254.203:9000/socket?sri=" + sri);
var reconnect;

function initReconnect(){
  reconnect = setTimeout(function(){
    console.log("reconnecting ... ")
    clearTimeout(pingSchedule);
    if(ws){
      ws.onerror = $.noop;
      ws.onclose = $.noop;
      ws.onopen = $.noop;
      ws.onmessage = $.noop;
      ws.close();
    }
    initWs();
  }, 4000);
};

var ws;
function initWs(){
  var sri = Math.random().toString(36).substring(2);
  ws = new WebSocket("ws://" + document.domain + ":9000/socket?sri=" + sri);

  ws.onopen = function(){
    console.log('WebSocket ok');
    clearTimeout(reconnect);
    initReconnect()
    wsCtrl.ping = 0;
    send(pingData());
    prevTime = Date.now();
    wsCtrl.data.userOnline = [];
    send(sendData("get_onlines", ""));
  };

  ws.onmessage = function (e) {
    var m = JSON.parse(e.data);
    ctrl.listen(m)
  };

  ws.onclose = function(){
    console.log("socket closed")
  };
  ws.onerror = function(){
    console.log("socket error")
  };
  initReconnect();
};
initWs();

//var ws = new WebSocket("ws://localhost:9000/socket?sri=" + sri);
//var ws = new WebSocket("ws://192.168.1.25:9000/socket?sri=" + sri);

var pingData = function() {
  return JSON.stringify({
    t: "p",
    v: mVersion
  });
};

function arrayObjectIndexOf(myArray, searchTerm, property) {
  for(var i = 0, len = myArray.length; i < len; i++) {
    if (myArray[i][property] === searchTerm) return i;
  }
  return -1;
}

wsCtrl.sendData = function(t, d){
  return JSON.stringify({
    t: t,
    d: d
  })
};
var sendData = wsCtrl.sendData;

wsCtrl.send = function (message, callback) {
  waitForConnection(function () {
    ws.send(message);
    if (typeof callback !== 'undefined') {
      callback();
    }
  }, 1000);
};
var send = wsCtrl.send;

var waitForConnection = function (callback, interval) {
  if (ws.readyState === 1) {
    callback();
  } else {
    setTimeout(function () {
      waitForConnection(callback, interval);
    }, interval);
  }
};

//setInterval(function(){
//  send(pingData());
//}, 1000);





wsCtrl.getPosChat = function(user, mv){
  var cv = (mv == undefined)?0:mv;
  var read = (mv == undefined);
  pos = -1;
  for(var len = 0; len < data.chat.length; len++){
    if(data.chat[len].user.id == user.id) {
      pos = len;
      return pos
    }
  }
  if(pos = -1){
    data.chat.push({user: user, display: true, input: m.prop(''), init: false, hide: false, read: read, chat: []});
    send(sendData("init_chat", {w: user.id, cv: cv}));
    return (data.chat.length - 1)
  }
};
var getPosChat = wsCtrl.getPosChat;

function calcPing(){
  console.log("run calc");
  var now = Date.now();
  wsCtrl.ping = Math.ceil(0.8*(now - prevTime)/2 + wsCtrl.ping*0.2);
}

var calcTimeOut;
var pingSchedule;
var inPingSchedule;
function initPingSchedule() {
  pingSchedule = setTimeout(function pingScheduleFn() {
    if (wsCtrl.ping <= 4000) {
      calcPing();
      inPingSchedule = setTimeout(pingScheduleFn, 500);
    }
  }, 2000);
}

var ctrl = {};
ctrl.listen = function(d){
  if(d.t === "n"){
  clearTimeout(pingSchedule);
  clearTimeout(inPingSchedule);
  initPingSchedule();
  clearTimeout(reconnect);
  initReconnect();

    var now = Date.now();
    if(wsCtrl.ping){
      wsCtrl.ping =  Math.ceil((now - prevTime)/2);
    } else {
      wsCtrl.ping = Math.ceil(0.8*(now - prevTime)/2 + wsCtrl.ping*0.2);
    }
    if(wsCtrl.ping <= 1000){
      setTimeout(function(){
        prevTime = Date.now();
        send(pingData());
      }, 1000)
    } else {
      prevTime = Date.now();
      send(pingData());
    }

    var preNotify = data.notify.n;
    data.notify.n = d.d.n;
    if (preNotify !== data.notify.n) rd.nav(function(){m.redraw()})
    wsCtrl.total = d.d.d;

  }

  if(d.t === "ul"){
    d.d.map(function(user){
      if(arrayObjectIndexOf(data.userOnline, user.id, "id") < 0) {
        data.userOnline.push(user);
      }
    });
    rd.right(function(){m.redraw()})
  }

  if(d.t === "following_leaves"){
    data.userOnline.splice(arrayObjectIndexOf(data.userOnline, d.d.in, "id"), 1);
    rd.right(function(){m.redraw()})
  }

  if(d.t === "following_enters"){
    if(arrayObjectIndexOf(data.userOnline, d.d.id, "id") < 0) {
      data.userOnline.push(d.d);
      rd.right(function(){m.redraw()})
    }
  }



  if(d.t === "mes"){
    if(mVersion >= (d.d.v-1)){
      doMes(d);
      mVersion++;
      mRVersion ++;
    } else {
      var sendMes = { f: (mVersion+1), t: (d.d.v -1) };
      mVersion = d.d.v;

      setTimeout(function delayDoMes(){
        console.log("delay domess");
        if(mRVersion >= sendMes.t){
          doMes(d);
          mRVersion++;
        }else{
          setTimeout(delayDoMes, 200);
        }
      }, 1500);

      console.log("SEEND REQUEST GET MISSING MESS: " + sendMes.f + "=>" + sendMes.t);
      send(sendData("gmm", sendMes));
      var gmm = setTimeout(function getMissMes(){
        if(mRVersion >= sendMes.t){
          clearTimeout(gmm)
        } else {
          console.log("SEEND REQUEST GET MISSING MESS: " + sendMes.f + "=>" + sendMes.t);
          console.log("current mes need:" + sendMes.t);
          send(sendData("gmm", sendMes));
          setTimeout(getMissMes, 1500)
        }
      }, 1500)

    }
  }

  if(d.t === "init_chat"){
    var listMes = d.d;
    if(listMes.length > 0){
      var user = (userId == d.d[0].f.id)?d.d[0].t:d.d[0].f;
      var pos = getPosChat(user);
      //console.log("init posssssssss:" + pos);
      if(data.chat[pos].chat.length <= 1) {
        d.d.map(function (mes) {
          //console.log("init_chat: " + mes.mv);
          //console.log("push:" + mes.mes);
          data.chat[pos].chat.push(mes)
        });
      } else {
        d.d.map(function(mes){
          if(mes.mv < data.chat[pos].chat[0].mv) data.chat[pos].chat.push(mes)
        })
      }
      data.chat[pos].chat.sort(sortByVer);
      rd.right(function(){m.redraw()})
    }
  }

  if(d.t === "smm"){
      d.d.d.map(function(mes){
      var uid = (userId == mes.f)?mes.t:mes.f;
      var pos = getPosChat(uid);
      if(data.chat[pos].chat.length < 1) {
        d.d.d.map(function (mes) {
          data.chat[pos].chat.push(mes)
        });
      } else {
        d.d.d.map(function (mes) {
          console.log("smm: " + mes.mv + " --- " + data.chat[pos].chat[data.chat[pos].chat.length - 1].mv);
          if (mes.mv > data.chat[pos].chat[data.chat[pos].chat.length - 1].mv) data.chat[pos].chat.push(mes)
        })
      }
      if(mRVersion == d.d.f -1 ) mRVersion = d.d.t;
     });

      rd.right(function(){m.redraw()})
  }

  if(d.t === "init_notify") {
    data.notify.notifyMessage = d.d;
    data.notify.init = true;
    rd.nav(function(){m.redraw();});
  }
};

function sortByVer(a,b) {
  //console.log("sorting " + a.mv + b.mv)
  if (a.mv < b.mv)
    return -1;
  if (a.mv > b.mv)
    return 1;
  return 0;
}



var doMes = function(d){
    var user = (userId == d.d.f.id)? d.d.t: d.d.f;
    var pos = getPosChat(user, d.d.mv);
    data.chat[pos].chat.push(
        {f: d.d.f, "mv": d.d.mv, "mes": d.d.m, "time": d.d.time}
    );
    if(data.chat[pos].display != true){
      data.chat[pos].display = true;
      data.chat[pos].hide = false;
    }
    if(userId !== d.d.f.id) {
      data.chat[pos].read = false;
    } else data.chat[pos].read = true;
    rd.right(function(){m.redraw()})
};

$('body').on('click', '.relation_actions a.relation', function() {
  console.log("relation")
  var $a = $(this).addClass('processing');
  $.ajax({
    url: $a.attr('href'),
    type: 'post',
    success: function(html) {
      $a.parent().html(html);
    }
  });
  return false;
});


module.exports = wsCtrl;
},{}]},{},[4])