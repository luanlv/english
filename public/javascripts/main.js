$(document).on('click', '.route-button', function(){
  local(['nav', 'home'], function(){m.redraw()}).call()
});