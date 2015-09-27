var sri = Math.random().toString(36).substring(2);

var userId = document.body.getAttribute("id");

var userName = document.body.getAttribute("name");
var mVersion = document.body.getAttribute("mv");
var mRVersion = document.body.getAttribute("mv");

var ws = new WebSocket("ws://luanlv.info/socket?sri=" + sri);
//var ws = new WebSocket("ws://localhost:9000/socket?sri=" + sri);
//var ws = new WebSocket("ws://192.168.1.25:9000/socket?sri=" + sri);

var pingData = function() {
  return JSON.stringify({
    t: "p",
    v: mVersion
  });
};

var sendData = function(t, d){
  return JSON.stringify({
    t: t,
    d: d
  })
};


var send = function (message, callback) {
  waitForConnection(function () {
    ws.send(message);
    if (typeof callback !== 'undefined') {
      callback();
    }
  }, 1000);
};

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
  ws.send(pingData());
}, 1000);


ws.onopen = function(){
  console.log('WebSocket ok');
  ws.send(sendData("get_onlines", ""));
};



var ctrl = {};
var data = {
  userOnline: [],
  user: {},
  chat: []
};


var getChat = function(uid){
  var exist = false;
  pos = -1;
  for(var len = 0; len < data.chat.length; len++){
    if(data.chat[len].uid == uid) {
      exist = true;
      pos = len;
    }
  }
  return {exist: exist, pos: pos}
};

ctrl.listen = function(d){
  if(d.t === "ul"){
    d.d.map(function(uid){
      if(data.userOnline.indexOf(uid) < 0) {
        data.userOnline.push(uid);
      }
    });
    m.redraw();
  }

  if(d.t === "following_leaves"){
    data.userOnline.splice(data.userOnline.indexOf(d.d), 1);
    m.redraw();
  }

  if(d.t === "following_enters"){
    if(data.userOnline.indexOf(d.d) < 0 && d.d != userId) {
      data.userOnline.push(d.d);
      m.redraw();
    }
  }

  if(d.t === "nu"){
    data.user[d.d.id].name = d.d.n
    m.redraw();
  }

  if(d.t === "mes"){
    if(mVersion == (d.d.v-1)){
      doMes(d);
      mVersion++;
      mRVersion ++;
    } else {
      mVersion = d.d.v;
      var timeOut = setTimeout(function repeart(){
        if(mRVersion == d.d.v -1){
          doMes(d);
          mRVersion++;
          clearTimeout(timeOut)
        } else {
          setTimeout(repeart, 300)
        }
      },300);
      var sendMes = { f: (mRVersion +1), t: (d.d.v -1) };
      send(sendData("gmm", sendMes));
      setTimeout(function getMissMes(){
        console.log("SEEND REQUEST GET MISSING MESS: " + sendMes.toString());
        if(mRVersion == d.d.v){
          clearTimeout(getMissMes)
        } else {
          send(sendData("gmm", sendMes));
          setTimeout(getMissMes, 2000)
        }
      }, 2000)

    }
  }

  if(d.t === "init_chat"){
    var listMes = d.d;
    if(listMes.length > 0){
      var uid = (userId == d.d[0].f)?d.d[0].t:d.d[0].f;
      var chat = getChat(uid);
      if(chat.exist){
        if(data.chat[chat.pos].chat.length < 1) {
          d.d.map(function (mes) {
            data.chat[chat.pos].chat.push(mes)
          });
        } else {
          d.d.map(function(mes){
            if(mes.v < data.chat[chat.pos].chat[0].v) data.chat[chat.pos].chat.push(mes)
          })
        }
      } else {
        d.d.map(function(mes){
          if(mes.v < data.chat[chat.pos].chat[0].v) data.chat[chat.pos].chat.push(mes)
        })
      }
      data.chat[chat.pos].chat.sort(sortByVer);
      m.redraw()
    }
  }

  if(d.t === "smm"){
      d.d.map(function(mes){
      var uid = (userId == mes.f)?mes.t:mes.f;
      var chat = getChat(uid);
      if(chat.exist){
        if(data.chat[chat.pos].chat.length < 1) {
          d.d.map(function (mes) {
            data.chat[chat.pos].chat.push(mes)
          });
        } else {
          d.d.map(function(mes){
            if(mes.v < data.chat[chat.pos].chat[0].v) data.chat[chat.pos].chat.push(mes)
          })
        }
      } else {
        d.d.map(function(mes){
          if(mes.v < data.chat[chat.pos].chat[0].v) data.chat[chat.pos].chat.push(mes)
        })
      }
      if(mRVersion < mes.mv) mRVersion = mes.mv;
     });
      m.redraw()
  }

};

function sortByVer(a,b) {
  if (a.v < b.v)
    return -1;
  if (a.v > b.v)
    return 1;
  return 0;
}


var poshMes = function(data, chat){
  if(!chat.init){
  }
};


ws.onmessage = function (e) {
  var m = JSON.parse(e.data);
  ctrl.listen(m)
};

var doMes = function(d){
    var uid = (userId == d.d.f)? d.d.t: d.d.f;
    var chat = getChat(uid);
    if(!chat.exist && d.d.f != userId){
      data.chat.push(
          {
            uid: d.d.f, display: true, input: m.prop(''), init: false, hide: false, chat: [
            {f: d.d.f, "v": d.d.mv, "mes": d.d.m, "time": d.d.time}
          ]
          }
      );
      send(sendData("init_chat", uid));
    } else {
      data.chat[chat.pos].chat.push(
          {f: d.d.f, "v": d.d.mv, "mes": d.d.m, "time": d.d.time}
      );
      if(data.chat[chat.pos].display != true){
        data.chat[chat.pos].display = true;
        data.chat[chat.pos].hide = false;
      }
    }
    m.redraw();
};

