"use strict";var _DENSITY=20,_FPS=30,_OPACITY=.5;!function(t){t.fn.effect=function(){var i=this;return t(i).children().each(function(){var i=this,o=Math.round(1/t(i).offset().top*(t(window).height()/2+t(window).scrollTop())*100)/100;t(i).find(".effect").css({opacity:o>=1?1:0})}),this},t.fn.engine=function(){for(var i=this,o=_DENSITY,n=_FPS,e=Math.round(window.devicePixelRatio?window.devicePixelRatio:1),h=[],a=t(i).find(".animation").get(0),d=a.getContext("2d"),r=function(){a.width=t(i).width()*e,a.height=t(i).height()*e,d.clearRect(0,0,a.width,a.height),t(h).each(function(){var t=this,i=_OPACITY-_OPACITY/(a.width/2)*Math.abs(a.width/2-t.position.x),o=_OPACITY-_OPACITY/(a.height/2)*Math.abs(a.height/2-t.position.y);d.beginPath(),d.arc(t.position.x,t.position.y,t.speed.x*t.speed.y%(50*e)+10*e,0*Math.PI,2*Math.PI),d.globalAlpha=o>=i?i:o,d.fillStyle="rgba("+t.color.r+", "+t.color.g+", "+t.color.b+", 1)",d.fill(),d.closePath(),t.position.x=((t.speed.x%2?t.position.x+t.speed.x/n:t.position.x-t.speed.x/n)+a.width)%a.width,t.position.y=((t.speed.y%2?t.position.y+t.speed.y/n:t.position.y-t.speed.y/n)+a.height)%a.height})};o--;)h.push({position:{x:Math.round(1e6*Math.random())%(t(i).width()*e),y:Math.round(1e6*Math.random())%(t(i).height()*e)},speed:{x:Math.round(1e6*Math.random())%(50*e),y:Math.round(1e6*Math.random())%(50*e)},color:{r:Math.round(1e6*Math.random())%64+192,g:Math.round(1e6*Math.random())%64+192,b:Math.round(1e6*Math.random())%64+192}});return r(),setInterval(function(){r()},1e3/n),t(window).bind("load resize scroll",function(){var o=Math.round(100*(1-2/t(i).height()*t(window).scrollTop()))/100;Math.round(t(i).height()/2-t(i).find(".content").height()/2-t(window).scrollTop()/4),t(i).find(".animation").css({opacity:o>=0?o:0})}),this}}(jQuery);