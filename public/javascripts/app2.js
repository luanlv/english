(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = api || {};

api.data = "hello"

api.rd = function(name){
  console.log("redraw " + name)
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
},{"../ws/_wsCtrl.js":7}],2:[function(require,module,exports){
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
},{"../ws/_wsCtrl.js":7,"./api.msx":1}],3:[function(require,module,exports){
window.Nav = require('./nav.msx');
window.Home = require('./home.msx');
window.Dashboard = require('./dashboard.msx');

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
    target = id;
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



window.Right = require('./right.msx');

window.Loading = {
  controller: function(){

  },
  view: function(){
    console.log("render loading!!");
    return m('', 'LOADING')
  }
};

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
  m.mount(document.getElementById('count'), Count);
  m.mount(document.getElementById('rightContainer'), tenant('right', window.Right));
}
},{"./dashboard.msx":2,"./home.msx":4,"./nav.msx":5,"./right.msx":6}],4:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');
var div = [{id: "div 1", v: 1},{id: "div 2", v: 5} ,{id:"div 3", v: 10}];

var Home = {
  controller: function() {
    var ctrl = this;
    ctrl.divs = m.prop([]);
    ctrl.divs(div);
    rd.home(function(){m.redraw()});
  },
  view: function(ctrl) {
    api.rd("home:" + redraw.home);
    redraw.home++;
    return (
        {tag: "div", attrs: {}, children: [
          ctrl.divs().map(function(item){
              return {tag: "div", attrs: {className:"config", 
                          id:item.id, 
                          v:item.v}, children: [item.id]}
              })
        ]}
    )
  }
};

module.exports = Home;
},{"../ws/_wsCtrl.js":7,"./api.msx":1}],5:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');


var Nav = {
  controller: function(){
    var ctrl = this;
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
    return [
      m("a", {href: "/", config: m.route}, " Home |"),
      m("a", {href: "/dashboard",  config: m.route}, " Dashboard |"),
      (wsCtrl.userId.length>0)?(" ---Hello:" + wsCtrl.userId) + "--- |":"",
      (wsCtrl.userId.length>0)?m("a", {href: "/logout"}, "Logout"):"",
      (!wsCtrl.userId.length>0)?m("a", {href: "/login"}, "Sign in |"):"",
      (!wsCtrl.userId.length>0)?m("a", {href: "/signup"}, " Sign up"):"",
      (wsCtrl.userId.length>0)?m('.notify', [
        m('.numNotify',{
          onclick: function(){
            ctrl.displayNofity();
            rd.nav();
          }
        }, wsCtrl.data.notify.n),

        m('.notifyWr', [
          !wsCtrl.data.notify.display?"":m('.inNotify', !wsCtrl.data.notify.init?"LOADING":[
            wsCtrl.data.notify.notifyMessage.map(function(mes){
              return m('.notifyMes', {
                config: function(el){
                  $(el).click(function(){
                    local(['nav', 'right'], function(){
                      console.log("click")
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
              }, [
                m('.notifyName', mes.user.name),
                m('.mesNumber', " (" + mes.n+ ") "),
                m('.lastMes', mes.lm.mes)
              ])
            })
          ])
        ])
      ]):"",
    ]
  }
};

module.exports = Nav;
},{"../ws/_wsCtrl.js":7,"./api.msx":1}],6:[function(require,module,exports){
var wsCtrl = require('../ws/_wsCtrl.js');
var api = require('./api.msx');

var Right = {
  controller: function(){
    var ctrl = this;
    m.redraw.strategy("diff");

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
      console.log(wsCtrl.data.chat[num].input())
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
    api.rd("right: " + redraw.right);
    //console.log("right");
    redraw.right++;
    return (
        {tag: "div", attrs: {}, children: [
          {tag: "div", attrs: {id:"user-list"}, children: [
            {tag: "div", attrs: {className:"title-user-list"}, children: ["USER ONLINE"]}, 
            wsCtrl.data.userOnline.map(function(user){
                return {tag: "div", attrs: {className:"userOnline", 
                            config:function(el){
                                $(el).click(function(){
                                  if(wsCtrl.userId.length > 0 && wsCtrl.userId !== user.id) ctrl.makechat(user)
                                });
                              }
                            
                        }, children: [user.name + ((wsCtrl.userId.length > 0 && wsCtrl.userId !== user.id)?"":" (you)")]}
                }), 
            {tag: "div", attrs: {className:"title-user-list"}, children: ["USER ONLINE"]}
          ]}, 

          {tag: "div", attrs: {id:"dock-bot"}, children: [
            wsCtrl.data.chat.map(function(chat, rank){
                return (!chat.display)?"":(
                    {tag: "div", attrs: {className:"chatWr " + (chat.hide?"w2":"w1")}, children: [
                      {tag: "div", attrs: {className:"chat-title2" + (chat.read?"":" unread"), 
                           style:!chat.hide?"display: none":"", 
                           onclick:function(){rd.right(ctrl.toggleChat(rank))}
                      }, children: [chat.user.name]}, 

                      {tag: "div", attrs: {className:"chatboxWr", 
                           style:chat.hide?"display: none":""}, children: [
                          {tag: "div", attrs: {className:"chat-title" + (chat.read?"":" unread"), 
                               onclick:function(){rd.right(ctrl.toggleChat(rank))}
                          }, children: [
                            chat.user.name, 
                            {tag: "span", attrs: {className:"close-chat", 
                                  onclick:function(){rd.right(ctrl.stopChat(rank))}
                            }, children: ["X"]}
                          ]}, 

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
                                        $(element).textareaAutoSize();
                                        $(element).attrchange({
                                          //trackValues: true,
                                          callback: function (event) {
                                            var prev = element.previousSibling;
                                            var $prev = $(prev);
                                            if ($prev.scrollTop() + $prev.innerHeight() >= element.previousSibling.scrollHeight) {
                                              $prev.css('height', 271 - $(element).outerHeight());
                                              prev.scrollTop = prev.scrollHeight;
                                            } else {
                                              $prev.css('height', 271 - $(element).outerHeight())
                                            }
                                          }
                                        });
                                      }
                                      element.value = wsCtrl.data.chat[rank].input();
                                    }, 
                                  
                                  onkeypress:function(e){
                                        if(e.keyCode == 13 && !e.shiftKey) {
                                          if(wsCtrl.data.chat[rank].input() != undefined) console.log(wsCtrl.data.chat[rank].input().length + "=====")
                                          if (wsCtrl.data.chat[rank].input().length < 1) {
                                            console.log("length < 1")
                                            return false;
                                          } else {
                                            var source = e.target || e.srcElement;
                                            var prev = source.previousSibling;
                                            prev.scrollTop = prev.scrollHeight;
                                            ctrl.add(rank);
                                            return false;
                                          }
                                        }else{
                                          m.redraw.strategy("none")
                                          if(e.keyCode == 13 && e.shiftKey && wsCtrl.data.chat[rank].input().length < 1){
                                            return false;
                                          }
                                        }
                                    }, 
                                  
                                  oninput:function(){rd.right(api.setsVal(wsCtrl.data.chat[rank].input))}
                        }}
                      ]}
                    ]}
                    )
                })
          ]}
        ]}
    )
  }
};

module.exports = Right;

},{"../ws/_wsCtrl.js":7,"./api.msx":1}],7:[function(require,module,exports){
var wsCtrl = {}

var sri = Math.random().toString(36).substring(2);

wsCtrl.userId = document.body.getAttribute("id");
var userId = wsCtrl.userId;
wsCtrl.userName = document.body.getAttribute("name");
var userName = wsCtrl.userName
wsCtrl.mVersion = parseInt(document.body.getAttribute("mv"));
var mVersion = wsCtrl.mVersion
wsCtrl.mRVersion = parseInt(document.body.getAttribute("mv"));
var mRVersion = wsCtrl.mRVersion


window.redraw = {
  nav: 0,
  home: 0,
  dashboard: 0,
  right: 0,
  app: 0
}

//var ws = new WebSocket("ws://188.166.254.203:9000/socket?sri=" + sri);
var ws = new WebSocket("ws://" + document.domain + ":9000/socket?sri=" + sri);
//var ws = new WebSocket("ws://localhost:9000/socket?sri=" + sri);
//var ws = new WebSocket("ws://192.168.1.25:9000/socket?sri=" + sri);

var pingData = function() {
  return JSON.stringify({
    t: "p",
    v: mVersion
  });
};

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

setInterval(function(){
  send(pingData());
}, 1000);


ws.onopen = function(){
  console.log('WebSocket ok');
  send(sendData("get_onlines", ""));
};


ws.onclose = function(){
  alert("socket closed")
  console.log("socket closed")
};

ws.onerror = function(){
  alert("socket error")
  console.log("socket error")
}

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


var ctrl = {};
ctrl.listen = function(d){
  if(d.t === "n"){
    if(!data.notify.display) {
      var preNotify = data.notify.n;
      if (data.notify.display == false) data.notify.n = d.d;
      if (preNotify !== data.notify.n) rd.nav(function(){m.redraw()})
    }
  }

  if(d.t === "ul"){
    d.d.map(function(user){
      if(data.userOnline.indexOf(user) < 0) {
        data.userOnline.push(user);
      }
    });
    rd.right(function(){m.redraw()})
  }

  if(d.t === "following_leaves"){
    data.userOnline.splice(data.userOnline.indexOf(d.d), 1);
    rd.right(function(){m.redraw()})
  }

  if(d.t === "following_enters"){
    if(data.userOnline.indexOf(d.d) < 0) {
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
      }, 100);

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
    console.log(target);
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




ws.onmessage = function (e) {
  var m = JSON.parse(e.data);
  ctrl.listen(m)
};

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
},{}]},{},[3])