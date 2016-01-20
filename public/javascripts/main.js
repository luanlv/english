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


var powerTipLoader = '<div class="square-wrap"><div class="square-spin"></div></div>';


var userPowertip = function($els, placement) {
  $els.removeClass('ulpt').powerTip({
    intentPollInterval: 200,
    fadeInTime: 100,
    fadeOutTime: 100,
    placement: placement,
    smartPlacement: true,
    mouseOnToPopup: true,
    closeDelay: 200
  }).on({
    powerTipPreRender: function() {
      $.ajax({
        url: ($(this).attr('href') || $(this).data('href')).replace(/\?.+$/, '') + '/mini',
        success: function(html) {
          $('#powerTip').html(html);
          $('body').trigger('lichess.content_loaded');
        }
      });
    }
  }).data('powertip', powerTipLoader);
};

$( document ).ready(function() {

  function updatePowertips() {
    userPowertip($('.ulptr.ulpt'), 'nw');
    userPowertip($('.ulpt'), 'sw-alt');
  };

  //setTimeout(updatePowertips, 1600);
  setInterval(updatePowertips, 300);
  $('body').on('lichess.content_loaded', updatePowertips);

});


