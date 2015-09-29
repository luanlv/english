var redraw = 0;
var rd = function(name){
  //console.log("redraw " + name)
};

var requestWithFeedback = function(args) {
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
      m.redraw()
    }
  }
  return {
    request: m.request(args).then(data).then(function(){complete();m.redraw()}),
    data: data,
    ready: completed
  }
}

var nav = {
  controller: function(){
    setInterval(function(){
      console.log("mVersion:" + mVersion);
      console.log("mRVersion:" + mRVersion);
    }, 1000)
  },
  view: function(){
    rd("nav");
    redraw++;
    return [
      m("a", {href: "/", config: m.route}, " Home |"),
      (userId.length>0)?(" ---Hello:" + userId) + "--- |":"",
      (userId.length>0)?m("a", {href: "/logout"}, " Đăng xuất"):"",
      (!userId.length>0)?m("a", {href: "/login"}, " Đăng nhập |"):"",
      (!userId.length>0)?m("a", {href: "/signup"}, " Đăng ký"):"",
      m("", " " + "redraw: " + redraw)
    ]
  }
};


var Home = {
  controller: function() {
    var ctrl = this;
    ctrl.divs = m.prop([]);
    ctrl.divs([]);

  },
  view: function(ctrl) {
    rd("home");
    return [
        m('div', [
          ctrl.divs().map(function(item){
          return m('div.config', {id: item.id, v: item.v}, item.id)
        })
        ])
    ]
  }
};

var Dashboard = {
  controller: function() {
    console.log("run controller Dashboard");
    var ctrl = this;
    ctrl.server = initData.dashboard || {server: false};
    ctrl.request = (!ctrl.server.server)? requestWithFeedback({method: "GET", url: "/json"}) : {
      ready: function(){return true},
      data: m.prop(initData.dashboard.data)
    };
    ctrl.server.server = false;
  },
  view: function(ctrl) {
    rd("dashBoard");
    if(!ctrl.request.ready()) {
      return m('div', "LOADING !!!")
    } else {
      return [
        ctrl.request.data().map(function (u) {
          return m('div', u.name)
        })
      ]
    }
  }
};



var div = [{id: "home1", v: 1},{id: "home2", v: 5} ,{id:"home3", v: 10}];

var listId = [];
var flag = true;


function scrollBottom(element, isInit, context) {
  if(!isInit){
    element.scrollTop = element.scrollHeight;
  }
  var addLegth = (element.scrollHeight - context.prevHeight) || 0;
  if( element.scrollHeight - element.clientHeight - element.scrollTop < addLegth +  + 10 )
    element.scrollTop = element.scrollHeight;
  context.prevHeight = element.scrollHeight
}

var right = {
  controller: function(){
    var ctrl = this;
    m.redraw.strategy("diff");

    ctrl.makechat = function(uid) {
      var pos = getPosChat(uid);
      data.chat[pos].display = true;
      data.chat[pos].hide = false;

    };

    ctrl.toggleChat = function(num){
      data.chat[num].hide = !data.chat[num].hide;
    };

    ctrl.stopChat = function(num){
      data.chat[num].display = false;
    };

    ctrl.add = function (num) {
      var input = data.chat[num].input().trim();
      input = input.replace(/\n/g, '<br/>');
      if (input) {
        send(sendData("m", {to: data.chat[num].uid, mes: input}));

        //data.chat[num].chat.push({f: "u0", mes: input});
        //data.chat[num].chat.push({f: "u1", mes: "received: " + input});
      }
      data.chat[num].input('');
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
    rd("right");
    //console.log("right");
    return [
      m('#user-list', [
        m('.title-user-list', "USER ONLINE"),
        data.userOnline.map(function(uid){
          return m('div.userOnline', {onclick: function(){if(userId.length > 0 && userId !== uid) ctrl.makechat(uid)}}, getUser(uid).name)
        }),
        m('.title-user-list', "USER ONLINE")
      ]),

      m('#dock-bot', [
      data.chat.map(function(chat, rank){
        return !chat.display?"":m('.chatWr' + (chat.hide?".w2":".w1"), [
          m('.chat-title2', {style: !chat.hide?"display: none":"", onclick: function(){ctrl.toggleChat(rank)}}, getUser(chat.uid).name
          ),
          m('.chatboxWr', {style: chat.hide?"display: none":""},
            [
                    m('.chat-title', {onclick: function(){ctrl.toggleChat(rank)}}, [
                      getUser(chat.uid).name,
                      m('span.close-chat', {onclick: function(){ctrl.stopChat(rank)}}, "X")
                    ]),
                    m('.chat-box', {config: scrollBottom}, [
                      chat.chat.map(function(item, num){
                        return [
                          chat.init?m('.loading_chat', "Loading previous ..."):"",
                          m('div'+ ((item.f == userId)?".comment-left": ".comment-right"),
                              m('.mes', m.trust(item.mes)))
                        ]
                      })
                    ])
                    ,
                    m('textarea.new-comment[placeholder="..."][rows=1]', {
                      style: chat.hide?"display: none":"",
                      config: function (element, isInit, ctx) {
                          element.value = data.chat[rank].input();
                      },
                      onkeypress: function(e){
                        if(e.keyCode == 13 && !e.shiftKey) {
                          if (data.chat[rank].input().length < 1) {
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
                          if(e.keyCode == 13 && e.shiftKey && data.chat[rank].input().length < 1)
                            return false;
                        }
                      },
                      value: data.chat[rank].input(),
                      oninput: setsVal(data.chat[rank].input)
                    })
            ])]
        ) // .right end
        }
      )
    ])
    ]
  }
};

var Loading = {
  controller: function(){

  },
  view: function(){
    console.log("render loading!!");
    return m('', 'LOADING')
  }
};


function route( sub ){
  return {
    controller : function(){
      m.redraw.strategy( 'diff' );

      return new sub.controller();
    },
    view : sub.view
  }
}

function setsVal(callback) {
  return function(e) {
    m.withAttr("value", callback)(e);
    m.redraw.strategy("none")
  }
}


var getUser = function(name){
  if(data.user[name] !== undefined){
    return data.user[name]
  } else {
    data.user[name] = {};
    send(sendData("gn", name));
    setTimeout(function getName(){
      if(data.user[name]  === undefined){
        setTimeout(getName, 2000);
      }
    }, 2000);
    return {name: "...."}
  }
};


m.mount(document.getElementById('nav'), nav);
m.mount(document.getElementById('app'), Loading);
m.mount(document.getElementById('rightContainer'), right);