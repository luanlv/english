(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');


var api = api || {};

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

api.requestWithFeedback2 = function(args, bind, fn, fnError) {
    var data = m.prop();
    var completed = m.prop(false);
    var complete = function() {
        completed(true)
    };
    args.background = true;
    args.config = function(xhr) {
        xhr.timeout = 4000;
        xhr.ontimeout = function() {
            complete();
            m.redraw();
        }
    };
    return {
        request: m.request(args).then(data).then(function(data){
            console.log(data);
            if(bind !== undefined) bind(data);
            if(fn !== undefined) fn();
            complete();
            m.redraw();
        }, function(error){
            console.log(error)
            if(fnError !== undefined)  fnError()
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
  if( element.scrollHeight - element.clientHeight - element.scrollTop < addLegth +  10 )
    element.scrollTop = element.scrollHeight;
  context.prevHeight = element.scrollHeight
}

api.scrollBottom2 = function(element, isInit, context) {
  if(!isInit){
    element.scrollTop = element.scrollHeight;
  }
  var addLegth = (element.scrollHeight - context.prevHeight) || 0;
  if( element.scrollHeight - element.clientHeight - element.scrollTop < addLegth + 60)
    element.scrollTop = element.scrollHeight;
  context.prevHeight = element.scrollHeight
};

api.setsVal = function(callback) {
  return function(e) {
    m.withAttr("value", callback)(e);
    m.redraw.strategy("none")
  }
}



api.markRead = function(rank){
  if(wsCtrl.data.chat[rank].read === false){
    //if(wsCtrl.data.chat[rank].chat[wsCtrl.data.chat[rank].chat.length - 1].f.id == wsCtrl.data.chat[rank].user.id) {
    //  wsCtrl.send(wsCtrl.sendData("mr", {
    //    uid: wsCtrl.data.chat[rank].user.id,
    //    mv: wsCtrl.data.chat[rank].chat[wsCtrl.data.chat[rank].chat.length - 1].mv
    //  }));
    //}
    wsCtrl.send(wsCtrl.sendData("mr", {
      uid: wsCtrl.data.chat[rank].user.id,
      mv: wsCtrl.data.chat[rank].chat[wsCtrl.data.chat[rank].chat.length - 1].mv
    }));
    wsCtrl.data.chat[rank].read = true;
  }
};

api.focusById = function(uid){
  setTimeout(function focusComment(){
    if(document.getElementById(uid) != undefined){
      console.log("run focus OK: " + uid)
      document.getElementById(uid).focus();
    } else {
      console.log("running focus: " + uid)
      setTimeout(focusComment, 100)
    }
  }, 100);
};

api.signin = function(){
  $('.ui.modal.sign-in').modal({
    onShow: function(){
      var contentWr = $(this).children().first();
      contentWr.load('/login', function(res, status, xhr){
        if(status == "success"){
          contentWr.removeClass('loading')
          $('.sign-in .referrer').attr('value', window.location.pathname)
        }
      })
    },
    onHide: function(){
      var contentWr = $(this).children().first();
      contentWr.addClass('loading')
    }
  })
  .modal('show');
};
window.signin = api.signin

api.signup = function(){
  $('.ui.modal.sign-up').modal({
        onShow: function(){
          var contentWr = $(this).children().first();
          contentWr.load('/signup', function(res, status, xhr){
            if(status == "success"){
              contentWr.removeClass('loading')
              $('.sign-up .referrer').attr('value', window.location.pathname)
            }
          })
        },
        onHide: function(){
          var contentWr = $(this).children().first();
          contentWr.addClass('loading')
        }
      })
    .modal('show');
}
window.signup = api.signup;

api.time = function(timestamp){
  return moment.unix(timestamp/1000).fromNow();
};




module.exports = api;
},{"../ws/_wsCtrl.js":16}],2:[function(require,module,exports){
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
      var index = wsCtrl.arrayObjectIndexOf2(wsCtrl.storage.chat, wsCtrl.data.chat[num].user.id, "id")
      if(index > -1){
        wsCtrl.storage.chat[index].hide = wsCtrl.data.chat[num].hide;
        $.localStorage.set('chat:' + wsCtrl.userId, wsCtrl.storage.chat);
      }
    };

    ctrl.stopChat = function(num){
      wsCtrl.data.chat[num].display = false;
      wsCtrl.data.chat[num].input('');
      var index = wsCtrl.arrayObjectIndexOf2(wsCtrl.storage.chat, wsCtrl.data.chat[num].user.id, "id");
      if(index > -1){
        wsCtrl.storage.chat.splice(index, 1);
        $.localStorage.set('chat:' + wsCtrl.userId, wsCtrl.storage.chat);
      }
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
          wsCtrl.userId.length>0?(
          ctrl.showChatDock?({tag: "div", attrs: {id:"user-list"}, children: [
            {tag: "div", attrs: {className:"ui segment pad0"}, children: [
            {tag: "div", attrs: {className:"ui top attached inverted header chat-title", 
                 onclick:function(){rd.right(ctrl.toggleChatDock())}
            }, children: ["Online"]}, 
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
            }, children: ["Online"]}
          ]})):"", 
          

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
                          {tag: "div", attrs: {className:"ui top attached inverted header chat-title sha2" + (chat.read?"":" unread"), 
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
                                    {tag: "div", attrs: {className:(item.f.id == wsCtrl.userId)?"ui left pointing basic label chat-left": "ui right pointing basic label chat-right"}, children: [
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
                                      
                            }}
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

},{"../ws/_wsCtrl.js":16,"./api.msx":1}],3:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');


var ChatRoom = {
  controller: function() {
    m.redraw.strategy("diff");
    $.cookie('url', m.route(), {path: "/"})
    var ctrl = this;
    ctrl.param = m.prop(m.route.param("roomId"));
    wsCtrl.send(wsCtrl.sendData("initChat", {t: "chatrooms"}));

    var intervalChatRooms = setInterval(function(){
      wsCtrl.send(wsCtrl.sendData("sub", {t: "chatrooms"}));
    }, 30000);
    ctrl.onunload = function() {
      wsCtrl.data.chatrooms = {};
      wsCtrl.send(wsCtrl.sendData("unSub", {t: "chatrooms"}));
      clearInterval(intervalChatRooms);
    };
    ctrl.roomList = [
      {id: "01", name: "Room 1", description: "Low intermediate level of English"},
      {id: "02", name: "Room 2", description: "High intermediate level of English"},
      {id: "03", name: "Room 3", description: "Advanced level of English"},
      {id: "04", name: "Room 4", description: "Proficient in English"},
      {id: "05", name: "Room 5", description: "Upper Intermediate"},
      {id: "06", name: "Room 6", description: "Very Advanced"},
    ];

    rd.chatroom();
  },
  view: function(ctrl) {
    return (
      {tag: "div", attrs: {className:"ui grid main-content"}, children: [
        {tag: "div", attrs: {className:"eleven wide column"}, children: [
          {tag: "div", attrs: {className:"ui segment segWr mh500"}, children: [
            {tag: "div", attrs: {className:"ui grid"}, children: [
              ctrl.roomList.map(function(room){
                return (
                    {tag: "div", attrs: {className:"eight wide column"}, children: [
                    {tag: "div", attrs: {className:"ui segment"}, children: [
                      {tag: "div", attrs: {className:"ui relaxed divided list"}, children: [
                        {tag: "a", attrs: {href:"/chatroom/" + room.id, className:"item", 
                           config:m.route
                        }, children: [

                    {tag: "span", attrs: {className:"fr"}, children: [
                      {tag: "div", attrs: {className:"item"}, children: [
                        {tag: "i", attrs: {className:"tiny users left middle aligned icon"
                        }, children: [wsCtrl.getRooms(room.id).u]}
                      ]}, 
                      {tag: "i", attrs: {className:"tiny plug left middle aligned icon"
                      }, children: [
                        wsCtrl.getRooms(room.id).c
                      ]}
                    ]}, 

                          {tag: "i", attrs: {className:"large pointing right middle aligned icon"}}, 
                          {tag: "div", attrs: {className:"content"}, children: [
                            {tag: "div", attrs: {className:"header"}, children: [room.name]}, 
                            {tag: "div", attrs: {className:"description"}, children: [room.description]}
                          ]}
                        ]}
                      ]}
                    ]}
                  ]}
                )
              })
            ]}, 




            Create()
          ]}
        ]}, 
        {tag: "div", attrs: {className:"five wide column"}, children: [
          {tag: "div", attrs: {className:"ui mh500"}

          }
        ]}
      ]}
    )
  }
};

var Create = function(){
  return (
      {tag: "div", attrs: {className:"ui form fluid popup create-room"}, children: [
        {tag: "div", attrs: {className:"two fields"}, children: [
          {tag: "div", attrs: {className:"field"}, children: [
            {tag: "label", attrs: {}, children: ["Tên phòng"]}, 
            {tag: "input", attrs: {type:"text", placeholder:"Tên phòng"}}
          ]}, 
          {tag: "div", attrs: {className:"field"}, children: [
            {tag: "label", attrs: {}, children: ["Mô tả"]}, 
            {tag: "input", attrs: {type:"text", placeholder:"Mô tả"}}
          ]}
        ]}, 

        {tag: "div", attrs: {className:"two fields"}, children: [
          {tag: "div", attrs: {className:"grouped field"}, children: [
            {tag: "label", attrs: {}, children: ["Level"]}, 
            {tag: "div", attrs: {className:"field"}, children: [
              {tag: "div", attrs: {className:"ui radio checkbox"}, children: [
                {tag: "input", attrs: {type:"radio", name:"fruit", tabindex:"0", className:"hidden"}}, 
                {tag: "label", attrs: {}, children: ["Elementary"]}
              ]}
            ]}, 
            {tag: "div", attrs: {className:"field"}, children: [
              {tag: "div", attrs: {className:"ui radio checkbox"}, children: [
                {tag: "input", attrs: {type:"radio", name:"fruit", tabindex:"0", className:"hidden"}}, 
                {tag: "label", attrs: {}, children: ["Intermediate"]}
              ]}
            ]}, 
            {tag: "div", attrs: {className:"field"}, children: [
              {tag: "div", attrs: {className:"ui radio checkbox"}, children: [
                {tag: "input", attrs: {type:"radio", name:"fruit", tabindex:"0", className:"hidden"}}, 
                {tag: "label", attrs: {}, children: ["Advanced"]}
              ]}
            ]}, 
            {tag: "div", attrs: {className:"field"}, children: [
              {tag: "div", attrs: {className:"ui radio checkbox"}, children: [
                {tag: "input", attrs: {type:"radio", name:"fruit", tabindex:"0", className:"hidden"}}, 
                {tag: "label", attrs: {}, children: ["Proficient"]}
              ]}
            ]}
          ]}, 
          {tag: "div", attrs: {className:"grouped field"}, children: [
            {tag: "label", attrs: {}, children: ["Kỹ năng"]}, 
            {tag: "div", attrs: {className:"field"}, children: [
              {tag: "div", attrs: {className:"ui radio checkbox"}, children: [
                {tag: "input", attrs: {type:"radio", name:"fruit", tabindex:"0", className:"hidden"}}, 
                {tag: "label", attrs: {}, children: ["Speaking"]}
              ]}
            ]}, 
            {tag: "div", attrs: {className:"field"}, children: [
              {tag: "div", attrs: {className:"ui radio checkbox"}, children: [
                {tag: "input", attrs: {type:"radio", name:"fruit", tabindex:"0", className:"hidden"}}, 
                {tag: "label", attrs: {}, children: ["Listening"]}
              ]}
            ]}, 
            {tag: "div", attrs: {className:"field"}, children: [
              {tag: "div", attrs: {className:"ui radio checkbox"}, children: [
                {tag: "input", attrs: {type:"radio", name:"fruit", tabindex:"0", className:"hidden"}}, 
                {tag: "label", attrs: {}, children: ["Wriring"]}
              ]}
            ]}, 
            {tag: "div", attrs: {className:"field"}, children: [
              {tag: "div", attrs: {className:"ui radio checkbox"}, children: [
                {tag: "input", attrs: {type:"radio", name:"fruit", tabindex:"0", className:"hidden"}}, 
                {tag: "label", attrs: {}, children: ["Reading"]}
              ]}
            ]}
          ]}
        ]}, 
  
        {tag: "div", attrs: {className:"field"}, children: [
          {tag: "button", attrs: {className:"button"}, children: ["Create"]}
        ]}

      ]}
  )
}

module.exports = ChatRoom;
},{"../ws/_wsCtrl.js":16,"./api.msx":1}],4:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');
var initData = {}
//initData.dashboard = {}
//initData.dashboard.data = {}

var Dashboard = {
  controller: function() {
    console.log("controller dashboard!")
    $.cookie('url', m.route(), {path: "/"})
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
      return (
          {tag: "div", attrs: {className:"ui grid main-content "}, children: [
            {tag: "div", attrs: {className:"sixteen wide column"}, children: [
              {tag: "div", attrs: {className:"ui segment loading mh300 noBor noSha"}, children: [
                "Loading"
              ]}
            ]}
          ]}
      )
    } else {
      return ({tag: "div", attrs: {className:"ui center aligned grid main-content"}, children: [
        {tag: "div", attrs: {className:"ui segment mh300 noBor noSha"}, children: [
          {tag: "div", attrs: {className:"sixteen wide column"}, children: [
            {tag: "div", attrs: {className:"building"}, children: [
              "this page is under construction!"
            ]}
          ]}
        ]}
      ]})
    }
  }
};


module.exports = Dashboard;
},{"../ws/_wsCtrl.js":16,"./api.msx":1}],5:[function(require,module,exports){
"user strict";

var Nav = require('./nav.msx');
var Home = require('./home.msx');
var Dashboard = require('./dashboard.msx');
var ChatRoom = require('./chatroom.msx');
var User = require('./user.msx');
var UserSetting = require('./userSetting.msx');
var Room = require('./room.msx');
var Footer = require('./footer.msx');

window.route = function( sub ){
  return {
    controller : function(){
      m.redraw.strategy( 'diff' );

      return new sub.controller();
    },
    view : sub.view
  }
};

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
  chatroom: function(callback){
    local(['chatroom'], callback).call()
  },
  room: function(callback){
    local(['room'], callback).call()
  },
  user: function(callback){
    local(['user'], callback).call()
  },
  setting: function(callback){
    local(['setting'], callback).call()
  },
  all: function(callback){
    local(["home", "dashboard", "nav", "app", "right", "chatroom", "room", "user", "setting"], callback).call()
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

window.initRoute = function(){
  m.route(document.getElementById('app'), "/", {
    "/": tenant('home', route(Home)),
    "/dashboard": tenant('dashboard', route(Dashboard)),
    "/chatroom": tenant('chatroom', route(ChatRoom)),
    "/chatroom/:roomId": tenant('room', route(Room)),
    "/settings": tenant('setting', route(UserSetting)),
    "/@/:user": tenant('user', route(User)),
  });
  $('.loaderWr').remove();
};

window.initComponent = function() {
  m.mount(document.getElementById('nav'), tenant('nav', Nav));
  m.mount(document.getElementById('footer'), tenant('footer', Footer));
  m.mount(document.getElementById('rightContainer'), tenant('right', Chat));
}
},{"./chat.msx":2,"./chatroom.msx":3,"./dashboard.msx":4,"./footer.msx":6,"./home.msx":7,"./nav.msx":12,"./room.msx":13,"./user.msx":14,"./userSetting.msx":15}],6:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');


var Footer = {
  controller: function() {

  },
  view: function(ctrl) {
    return (
        {tag: "div", attrs: {className:"footer"}, children: [
          "FOOTER !"
        ]}
    )
  }
};


module.exports = Footer;
},{"../ws/_wsCtrl.js":16,"./api.msx":1}],7:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');

var Home = {
  controller: function() {
    m.redraw.strategy("diff")
    $.cookie('url', m.route(), {path: "/"})
    api.rd("Controller: Home");
    var ctrl = this;
    ctrl.add = function(input){
      ctrl.inputPost('')
    };
    ctrl.inputPost = m.prop('');

    if(!wsCtrl.data.post.init){
      wsCtrl.send(wsCtrl.sendData("initPost", {}));
    }

    rd.home(function(){m.redraw()});
  },
  view: function(ctrl) {
    api.rd("home:" + redraw.home);
    redraw.home++;
    return (
        {tag: "div", attrs: {className:"ui grid  main-content "}, children: [
          {tag: "div", attrs: {className:"three wide column"}, children: [
              {tag: "div", attrs: {className:"ui  home-post-Wr mh500"}, children: [
                  {tag: "div", attrs: {className:"trending"}, children: [
                      {tag: "h3", attrs: {}, children: ["Trending Now (fake)"]}, 
                      {tag: "ul", attrs: {}, children: [
                          {tag: "li", attrs: {}, children: ["What should I know about food in Cancun, Mexico?"]}, 
                          {tag: "li", attrs: {}, children: ["What films were so bad, they destroyed the actors' careers?"]}, 
                          {tag: "li", attrs: {}, children: ["Why do Americans think eggs are a dairy product?"]}, 
                          {tag: "li", attrs: {}, children: ["I have good grades but I feel like I do not know anything properly. How should I deal with this situation?"]}, 
                          {tag: "li", attrs: {}, children: ["2016 Academy Award Nominations"]}, 
                          {tag: "li", attrs: {}, children: ["Death of Alan Rickman"]}, 
                          {tag: "li", attrs: {}, children: ["Death of David Bowie"]}, 
                          {tag: "li", attrs: {}, children: ["Sherlock: The Abominable Bride"]}, 
                          {tag: "li", attrs: {}, children: ["Making a Murderer"]}
                      ]}
                  ]}

              ]}, 
              {tag: "div", attrs: {className:"ui  home-post-Wr mh300"}}
          ]}, 

            {tag: "div", attrs: {className:"nine wide column"}, children: [

            {tag: "div", attrs: {className:"ui home-post-Wr "}, children: [

              {tag: "div", attrs: {className:"ui form postWr postContainer"}, children: [
                {tag: "div", attrs: {className:"field"}, children: [
                  {tag: "textarea", attrs: {className:"auto-size new-post", 
                            rows:"2", 
                            placeholder:"What's on your mind?", 
                            config:function (element, isInit, ctx) {

                                        if(!isInit) {
                                          if(wsCtrl.userId.length == 0){
                                            $(element).on('click input', function(){
                                              api.signin();
                                              element.value = ''
                                            })
                                          }

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
                  {tag: "button", attrs: {className:"ui mini primary button", 
                       onclick:function(e){
                        m.redraw.strategy("none");
                        $('.postWr').addClass('loading');
                        $.ajax({
                            type: "POST",
                            url: "/post",
                            data: JSON.stringify({content: ctrl.inputPost()}),
                            contentType: "application/json",
                            dataType: "text",
                            success: function(data){
                               ctrl.inputPost("");
                               $('.new-post').css('height', 41);
                               $('.postWr').removeClass('loading');
                               rd.home(function(){m.redraw()})
                            }
                         });
                       }
                  }, children: ["Post"]}, 
                  {tag: "span", attrs: {className:"clear"}}
                ]}
              ]}
            ]}, 

              !wsCtrl.data.post.init?(
                  (wsCtrl.userId.length>0)?(
                  {tag: "div", attrs: {className:"ui segment loading mh300 noBor noSha"}

                  }
                  ):(
                  {tag: "div", attrs: {className:"ui segment mh300 noBor noSha", style:"text-align: center"}, children: [
                    "Please login to see posts!"
                  ]}
              )
              ):(
                  wsCtrl.data.post.list.map(function(post){
                    return (
                        {tag: "div", attrs: {className:"ui home-post-Wr"}, children: [
                          {tag: "div", attrs: {className:"ui postContainer postDemo"}, children: [

                            {tag: "div", attrs: {className:"ui list"}, children: [
                              {tag: "div", attrs: {className:"item"}, children: [
                                  {tag: "a", attrs: {className:"fl avatar ulpt", href:"/@/" + post.user.id}, children: [
                                    {tag: "img", attrs: {className:"image", src:(post.user.avatar.length>0)?("/getimage/thumb/" + post.user.avatar):(wsCtrl.defaultAvata)}}
                                  ]}, 
                                  {tag: "div", attrs: {className:"content"}, children: [
                                    {tag: "span", attrs: {className:"header"}, children: [{tag: "a", attrs: {className:"name ulpt", href:"/@/" + post.user.id}, children: [post.user.name]}]}, 
                                    {tag: "div", attrs: {className:"description"}, children: [api.time(post.published)]}
                                  ]}
                             ]}

                            ]}, 

                            {tag: "p", attrs: {className:"content-post"}, children: [
                              post.content
                            ]}, 

                            {tag: "div", attrs: {className:"ui horizontal list extra-post"}, children: [
                              {tag: "div", attrs: {className:"item"}, children: [
                                {tag: "a", attrs: {className:"mini ui  basic button"}, children: [
                                  {tag: "i", attrs: {className:"heart icon"}}, 
                                  post.info.nbLike
                                ]}
                              ]}, 
                              {tag: "div", attrs: {className:"item"}, children: [
                                {tag: "a", attrs: {className:"mini ui basic button"}, children: [
                                  {tag: "i", attrs: {className:"comment icon"}}, 
                                  post.info.nbComment
                                ]}
                              ]}, 
                              {tag: "div", attrs: {className:"item"}, children: [
                                {tag: "a", attrs: {className:"mini ui basic button"}, children: [
                                  {tag: "i", attrs: {className:"share icon"}}, 
                                  post.info.nbShare
                                ]}
                              ]}
                            ]}


                          ]}
                        ]}
                    )
                  })
              )


          ]}, 
          {tag: "div", attrs: {className:"four wide column"}, children: [
            {tag: "div", attrs: {className:"ui  home-post-Wr mh500"}, children: [
              {tag: "div", attrs: {className:"trending"}, children: [
                {tag: "h3", attrs: {}, children: ["HOT NETWORK QUESTIONS (fake)"]}, 
                {tag: "ul", attrs: {}, children: [
                  {tag: "li", attrs: {}, children: ["What is the most terrifying experience you have had while travelling?"]}, 
                  {tag: "li", attrs: {}, children: ["Which actors have won Oscars without an Oscar-worthy performance?"]}, 
                  {tag: "li", attrs: {}, children: ["Does vending machine black coffee contain sugar?"]}, 
                  {tag: "li", attrs: {}, children: ["What famous movie lines were dramatic and serious or poignant at the time but now are almost comical in pop culture?"]}, 
                  {tag: "li", attrs: {}, children: ["Which is the most astonishing piece of code you've ever seen in your life?"]}, 
                  {tag: "li", attrs: {}, children: ["What do natives call San Francisco?"]}, 
                  {tag: "li", attrs: {}, children: ["Why does \"The Force Awakens\" use an image language associated with national socialism for the First Order?"]}, 
                  {tag: "li", attrs: {}, children: ["A Treasure Chest for your Post-apocalyptic Children"]}, 
                  {tag: "li", attrs: {}, children: ["Phrase when you offer someone something but it's really them who are paying for it"]}, 
                  {tag: "li", attrs: {}, children: ["Why do academic journals usually have continuous page numbering?"]}
                ]}
              ]}

            ]}
          ]}
        ]}
    )
  }
};

module.exports = Home;
},{"../ws/_wsCtrl.js":16,"./api.msx":1}],8:[function(require,module,exports){
var wsCtrl = require('../../ws/_wsCtrl.js');
var api = require('.././api.msx');

var Friend = function(ctrl){ return (
    {tag: "div", attrs: {}, children: [
      {tag: "a", attrs: {className:"item nofity border-left-icon message-button", 
         onclick:function(){rd.nav(ctrl.displayFriend());}
      }, children: [
        {tag: "a", attrs: {href:"javascript:void(0)"}, children: [{tag: "i", attrs: {className:"large icon add user users-icon"}}]}, 
        (wsCtrl.data.makeFriend.n>0)?({tag: "div", attrs: {className:"floating ui red label num-label"}, children: [wsCtrl.data.makeFriend.n]}):""
      ]}, 
      {tag: "div", attrs: {className:"notifyWr"}, children: [
        (wsCtrl.data.makeFriend.display)?(
            {tag: "div", attrs: {className:"inNotify", 
                 config:
                    function(el, isInit, ctx){
                      if(!isInit){
                      console.log("////");
                         $(el).on('mouseleave', function(){
                             if(!$('.menu:hover').length > 0){
                                $(el).unbind('mouseleave');
                                $('.menu').unbind('mouseleave');
                                wsCtrl.data.makeFriend.display = false;
                                rd.nav(function(){m.redraw()})
                             }
                          });

                         $('.menu').on('mouseleave', function(){
                            if(!$(el).filter(':hover').length > 0){
                              $(el).unbind('mouseleave');
                              $('.menu').unbind('mouseleave');
                              wsCtrl.data.makeFriend.display = false;
                              rd.nav(function(){m.redraw()})
                           }
                          })
                      }
                    }
                 
            }, children: [
              {tag: "div", attrs: {className:"corner-right"}, children: [{tag: "div", attrs: {className:"tr"}}]}, 
              {tag: "div", attrs: {className:"ui raised  attracted segment notify-content sha3 pad0"}, children: [
                {tag: "div", attrs: {className:"ui top attracted label tran"}, children: [
                  "Friend Requests"
                ]}, 
                !wsCtrl.data.makeFriend.init?"LOADING":(
                    {tag: "div", attrs: {}, children: [
                      wsCtrl.data.makeFriend.listRequests.map(function(friend){
                      return (
                          {tag: "div", attrs: {className:"notifyFriend clearfix"}, children: [
                            {tag: "span", attrs: {className:"ui list fleft"}, children: [
                              {tag: "div", attrs: {className:"item"}, children: [
                                {tag: "img", attrs: {className:"ui avatar image", src:(friend.avatar.length>0)?("/getimage/thumb/" + friend.avatar):wsCtrl.defaultAvata}}, 
                                  {tag: "div", attrs: {className:"content"}, children: [
                                    {tag: "a", attrs: {className:"header", href:"/@/" + friend.id, config:m.route}, children: [friend.name]}, 
                                    {tag: "div", attrs: {className:"description"}, children: ["wanting to make friend with you"]}
                                  ]}
                              ]}
                            ]}, 
                            {tag: "span", attrs: {className:"mini ui buttons fright"}, children: [
                              {tag: "a", attrs: {href:("/rel/request/" + friend.id), className:"ui positive button", 
                                onclick:function(e){
                                  e.preventDefault();
                                    $.post($(this).attr('href'),
                                       function(data) {
                                         //alert("Data Loaded: " + data);

                                         wsCtrl.data.makeFriend.listRequests = wsCtrl.data.makeFriend.listRequests.filter(function (el) {
                                            return el.id != friend.id;
                                         });

                                         rd.nav(function(){
                                         if(wsCtrl.data.makeFriend.listRequests.length < 1 ){
                                            wsCtrl.data.makeFriend.display = false;
                                         }
                                         m.redraw()
                                         });
                                       }
                                    );
                                }
                              }, children: ["Cormfirm"]}, 
                              {tag: "div", attrs: {className:"or"}}, 
                              {tag: "a", attrs: {className:"ui  button", 
                                 href:("/rel/reject/" + friend.id), 
                                 onclick:function(e){
                                  e.preventDefault();
                                    $.post($(this).attr('href'),
                                       function(data) {
                                         //alert("Data Loaded: " + data);

                                         wsCtrl.data.makeFriend.listRequests = wsCtrl.data.makeFriend.listRequests.filter(function (el) {
                                            return el.id != friend.id;
                                         });

                                         rd.nav(function(){
                                         if(wsCtrl.data.makeFriend.listRequests.length < 1 ){
                                            wsCtrl.data.makeFriend.display = false;
                                         }
                                         m.redraw()
                                         });
                                       }
                                    );
                                }
                              }, children: ["Reject"]}
                            ]}
                          ]}
                      )
                      })
                    ]}
                )
              ]}
            ]}
        ):""
      ]}
    ]}
) };

module.exports = Friend;
},{"../../ws/_wsCtrl.js":16,".././api.msx":1}],9:[function(require,module,exports){
var wsCtrl = require('../../ws/_wsCtrl.js');
var api = require('.././api.msx');


var LoginButton = function(ctrl){ return(
    {tag: "div", attrs: {className:"border-left-icon login-button"}, children: [
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
                {tag: "a", attrs: {href:"javascript:void(0)", className:"ui teal button", 
                   config:function(ele, isInit, ctx){
                    if(!isInit){
                      $(ele).on('click', function(){
                           api.signup()
                           rd.nav(function(){ctrl.displayLogin = false;m.redraw()})
                        })

                      }
                    }
                  
                }, children: ["Signup"]}
              ]}
            ]}
          ]}
        ]}):""
      ]}
    ]}
)}

module.exports = LoginButton;
},{"../../ws/_wsCtrl.js":16,".././api.msx":1}],10:[function(require,module,exports){
var wsCtrl = require('../../ws/_wsCtrl.js');
var api = require('.././api.msx');

var MessageButton = function(ctrl){ return (
    {tag: "div", attrs: {}, children: [
      {tag: "a", attrs: {className:"item nofity border-left-icon message-button", 
         onclick:function(){rd.nav(ctrl.displayNofity());}
      }, children: [
        {tag: "a", attrs: {href:"javascript:void(0)"}, children: [{tag: "i", attrs: {className:"large mail icon"}}]}, 
        (wsCtrl.data.notify.n>0)?({tag: "div", attrs: {className:"floating ui red label num-label"}, children: [wsCtrl.data.notify.n]}):""
      ]}, 
      {tag: "div", attrs: {className:"notifyWr"}, children: [
        !wsCtrl.data.notify.display?"":(
        {tag: "div", attrs: {className:"inNotify", 
          config:function(el, isInit){
            if(!isInit){
               $(el).on('mouseleave', function(){
                   if(!$('.menu:hover').length > 0){
                      $(el).unbind('mouseleave');
                      $('.menu').unbind('mouseleave');
                      wsCtrl.data.notify.display = false;
                      rd.nav(function(){m.redraw()})
                   }
                });

               $('.menu').on('mouseleave', function(){
                  if(!$(el).filter(':hover').length > 0){
                    $(el).unbind('mouseleave');
                    $('.menu').unbind('mouseleave');
                    wsCtrl.data.notify.display = false;
                    rd.nav(function(){m.redraw()})
                 }
                })
            }
          }
        }, children: [
          {tag: "div", attrs: {className:"corner-right"}, children: [{tag: "div", attrs: {className:"tr"}}]}, 
          {tag: "div", attrs: {className:"ui raised  attracted segment notify-content sha3 pad0"}, children: [
            {tag: "div", attrs: {className:"ui top attracted label tran"}, children: [
              "Inbox (", wsCtrl.data.notify.n, ")"
            ]}, 
            !wsCtrl.data.notify.init?"LOADING":(
            {tag: "div", attrs: {}, children: [
              
                  wsCtrl.data.notify.notifyMessage.map(function(mes){
                      return (
                      {tag: "div", attrs: {className:"notifyMes clearfix", 
                           config:
                                    function(el, isInit, ctx){

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
                        {tag: "div", attrs: {className:"nleft"}}, 
                        {tag: "div", attrs: {className:"nright"}, children: [
                          {tag: "span", attrs: {className:"notifyName"}, children: [
                            mes.user.name
                          ]}, 
                          {tag: "span", attrs: {className:"mesNumber"}, children: [
                             "(", mes.n, ")"
                          ]}, 
                          {tag: "div", attrs: {className:"lastMes"}, children: [
                            mes.lm.mes
                          ]}
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
},{"../../ws/_wsCtrl.js":16,".././api.msx":1}],11:[function(require,module,exports){
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

                  $(el).on('mouseleave', function(){
                     if(!$('.menu:hover').length > 0){
                        $(el).unbind('mouseleave');
                        $('.menu').unbind('mouseleave');
                        ctrl.displayUser = false;
                        rd.nav(function(){m.redraw()})
                     }
                  });

                   $('.menu').on('mouseleave', function(){
                      if(!$(el).filter(':hover').length > 0){
                        $(el).unbind('mouseleave');
                        $('.menu').unbind('mouseleave');
                        ctrl.displayUser = false;
                        rd.nav(function(){m.redraw()})
                     }
                    })
               }
             }
        }, children: [
          {tag: "div", attrs: {className:"corner-right"}, children: [{tag: "div", attrs: {className:"tr"}}]}, 
          {tag: "div", attrs: {className:"ui  attracted segment notify-content sha3"}, children: [
            {tag: "a", attrs: {href:"/@/" + wsCtrl.userId, class:"fluid ui small button", 
              config:m.route, 
              onclick:function(){
                ctrl.displayUser = false;
                rd.nav(function(){m.redraw()})
              }
            }, children: [
              {tag: "i", attrs: {class:"user icon"}}, 
              "Profile"
            ]}, 
            {tag: "br", attrs: {}}, 
            {tag: "a", attrs: {href:"/logout", class:"fluid ui small button"}, children: [
              {tag: "i", attrs: {class:"sign out icon"}}, 
              "Logout"
            ]}
          ]}
        ]}):""
      ]}
    ]}
) }

module.exports = UserButton;
},{"../../ws/_wsCtrl.js":16,".././api.msx":1}],12:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');

var UserButton = require('./menu_button/User.msx');
var LoginButton = require('./menu_button/Login.msx');
var MessageButton = require('./menu_button/Message.msx');
var FriendButton = require('./menu_button/Friend.msx');

var Nav = {
  controller: function(){
    m.redraw.strategy("diff")
    api.rd("Controller: nav");
    var ctrl = this;
    ctrl.ping = m.prop(wsCtrl.ping);
    ctrl.userNumber = m.prop(0);

    var initPing = setInterval(function(){
      if(wsCtrl.ping) {
        clearInterval(initPing);
        rd.nav(function(){ctrl.ping(wsCtrl.ping);ctrl.userNumber(wsCtrl.total); m.redraw();})
      }
    }, 200);

    setInterval(function(){
      rd.nav(function(){ctrl.ping(wsCtrl.ping);ctrl.userNumber(wsCtrl.total); m.redraw();})
    }, 1000);
    ctrl.displayUser = false;
    ctrl.toggleUser = function(){
      wsCtrl.data.notify.display = false;
      wsCtrl.data.makeFriend.display = false;
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
      wsCtrl.data.makeFriend.display = false;
      ctrl.displayUser = false;
      wsCtrl.data.notify.display = !wsCtrl.data.notify.display;
    };



    ctrl.displayFriend = function(){
      if(wsCtrl.data.makeFriend.display == false ) {
        wsCtrl.data.makeFriend.init = false;
        wsCtrl.send(wsCtrl.sendData("gmf", 0))
      }
      wsCtrl.data.notify.display = false;
      ctrl.displayUser = false;
      wsCtrl.data.makeFriend.display = !wsCtrl.data.makeFriend.display;
    }

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
            {tag: "i", attrs: {className:"large icon newspaper"}}
          ]}, 
          {tag: "a", attrs: {href:"/chatroom", 
             className:((m.route().substring(0, 9) == "/chatroom")?"active":"") + " item route-button", 
             config:m.route
          }, children: [
            {tag: "i", attrs: {className:"large icon comments"}}
          ]}, 
          {tag: "div", attrs: {className:"ui category search item"}, children: [
            {tag: "div", attrs: {className:"ui icon input"}, children: [
              {tag: "input", attrs: {className:"", type:"text", style:"width: 400px;", placeholder:"Search ..."}}, 
                {tag: "i", attrs: {className:"search link icon"}}
            ]}, 
            {tag: "div", attrs: {className:"results"}}
          ]}, 


           (wsCtrl.userId.length>0)?({tag: "div", attrs: {className:"right menu"}, children: [

             {tag: "div", attrs: {className:"item"}, children: [
               {tag: "i", attrs: {className:"large icon  users"}}, 
               {tag: "div", attrs: {className:"bold"}, children: [ctrl.userNumber()?(ctrl.userNumber()):("?")]}
             ]}, 
             {tag: "a", attrs: {href:"javascript:void(0)", className:"item", 
                config:function(el, isInit, ctx){
                          if(!isInit){
                            $(el).popup({
                              popup : $('.ui.popup.show-ping'),
                              position : 'bottom right',
                              on    : 'hover'
                            })
                          }
                      }
                    
             }, children: [
               (ctrl.ping()>8000 || ctrl.ping() < 0)?(
                {tag: "i", attrs: {className:"large spinner loading " + ((ctrl.ping()>8000)?"red":"") + " icon zero-margin-right"}}
                   ):(
                {tag: "i", attrs: {className:"large " + ((ctrl.ping()<500)?"":((ctrl.ping()<1500)?"yellow":"red")) + " icon heartbeat zero-margin-right", 
                   config:function(element, isInit, ctx){
                      if(!isInit){
                        setTimeout(function fnJiggle(){
                          $(element).transition('jiggle')
                          if(ctrl.ping() > 0 || ctrl.ping <= 500){
                            setTimeout(fnJiggle, 1000)
                          } else {
                            setTimeout(fnJiggle, 500)
                          }
                        },1000)
                      }
                     }
                   
                }}
               )
             ]}, 
           FriendButton(ctrl), 
            MessageButton(ctrl), 
            UserButton(ctrl)
          ]}):(
              {tag: "div", attrs: {className:"right menu"}, children: [
                {tag: "div", attrs: {className:"item"}, children: [
                  {tag: "i", attrs: {className:"large icon users"}}, 
                  {tag: "div", attrs: {className:"bold"}, children: [ctrl.userNumber()?(ctrl.userNumber()):("?")]}
                ]}, 
                {tag: "a", attrs: {href:"javascript:void(0)", className:"item", 
                   config:function(el, isInit, ctx){
                          if(!isInit){
                            $(el).popup({
                              popup : $('.ui.popup.show-ping'),
                              position : 'bottom right',
                              on    : 'hover'
                            })
                          }
                      }
                    
                }, children: [
                  (ctrl.ping()>8000 || ctrl.ping() < 0)?(
                  {tag: "i", attrs: {className:"large spinner loading " + ((ctrl.ping()>8000)?"red":"") + " icon zero-margin-right"}}
                      ):(
                  {tag: "i", attrs: {className:"large " + ((ctrl.ping()<500)?"":((ctrl.ping()<1500)?"yellow":"red")) + " icon heartbeat zero-margin-right", 
                     config:function(element, isInit, ctx){
                      if(!isInit){
                        setTimeout(function fnJiggle(){
                          $(element).transition('jiggle');
                          if(ctrl.ping() > 0 || ctrl.ping <= 500){
                            setTimeout(fnJiggle, 1000)
                          } else {
                            setTimeout(fnJiggle, 500)
                          }
                        },1000)
                      }
                     }
                   
                  }}
                      )
                ]}, 
                LoginButton(ctrl)
              ]}
              ), 
          Ping(ctrl), 
          {tag: "div", attrs: {className:"ui modal sign-up"}, children: [
            {tag: "div", attrs: {className:"ui segment loading", style:"min-height: 500px;"}
            }
          ]}, 
          {tag: "div", attrs: {className:"ui modal sign-in"}, children: [
            {tag: "div", attrs: {className:"ui segment loading", style:"min-height: 300px;"}
            }
          ]}
        ]}
    )
  }
};

var Ping = function(ctrl){
  return(
      {tag: "div", attrs: {className:"ui fluid popup show-ping"}, children: [
        {tag: "div", attrs: {className:(ctrl.ping()<500)?"green":((ctrl.ping()<1500)?"yellow":"red")}, children: [ctrl.ping(), " ms"]}
      ]}
  )
};

module.exports = Nav;
},{"../ws/_wsCtrl.js":16,"./api.msx":1,"./menu_button/Friend.msx":8,"./menu_button/Login.msx":9,"./menu_button/Message.msx":10,"./menu_button/User.msx":11}],13:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');

var Room = {
  controller: function() {
    m.redraw.strategy("diff")
    $.cookie('url', m.route(), {path: "/"});
    var ctrl = this;
    ctrl.id = m.route.param("roomId");
    ctrl.param = m.prop(m.route.param("roomId"));
    ctrl.loadMore = true;
    ctrl.scrollBottom = true;
    ctrl.add = function () {
      var input = wsCtrl.inputChat(ctrl.id)().trim();
      //input = input.replace(/\n/g, '');
      if (input) {
        wsCtrl.send(wsCtrl.sendData("chat", {room: ctrl.id, d: input}));
        wsCtrl.inputChat(ctrl.id)('');
      }
      rd.room(function(){m.redraw()})
    };

    wsCtrl.send(wsCtrl.sendData("initChat", {t: "room", v: ctrl.param()}));

    var intervalRoom = setInterval(function(){
      wsCtrl.send(wsCtrl.sendData("sub", {t: "room", v: ctrl.param()}));
    }, 30000);

    ctrl.onunload = function() {
      wsCtrl.send(wsCtrl.sendData("unSub", {t: "room", v: ctrl.param()}));
      wsCtrl.clearOldRoom(ctrl.id);
      clearInterval(intervalRoom)
    };
    console.log("ctrl room !");
    rd.room();
  },
  view: function(ctrl) {
    return (
        {tag: "div", attrs: {className:"ui grid main-content "}, children: [
          {tag: "div", attrs: {className:"eleven wide column room-chat"}, children: [
            {tag: "div", attrs: {className:"ui segment segWr"}, children: [
              {tag: "div", attrs: {className:"ui padded grid"}, children: [
                {tag: "div", attrs: {className:"thirteen wide  column light-border-right pad-bot0"}, children: [
                  Comments(ctrl)
                ]}, 
                {tag: "div", attrs: {className:"three wide column"}, children: [
                  {tag: "div", attrs: {className:"room-user"}, children: [

                    {tag: "h5", attrs: {className:"ui dividing header"}, children: ["List Users !"
                    ]}, 
                    {tag: "div", attrs: {}, children: [
                      {tag: "div", attrs: {}, children: [
                        (!wsCtrl.getRoom(ctrl.id).initOk)?(
                            {tag: "div", attrs: {className:"ui active loader"}}
                        ):(
                            {tag: "div", attrs: {className:"ui list users-in-room"}, children: [
                              wsCtrl.userInRoom(ctrl.id).map(function(user){
                                return (

                                    {tag: "div", attrs: {className:"item"}, children: [

                                      {tag: "i", attrs: {className:"user " + ((user.role == "Admin")?"red":((user.role == "Mod")?"yellow":"blue"))  +" icon"}}, 

                                      {tag: "div", attrs: {className:"content"}, children: [
                                        {tag: "a", attrs: {className:"ulpt", href:"/@/" + user.id, config:m.route}, children: [
                                          user.name
                                        ]}
                                      ]}
                                    ]}

                                )
                              })
                            ]}
                        )
                        
                      ]}

                    ]}
                  ]}
                ]}
              ]}, 
              {tag: "div", attrs: {className:"ui padded grid "}, children: [
                {tag: "div", attrs: {className:"thirteen wide column light-border-right pad-top0"}, children: [
                  {tag: "div", attrs: {className:"ui divider"}}, 
                  {tag: "div", attrs: {className:"ui comments mar0"}, children: [
                    {tag: "div", attrs: {className:"comment"}, children: [
                      {tag: "a", attrs: {className:"avatar"}, children: [
                        {tag: "img", attrs: {src:(wsCtrl.avatar.length>0)?("/getimage/thumb/" + wsCtrl.avatar):("/assets/avatar/2.jpg")}}
                      ]}, 
                      {tag: "div", attrs: {className:"ui form content"}, children: [
                        {tag: "div", attrs: {className:"field", style:"display:inline"}, children: [
                        {tag: "textarea", attrs: {rows:"1", style:"max-height: 150px", 
                                  config:function (element, isInit, ctx) {
                                          if(!isInit) {
                                            if(wsCtrl.userId.length == 0){
                                              $(element).on('click input', function(){
                                                api.signin();
                                                element.value = ''
                                              })
                                            } else {
                                              $(element).on('input', function(){
                                                wsCtrl.inputChat(ctrl.id)($(element).val())
                                              });
                                            }
                                            $(element).textareaAutoSize();
                                            $(element).attrchange({
                                              //trackValues: true,
                                              callback: function (event) {
                                                var boxNode = document.getElementsByClassName('box-comment')[0];
                                                var box = $('.box-comment');
                                                var input = $(element);


                                                if (box.scrollTop() + box.innerHeight() >= box.prop('scrollHeight')) {
                                                  box.css('height', 490 + 36 - $(element).outerHeight());
                                                  boxNode.scrollTop = boxNode.scrollHeight;
                                                } else {
                                                  box.css('height', 490 + 36 - $(element).outerHeight());
                                                }

                                              }
                                            });


                                          }
                                          element.value = wsCtrl.inputChat(ctrl.id)();
                                          if(element.value.length<1){
                                            $(element).css('height', '36.4286px')
                                          }
                                        }, 
                                      
                                  onkeypress:function(e){
                                          if(e.keyCode == 13 && !e.shiftKey) {
                                            m.redraw.strategy("none");
                                            if (wsCtrl.inputChat(ctrl.id)().length < 1) {
                                             console.log("chat length < 1")
                                              return false;
                                            } else {
                                              var source = e.target || e.srcElement;
                                              ctrl.add();
                                              return false;
                                            }
                                          }else{
                                            m.redraw.strategy("none");
                                            if(e.keyCode == 13 && e.shiftKey && wsCtrl.inputChat(ctrl.id)().length < 1){
                                              return false;
                                            }
                                          }
                                        }, 
                                      
                                  placeholder:"Click here to type a chat message"
                        }}
                        ]}
                      ]}
                    ]}
                  ]}
                ]}, 
                {tag: "div", attrs: {className:"three wide column pad-top0"}, children: [
                  {tag: "div", attrs: {className:"ui divider "}}, 
                  {tag: "div", attrs: {}}
                ]}
              ]}
            ]}
          ]}, 

          {tag: "div", attrs: {className:"five wide column"}, children: [
            {tag: "div", attrs: {className:"ui mh500"}

            }
          ]}
        ]}
    )
  }
};

var Comments = function(ctrl){
  return (
      {tag: "div", attrs: {className:"ui comments room-box"}, children: [
        {tag: "h5", attrs: {className:"ui dividing header"}, children: ["Room: ", ctrl.id, 
          {tag: "span", attrs: {className:"fr"}, children: [
            {tag: "i", attrs: {className:"tiny users left middle aligned icon", style:"margin: 0 5px;"}, children: [wsCtrl.getRooms(ctrl.id).u]}, 
            {tag: "i", attrs: {className:"tiny plug left middle aligned icon", style:"margin: 0 5px;"}, children: [wsCtrl.getRooms(ctrl.id).c]}
        ]}
        ]}, 

        {tag: "div", attrs: {className:"box-commentWr"}, children: [
          {tag: "div", attrs: {className:"box-comment", 
               config:function(element, isInit, context) {
                      if(context.flagScroll == true) context.fixPos = context.prevScrollTop + element.scrollHeight - context.prevScrollHeight;
                      if(wsCtrl.getRoom(ctrl.id).gettingPrev == true){
                        element.scrollTop = context.prevScrollTop + element.scrollHeight - context.prevScrollHeight;
                        if(downkey){
                          context.flagScroll = false;
                          $(element).on('scroll', function f2(){
                            element.scrollTop = context.fixPos;
                            $(document).on('mouseup', function mouseup2(){
                                $(document).off('mouseup', mouseup2);
                                $(element).off('scroll', f2);
                                context.flagScroll = true;
                                wsCtrl.getRoom(ctrl.id).gettingPrev = false;
                            })
                          })
                        } else {
                          wsCtrl.getRoom(ctrl.id).gettingPrev = false;
                        }
                      }

                      if(context.flagScroll == undefined) context.flagScroll = true


                      if(context.run && wsCtrl.getRoom(ctrl.id).initOk){
                         element.scrollTop = element.scrollHeight;
                         context.run = false;
                      }

                      if(!isInit){
                        
                        context.run = true;
                        $(element).on('scroll', function f1(){
                        if(element.scrollHeight - element.scrollTop - element.clientHeight > 50){
                            ctrl.scrollBottom = false;
                        } else {
                            ctrl.scrollBottom = true;
                        }
                        if(wsCtrl.getRoom(ctrl.id).initOk){
                          if(element.scrollTop < 10 && wsCtrl.getRoom(ctrl.id).gettingPrev == false && wsCtrl.loadMoreComments(ctrl.id)){
                            ctrl.loadMore = false;
                            wsCtrl.getRoom(ctrl.id).gettingPrev = true;
                            wsCtrl.send(wsCtrl.sendData("prevChat", {t: "room", v: ctrl.param(), lastTime: wsCtrl.commentsInRoom(ctrl.id)[0].time}));
                          }
                        }
                          context.prevScrollTop = element.scrollTop
                        });
                      }

                      var addLength = (element.scrollHeight - context.prevScrollHeight) || 0;


                        if(ctrl.scrollBottom){
                          element.scrollTop = element.scrollHeight;
                        }
                      context.prevScrollTop = element.scrollTop;
                      context.prevScrollHeight = element.scrollHeight
                    }
                 
          }, children: [
            (!wsCtrl.getRoom(ctrl.id).initOk)?(
                {tag: "div", attrs: {className:"ui active loader"}}
            ):(
                {tag: "div", attrs: {}
                , children: [
                  wsCtrl.commentsInRoom(ctrl.id).map(function(comment){
                    return (
                        {tag: "div", attrs: {className:"comment", 
                             key:comment.time
                        }, children: [
                          {tag: "a", attrs: {className:"avatar ulpt", href:"/@/" + comment.userId, config:m.route}, children: [
                            {tag: "img", attrs: {src:comment.avatar}}
                          ]}, 
                          {tag: "div", attrs: {className:"content"}, children: [
                            {tag: "a", attrs: {className:"author ulpt", href:"/@/" + comment.userId, config:m.route}, children: [comment.user]}, 
                            {tag: "div", attrs: {className:" metadata fr"}, children: [
                              {tag: "span", attrs: {className:"date"}, children: [api.time(comment.time)]}
                            ]}, 
                            {tag: "div", attrs: {className:"text"}, children: [
                              comment.comment
                            ]}

                          ]}
                        ]}
                    )
                  })
                  
                ]}
            )
            
          ]}
        ]}
      ]}
  )
}

module.exports = Room;
},{"../ws/_wsCtrl.js":16,"./api.msx":1}],14:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');


var User = {
  controller: function() {
    var ctrl = this;
    ctrl.userId = m.route.param('user');
    ctrl.user = m.prop({});
    //ctrl.userId = m.route.param('user');
    //m.request({method: "GET", url: "/api/getUser/" + ctrl.userId}).then(
    //    function(user){
    //        ctrl.user(user);
    //    }
    //);
    ctrl.setup = function(){
      rd.user();
    };

    ctrl.error = function(){
      m.route('/');
    }
    ctrl.request = api.requestWithFeedback2({method: "GET", url: "/api/getUser/" + ctrl.userId}, ctrl.user, ctrl.setup, ctrl.error);
    rd.user()
  },
  view: function(ctrl) {
    return (!ctrl.request.ready()?(
            {tag: "div", attrs: {className:"ui segment loading mh500"}

            }
        ):(
            {tag: "div", attrs: {className:"ui grid main-content sha2"}, children: [
              {tag: "div", attrs: {className:"head-user ui grid"}, children: [
                {tag: "div", attrs: {className:"four wide column", style:"  min-height: 250px"}, children: [
                  {tag: "div", attrs: {className:"avatarWr"}, children: [
                    {tag: "img", attrs: {id:"avatarImg", src:(ctrl.user().avatar.length>0)?("/getimage/small/" + ctrl.user().avatar):"/assets/img/user.jpg", width:"180", height:"180"}}
                  ]}

                ]}, 
                {tag: "div", attrs: {className:"twelve wide column", style:"b min-height: 250px"}, children: [
                  {tag: "h2", attrs: {className:"ui header"}, children: [
                    ctrl.user().name
                  ]}, 
                  {tag: "div", attrs: {className:"edit"}, children: [
                    (wsCtrl.userId !== ctrl.userId)?[

                    ]:(
                        {tag: "a", attrs: {className:"ui basic button", href:"/settings", config:m.route}, children: [
                          {tag: "i", attrs: {className:"write square icon"}}, 
                          "Edit Profile"
                        ]}
                    )


                  ]}, 
                  {tag: "div", attrs: {className:"ui divider"}}, 
                  "...", 

                  {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, 

                  {tag: "div", attrs: {className:"ui divider"}}, 
                  {tag: "div", attrs: {className:"infoWr"}, children: [
                    {tag: "div", attrs: {className:"relation_actions"}, children: [
                      (wsCtrl.userId === ctrl.user().username)?[
                        {tag: "a", attrs: {className:"tiny ui  basic  relation button hint--bottom hover_text custom", tabindex:"0", href:"#"}, children: [
                          {tag: "span", attrs: {className:"content"}, children: [ctrl.user().extra.nbFollower, " Folowers"]}
                        ]},
                        {tag: "a", attrs: {className:"tiny ui  basic  relation button hint--bottom hover_text custom", tabindex:"0", href:"#"}, children: [
                          {tag: "span", attrs: {className:"content"}, children: [ctrl.user().extra.nbFriends, " Friends"]}
                        ]}
                      ]:[(!ctrl.user().extra.relation)?(
                          {tag: "a", attrs: {className:"tiny ui  basic animated relation button hint--bottom hover_text custom", 
                             tabindex:"0", href:"/rel/follow/" + ctrl.user().username + "?mini=1"}, children: [
                            {tag: "span", attrs: {className:"visible content"}, children: [ctrl.user().extra.nbFollower, " Follower"]}, 
                                            {tag: "span", attrs: {className:"hidden content"}, children: [
                                              {tag: "i", attrs: {className:"thumbs outline up icon"}}, 
                                              "Follow"
                                            ]}
                          ]}
                      ):(
                          {tag: "a", attrs: {className:"tiny ui blue basic animated relation button hint--bottom hover_text custom", tabindex:"0", href:"/rel/unfollow/" + ctrl.user().username + "?mini=1"}, children: [
                            {tag: "span", attrs: {className:"visible content"}, children: [ctrl.user().extra.nbFollower, " Follower"]}, 
                                                {tag: "span", attrs: {className:"hidden content"}, children: [
                                                  {tag: "i", attrs: {className:"thumbs up icon"}}, 
                                                  "Following"
                                                ]}
                          ]}
                      ),
                        (!ctrl.user().extra.friend)?(
                            (!ctrl.user().extra.makeFriend)?(
                                {tag: "a", attrs: {className:"tiny ui  basic animated relation button hint--bottom hover_text custom", tabindex:"0", href:"/rel/request/" + ctrl.user().username + "?mini=1"}, children: [
                                  {tag: "span", attrs: {className:"visible content"}, children: [ctrl.user().extra.nbFriends, " Friends"]}, 
                                                {tag: "span", attrs: {className:"hidden content"}, children: [
                                                  {tag: "i", attrs: {className:"thumbs up icon"}}, 
                                                  "Add Friend"
                                                ]}
                                ]}
                            ):(
                                {tag: "a", attrs: {className:"tiny ui olive basic animated relation button hint--bottom hover_text custom", tabindex:"0", href:"/rel/unrequest/" + ctrl.user().username + "?mini=1"}, children: [
                                  {tag: "span", attrs: {className:"visible content"}, children: [ctrl.user().extra.nbFriends, " Friends"]}, 
                                                {tag: "span", attrs: {className:"hidden content"}, children: [
                                                  {tag: "i", attrs: {className:"thumbs up icon"}}, 
                                                  "Cancel Request"
                                                ]}
                                ]}
                            )

                        ):(
                            {tag: "a", attrs: {className:"tiny ui blue basic animated relation button hint--bottom hover_text custom unfriend", tabindex:"0", href:"/rel/unfriend/" + ctrl.user().username + "?mini=1"}, children: [
                              {tag: "span", attrs: {className:"visible content unfriend"}, children: [ctrl.user().extra.nbFriends, " Friends"]}, 
                              {tag: "span", attrs: {className:"hidden content unfriend"}, children: [
                                {tag: "i", attrs: {className:"remove user icon"}}, 
                                "Unfriend"
                              ]}
                            ]}
                        )
                      ]
                      

                    ]}
                  ]}
                ]}
              ]}
            ]}
        )
    )
  }
};


module.exports = User;
},{"../ws/_wsCtrl.js":16,"./api.msx":1}],15:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');


var UserSetting = {
  controller: function() {
    var ctrl = this;
    if(wsCtrl.userId.length < 1){
      m.route("/");
    } else {
      ctrl.user = m.prop({});
      ctrl.setup = function(){
        rd.setting();
      };
      ctrl.request = api.requestWithFeedback2({method: "POST", url: "/settings"}, ctrl.user, ctrl.setup);

      rd.setting()
    }

  },
  view: function(ctrl) {
    return (!ctrl.request.ready()?(
        {tag: "div", attrs: {className:"ui segment main-content loading mh500"}

        }
    ):[
      {tag: "div", attrs: {className:"ui grid main-content sha2 "}, children: [
        {tag: "div", attrs: {className:"head-user ui grid"}, children: [
          {tag: "div", attrs: {className:"four wide column", style:"  min-height: 250px"}, children: [
            avatarWr(ctrl)
          ]}, 
          {tag: "div", attrs: {className:"twelve wide column edit-information", style:"b min-height: 250px"}, children: [

            {tag: "div", attrs: {class:"ui labeled input"}, children: [
              {tag: "div", attrs: {className:"field"}, children: ["Name:"]}, 
              {tag: "input", attrs: {id:"name", type:"text", placeholder:"Tên hiển thị!", value:ctrl.user().name}}
            ]}, 
            {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, {tag: "br", attrs: {}}, 
            {tag: "div", attrs: {className:"ui divider"}}, 
            {tag: "button", attrs: {class:"ui button", 
                    onclick:function(){
                               var sendInfo = {
                                   "name": $('#name').val()
                               };
                               console.log(sendInfo);

                               $.ajax({
                                    type: "POST",
                                    url: "/settings/updateInfo",
                                    data: JSON.stringify(sendInfo),
                                    contentType: "application/json",
                                    dataType: "text",
                                    success: function(data){
                                       wsCtrl.userName = $('#name').val();
                                    }
                               });

                           }
            }, children: [
              "Save"
            ]}, 
            {tag: "button", attrs: {class:"ui button"}, children: [
              "Reset"
            ]}
          ]}
        ]}
      ]}
    ])
  }
};


var avatarWr = function(ctrl){
  return (
      {tag: "div", attrs: {className:"ui segment avatarWr"}, children: [
        {tag: "div", attrs: {className:"ui special  cards", 
             config:function(el, isInited, ctx){
                                if(!isInited){
                                    $('.special.cards .image').dimmer({
                                      on: 'hover'
                                    });
                                }
                            }
        }, children: [

          {tag: "div", attrs: {className:"card"}, children: [
            {tag: "div", attrs: {className:"blurring dimmable image"}, children: [
              {tag: "div", attrs: {className:"ui dimmer"}, children: [
                {tag: "div", attrs: {className:"content"}, children: [
                  {tag: "div", attrs: {className:"center"}, children: [
                    {tag: "form", attrs: {id:"formUpload", enctype:"multipart/form-data", action:"/upload/image", method:"post"}, children: [
                      {tag: "input", attrs: {className:"avatar-upload", name:"picture", id:"picture", type:"file", accept:"image/*", 

                             config:function(el, isInited){
                                                            if(!isInited){
                                                                $(el).change(function (){
                                                                   var _URL = window.URL || window.webkitURL;
                                                                    var file, img;
                                                                    if ((file = this.files[0])) {
                                                                        img = new Image();
                                                                        img.onload = function () {

                                                                            if(this.width >= 180 && this.height >=180){
                                                                                $('.avatarWr').addClass('loading');
                                                                                jQuery.each(jQuery('#picture')[0].files, function(i, file) {
                                                                                    data.append('file-'+i, file);
                                                                                });
                                                                                jQuery.ajax({
                                                                                    url: '/upload/image',
                                                                                    data: data,
                                                                                    cache: false,
                                                                                    contentType: false,
                                                                                    processData: false,
                                                                                    type: 'POST',
                                                                                    success: function(data){
                                                                                        var url = "/getimage/small/" + data;
                                                                                        $('#avatarImg').attr('src', url);
                                                                                        $('.avatarWr').removeClass('loading');
                                                                                        wsCtrl.avatar = data;
                                                                                    }
                                                                                });
                                                                            } else {
                                                                                alert("Chiều rộng và chiều dài của ảnh phải lớn hơn 180px");
                                                                            }
                                                                        };
                                                                        img.src = _URL.createObjectURL(file);
                                                                    }
                                                                    var data = new FormData();

                                                                 });
                                                            }
                                                          }}
                      }
                    ]}

                  ]}
                ]}
              ]}, 
              {tag: "img", attrs: {id:"avatarImg", src:(ctrl.user().avatar.length>0)?("/getimage/small/" + ctrl.user().avatar):"/assets/img/user.jpg", width:"180", height:"180"}}
            ]}
          ]}
        ]}

      ]}
  )
}


module.exports = UserSetting;
},{"../ws/_wsCtrl.js":16,"./api.msx":1}],16:[function(require,module,exports){
var wsCtrl = {};
wsCtrl.userId = document.body.getAttribute("id");
var userId = wsCtrl.userId;

wsCtrl.userName = document.body.getAttribute("name");
var userName = wsCtrl.userName;

wsCtrl.avatar = document.body.getAttribute("avatar");

wsCtrl.mVersion = parseInt(document.body.getAttribute("mv"));
var mVersion = wsCtrl.mVersion;
wsCtrl.mRVersion = parseInt(document.body.getAttribute("mv"));
var mRVersion = wsCtrl.mRVersion;

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

wsCtrl.reconnect = false;

wsCtrl.storage = {
  chat: $.localStorage.get('chat:' + wsCtrl.userId) || []
};


wsCtrl.defaultAvata = "/assets/avatar/2.jpg";
wsCtrl.data = {
  post: {
    init: false,
    list: [],
    timepoint: Date.now(),
  },
  chatrooms: {},
  chatroom: {},
  userOnline: [],
  user: {},
  chat: [],
  makeFriend: {
    n: 0,
    listRequests: [],
    init: false,
    display: false
  },
  notify: {
    n: 0,
    notifyMessage: [],
    init: false,
    display: false
  }
};
var data = wsCtrl.data;

function initChat(){
  wsCtrl.storage.chat.map(function(user){
    data.chat.push({user: user.user, display: true, input: m.prop(''), init: false, hide: user.hide, read: true, chat: []});
    send(sendData("init_chat", {w: user.user.id, cv: 0}));
  });
}

//var ws = new WebSocket("ws://188.166.254.203:9000/socket?sri=" + sri);
var reconnect;
var delayInitWs;

function initReconnect(setTime){
  var delayReconnect;
  var delayInit;
  if(setTime === undefined) {
    delayReconnect = 8000;
    delayInit = 2000;
  } else {
    delayReconnect = setTime;
    delayInit = 0;
  }
  clearTimeout(reconnect);
  reconnect = setTimeout(function(){
    console.log("run reconnect !!")
    clearTimeout(pingSchedule);
    if(wsCtrl.ws){
      wsCtrl.ws.onerror = $.noop;
      wsCtrl.ws.onclose = $.noop;
      wsCtrl.ws.onopen = $.noop;
      wsCtrl.ws.onmessage = $.noop;
      wsCtrl.ws.close();
    }
    wsCtrl.reconnect = true;
    console.log("websocket will reconnect in " + delayInit +" ms !!")
    if(delayInitWs) clearTimeout(delayInitWs);
    delayInitWs = setTimeout(initWs, delayInit);
  }, delayReconnect);
};


function initWs(){
  var sri = Math.random().toString(36).substring(2);
  if(document.domain === "localhost") {
    wsCtrl.ws = new WebSocket("ws://" + document.domain + ":9000/socket?sri=" + sri);
  } else {
    wsCtrl.ws = new WebSocket("ws://" + document.domain + ":9903/socket?sri=" + sri);
  }
  wsCtrl.ws.onopen = function(){
    console.log('WebSocket ok');
    initReconnect();
    //console.log("prev Ping:" + wsCtrl.ping)
    wsCtrl.ping = -1;
    send(pingData());
    prevTime = Date.now();
    wsCtrl.data.userOnline = [];
    send(sendData("get_onlines", ""));

    if(wsCtrl.reconnect){
      getMissing();
    }

    initChat();
  };

  wsCtrl.ws.onmessage = function (e) {
    var m = JSON.parse(e.data);
    ctrl.listen(m)
  };

  wsCtrl.ws.onclose = function(){
    console.log("socket closed")
  };
  wsCtrl.ws.onerror = function(){
    console.log("socket error")
  };
  initReconnect(4000);
};
initWs();


var getMissing = function(){
  console.log(m.route());
  //console.log("get missing!")
  if(m.route() === "/"){
    wsCtrl.send(wsCtrl.sendData("reconnect", {t: "home", v: wsCtrl.data.post.timepoint}));
  }

  if(m.route().toString().indexOf('/chatroom/') >= 0){
    wsCtrl.send(wsCtrl.sendData("reconnect", {t: "room", v: m.route.param("roomId")}))
    if(wsCtrl.data.post.init){
      wsCtrl.send(wsCtrl.sendData("reconnect", {t: "home", v: wsCtrl.data.post.timepoint}));
    }
  }
};

//var ws = new WebSocket("ws://localhost:9000/socket?sri=" + sri);
//var ws = new WebSocket("ws://192.168.1.25:9000/socket?sri=" + sri);

var pingData = function() {
  return JSON.stringify({
    t: "p",
    v: mVersion
  });
};

wsCtrl.arrayObjectIndexOf = function(myArray, searchTerm, property) {
  for(var i = 0, len = myArray.length; i < len; i++) {
    if (myArray[i][property] === searchTerm) return i;
  }
  return -1;
};
var arrayObjectIndexOf = wsCtrl.arrayObjectIndexOf;

wsCtrl.arrayObjectIndexOf2 = function(myArray, searchTerm, property) {
  for(var i = 0, len = myArray.length; i < len; i++) {
    if (myArray[i]['user'][property] === searchTerm) return i;
  }
  return -1;
};

var arrayObjectIndexOf2 = wsCtrl.arrayObjectIndexOf2;

wsCtrl.sendData = function(t, d){
  return JSON.stringify({
    t: t,
    d: d
  })
};
var sendData = wsCtrl.sendData;

wsCtrl.send = function (message, callback) {
  waitForConnection(function () {
    wsCtrl.ws.send(message);
    if (typeof callback !== 'undefined') {
      callback();
    }
  }, 100);
};
var send = wsCtrl.send;

var waitForConnection = function (callback, interval) {
  if (wsCtrl.ws.readyState === 1) {
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
    data.chat.push({user: user, display: true, input: m.prop(''), init: false, hide: false, read: true, chat: []});
    send(sendData("init_chat", {w: user.id, cv: cv}));
    wsCtrl.storage.chat.push({user: user, hide: false});
    $.localStorage.set('chat:' + wsCtrl.userId, wsCtrl.storage.chat);
    return (data.chat.length - 1)
  }
};
var getPosChat = wsCtrl.getPosChat;

function calcPing(){
  var now = Date.now();
  wsCtrl.ping = Math.ceil(now - prevTime);
  console.log("run calc: " + wsCtrl.ping);
}

var calcTimeOut;
var pingSchedule;
var inPingSchedule;
function initPingSchedule() {
  pingSchedule = setTimeout(function pingScheduleFn() {
    if (wsCtrl.ping <= 8000) {
      calcPing();
      inPingSchedule = setTimeout(pingScheduleFn, 100);
    }
  }, 1500);
}

var ctrl = {};
var testInit = false
var test1;
var test2;
var testDelay = 200;
ctrl.listen = function(d){
  if(d.t === "n"){
  clearTimeout(pingSchedule);
  clearTimeout(inPingSchedule);
  initPingSchedule();
  initReconnect();

    var now = Date.now();

    wsCtrl.ping =  Math.ceil(now - prevTime);

    if(wsCtrl.ping <= 1000){
      setTimeout(function(){
        prevTime = Date.now();
        send(pingData());
      }, 1000 - (wsCtrl.ping))
    } else {
      prevTime = Date.now();
      send(pingData());
    }
    wsCtrl.data.makeFriend.n = d.d.mf;
    var preNotify = data.notify.n;
    data.notify.n = d.d.n;
    if (preNotify !== data.notify.n) rd.nav(function(){m.redraw()})
    wsCtrl.total = d.d.d;

  }
  else if(d.t === "nbRequester"){
    wsCtrl.data.makeFriend.n = d.d;
  }

  else if(d.t === "initPost"){
    wsCtrl.data.post.list = d.d;
    wsCtrl.data.post.init = true;
    if(wsCtrl.data.post.list.length > 0) wsCtrl.data.post.timepoint = wsCtrl.data.post.list[wsCtrl.data.post.list.length - 1].published;
    rd.home(function(){m.redraw()})
  }
  else if(d.t === "newPost"){
    console.log("new post")
    if(d.d.id !== undefined) {
      wsCtrl.data.post.list.unshift(d.d);
      wsCtrl.data.post.timepoint = d.d.published;
    }
    rd.home(function(){m.redraw()})
  }

  else if(d.t === "mes"){
    //--------------test
    if(d.d.m === "stop"){
      if(wsCtrl.ws){
        wsCtrl.ws.onerror = $.noop;
        wsCtrl.ws.onclose = $.noop;
        wsCtrl.ws.onopen = $.noop;
        wsCtrl.ws.onmessage = $.noop;
        wsCtrl.ws.close();
      }
      initReconnect(0)
      //initWs();
    }
    //--------------test
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

  else if(d.t === "following_onlines"){
    data.userOnline = d.d;
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



  else if(d.t === "init_chat"){
    var listMes = d.d.reverse();
    if(listMes.length > 0){
      var user = (userId == d.d[0].f.id)?d.d[0].t:d.d[0].f;
      var pos = getPosChat(user);
      //console.log("init posssssssss:" + pos);
      if(data.chat[pos].chat.length <= 1) {
        //d.d.map(function (mes) {
        //  //console.log("init_chat: " + mes.mv);
        //  //console.log("push:" + mes.mes);
        //  data.chat[pos].chat.push(mes)
        //});
      data.chat[pos].chat = d.d.concat(data.chat[pos].chat)
      } else {
        //d.d.map(function(mes){
        //  if(mes.mv < data.chat[pos].chat[0].mv) data.chat[pos].chat.push(mes)
        //})
        data.chat[pos].chat = d.d
      }
      //data.chat[pos].chat.sort(sortByVer);
      rd.right(function(){m.redraw()})
    }
  }

  else if(d.t === "smm"){
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

  else if(d.t === "init_notify") {
    data.notify.notifyMessage = d.d;
    data.notify.init = true;
    rd.nav(function(){m.redraw();});
  }

  else if(d.t === "init_friend_request"){
    data.makeFriend.listRequests = d.d;
    data.makeFriend.init = true;
    rd.nav(function(){m.redraw();});
  }

  else if(d.t === "chatNotify") {

        var roomId = d.d.room;

        if(d.d.t === "chat") {
          var mes = d.d.d;
          wsCtrl.commentsInRoom(roomId).push(
              {
                avatar: (mes.user.avatar.length>0)?("/getimage/thumb/" + mes.user.avatar):wsCtrl.defaultAvata,
                userId: mes.user.id,
                user: mes.user.name,
                time: Date.now(),
                comment: mes.chat
              }
          );
        } else if(d.d.t == "userEnter"){

          var user = d.d.u;
            user.role = "user";

          if(arrayObjectIndexOf(wsCtrl.userInRoom(roomId), user.id, "id") < 0){
            wsCtrl.userInRoom(roomId).push(user)
          }
        } else if(d.d.t == "userLeaves"){
          var user = d.d.u;
          //console.log(user)
          //console.log(arrayObjectIndexOf(wsCtrl.userInRoom(roomId), user, "name"))
          wsCtrl.userInRoom(roomId).splice(arrayObjectIndexOf(wsCtrl.userInRoom(roomId), user.id, "id"), 1);
        } else if(d.d.t === "initChat") {
          var users = d.d.lu;
          wsCtrl.getRooms(d.d.room).c = d.d.c;
          wsCtrl.getRooms(d.d.room).u = d.d.u;
          var listChats = d.d.lc;


            users.map(function(user){

              if(arrayObjectIndexOf(wsCtrl.userInRoom(roomId), user.id, "id") < 0){
              user.role = "user";
              wsCtrl.userInRoom(roomId).push(user)
            }
          });

          listChats.map(function(chat){
            wsCtrl.commentsInRoom(roomId).unshift(
                {
                  avatar: chat.user.avatar.length>0?('/getimage/thumb/' + chat.user.avatar):wsCtrl.defaultAvata,
                  userId: chat.user.id,
                  user: chat.user.name,
                  time: chat.time,
                  comment: chat.chat
                }
            );
          });
          wsCtrl.getRoom(roomId).initOk = true;
        } else if(d.d.t === "prevChat") {
            var listChats = d.d.lc.reverse();
            var roomId = d.d.room;
            if(listChats.length < 1) wsCtrl.getRoom(roomId).loadMore = false;
            var listComments = listChats.map(function(chat){

            return {
              avatar: chat.user.avatar.length>0?('/getimage/thumb/' + chat.user.avatar):wsCtrl.defaultAvata,
              userId: chat.user.userId,
              user: chat.user.name,
              time: chat.time,
              comment: chat.chat
            }
          });
          wsCtrl.data.chatroom[roomId].comments = listComments.concat(wsCtrl.data.chatroom[roomId].comments)
          //wsCtrl.data.chatroom[roomId].gettingPrev = false;
        } else if(d.d.t === "initChatRooms") {
          var listInfos = d.d.v;
          listInfos.map(function(room){
            wsCtrl.getRooms(room.id).c = room.c;
            wsCtrl.getRooms(room.id).u = room.u;
          });
          rd.chatroom(function(){m.redraw()})
        } else if(d.d.t === "io") {
          var value = d.d.v;
          if(value.c != undefined) wsCtrl.getRooms(value.rid).c += parseInt(value.c);
          if(value.u != undefined) wsCtrl.getRooms(value.rid).u += parseInt(value.u);
          rd.chatroom(function(){rd.room(function(){m.redraw()})});

        }
        rd.room(function(){m.redraw()})
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
    //if(data.chat[pos].display != true){
    //  data.chat[pos].display = true;
    //  data.chat[pos].hide = false;
    //}
    if(userId !== d.d.f.id) {
      data.chat[pos].read = false;
    } else data.chat[pos].read = true;
    rd.right(function(){m.redraw()})
};

wsCtrl.getRoom = function(id){
  if(wsCtrl.data.chatroom[id] == undefined) {
    wsCtrl.data.chatroom[id] = {};
    wsCtrl.data.chatroom[id].initOk = false;
    wsCtrl.data.chatroom[id].loadMore = true;
    wsCtrl.data.chatroom[id].gettingPrev = false;
  }
  return wsCtrl.data.chatroom[id]
};
var getRoom = wsCtrl.getRoom;

wsCtrl.userInRoom = function(id){
  if(wsCtrl.getRoom(id).users == undefined) wsCtrl.getRoom(id).users = [];
  return wsCtrl.getRoom(id).users
};
var userInRoom = wsCtrl.userInRoom;

wsCtrl.loadMoreComments = function(id){
    if(wsCtrl.getRoom(id).loadMore == undefined) wsCtrl.getRoom(id).loadMore = true;
    return wsCtrl.getRoom(id).loadMore;
};

wsCtrl.commentsInRoom = function(id){
  if(wsCtrl.getRoom(id).comments == undefined) wsCtrl.getRoom(id).comments = []
  return wsCtrl.getRoom(id).comments
};
var commentsInRoom = wsCtrl.commentsInRoom;

wsCtrl.inputChat = function(id){
  if(wsCtrl.getRoom(id).input == undefined) wsCtrl.getRoom(id).input = m.prop('');
  return wsCtrl.getRoom(id).input
};

wsCtrl.clearOldRoom = function(id){
  wsCtrl.data.chatroom[id] = undefined
};


wsCtrl.getRooms = function(id){
  if(wsCtrl.data.chatrooms[id] == undefined) {
    wsCtrl.data.chatrooms[id] = {};
    wsCtrl.data.chatrooms[id].c = 0;
    wsCtrl.data.chatrooms[id].u = 0;
  }
  return wsCtrl.data.chatrooms[id]
};

$(document).on('click', '.route', function(e){
  console.log("route")
  e.preventDefault();
  m.route($(this).attr('href'));
  $('.mini').remove();
});

$('body').on('click', '.relation_actions a.relation', function() {
  var $a = $(this).addClass('processing');
  if (!$(this).hasClass("unfriend") || confirm('Are you sure you want to delete this?')) {
    $.ajax({
      url: $a.attr('href'),
      type: 'post',
      success: function (html) {
        $a.parent().html(html);
      }
    });
  }
  return false;
});



module.exports = wsCtrl;
},{}]},{},[5])