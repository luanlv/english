$(document).on('click', '.route-button', function(){
  local(['nav', 'home'], function(){m.redraw()}).call()
});


var vis = (function(){
  var stateKey, eventKey, keys = {
    hidden: "visibilitychange",
    webkitHidden: "webkitvisibilitychange",
    mozHidden: "mozvisibilitychange",
    msHidden: "msvisibilitychange"
  };
  for (stateKey in keys) {
    if (stateKey in document) {
      eventKey = keys[stateKey];
      break;
    }
  }
  return function(c) {
    if (c) document.addEventListener(eventKey, c);
    return !document[stateKey];
  }
})();

var downkey = false;
$(document).on('mousedown', function mousedown1(){
  downkey = true
});

$(document).on('mouseup', function mouseup(){
  downkey = false
});