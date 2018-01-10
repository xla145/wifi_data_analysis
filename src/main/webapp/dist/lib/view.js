/** layuiAdmin-v1.0.0-beta4 GPL-v2 License By http://www.layui.com/admin/ */
 ;layui.define(["laytpl","layer"],function(e){var t=layui.jquery,n=layui.laytpl,r=layui.layer,a=layui.setter,i=(layui.device(),layui.hint()),o=function(e){return new d(e)},s="LAY_app_body",d=function(e){this.id=e,this.container=t("#"+(e||s))};o.loading=function(e){e.append(this.elemLoad=t('<i class="layui-anim layui-anim-rotate layui-anim-loop layui-icon layui-icon-loading layadmin-loading"></i>'))},o.removeLoad=function(){this.elemLoad&&this.elemLoad.remove()},o.req=function(e){var n=e.success,r=(e.error,a.request),i=a.response;return e.data=e.data||{},e.data[r.tokenName]=r.tokenName in e.data?e.data[r.tokenName]:layui.data(a.tableName)[r.tokenName]||"",delete e.success,delete e.error,t.ajax(t.extend({type:"get",dataType:"json",success:function(t){if(t[i.statusName]==i.statusCode)"function"==typeof e.done&&e.done(t);else{var r="<cite>Error：</cite> "+(t[i.msgName]||"返回状态码异常")+" <br><cite>URL：</cite>"+e.url;o.error(r)}"function"==typeof n&&n(t)},error:function(t,n){var r="请求异常，请重试<br><cite>错误信息：</cite>"+n+"<br><cite>URL：</cite>"+e.url;o.error(r),"function"==typeof r&&r(res)}},e))},o.popup=function(e){var n=e.success,a=e.skin;delete e.success,delete e.skin,r.open(t.extend({type:1,title:"提示",content:"",id:"LAY-system-view-popup",skin:"layui-layer-admin"+(a?" "+a:""),shadeClose:!0,closeBtn:!1,success:function(e,a){var i=t('<i class="layui-icon" close>&#x1006;</i>');e.append(i),i.on("click",function(){r.close(a)}),"function"==typeof n&&n.apply(this,arguments)}},e))},o.error=function(e,n){return o.popup(t.extend({content:e,maxWidth:300,offset:"t",anim:6,id:"LAY_adminError"},n))},d.prototype.render=function(e){var n=this;layui.router();return e=a.views+e+a.engine,t("#"+s).children(".layadmin-loading").remove(),o.loading(n.container),t.ajax({url:e,type:"get",dataType:"html",data:{v:layui.cache.version},success:function(e){e="<div>"+e+"</div>";var r=t(e).find("title"),a=r.text(),i={title:a,body:e};r.remove(),n.then&&(n.then(i),delete n.then),n.parse(e),o.removeLoad(),n.done&&(n.done(i),delete n.done)},error:function(e){return o.removeLoad(),n.render.isError?o.error("请求视图文件异常，状态："+e.status):(404===e.status?n.render("template/tips/404"):n.render("template/tips/error"),void(n.render.isError=!0))}}),n},d.prototype.parse=function(e,r,a){var s=this,d="object"==typeof e,l=d?e:t(e),u=d?e:l.find("*[template]"),c=function(e){var t=n(e.dataElem.html());e.dataElem.after(t.render(e.res||{})),"function"==typeof a&&a();try{e.done&&new Function("d",e.done)(e.res)}catch(r){console.error(e.dataElem[0],"\n存在错误回调脚本\n\n",r)}};l.find("title").remove(),s.container[r?"after":"html"](l.children());for(var y=u.length;y>0;y--)!function(){var e=u.eq(y-1),t=e.attr("lay-done")||e.attr("lay-then"),r=n(e.attr("lay-url")||"").render(layui.router()),a=n(e.attr("lay-data")||"").render(layui.router()),s=n(e.attr("lay-headers")||"").render(layui.router());try{a=new Function("return "+a+";")()}catch(d){i.error("lay-data: "+d.message),a={}}try{s=new Function("return "+s+";")()}catch(d){i.error("lay-headers: "+d.message),s=s||{}}r?o.req({type:e.data("lay-type")||"get",url:r,data:a,dataType:"json",headers:s,success:function(n){c({dataElem:e,res:n,done:t})}}):c({dataElem:e,done:t})}();return s},d.prototype.send=function(e,t){var r=n(e||this.container.html()).render(t||{});return this.container.addClass(SHOW).html(r),this},d.prototype.refresh=function(e){var t=this,n=t.container.next(),r=n.attr("lay-templateid");return t.id!=r?t:(t.parse(t.container,"refresh",function(){t.container.siblings('[lay-templateid="'+t.id+'"]:last').remove(),"function"==typeof e&&e()}),t)},d.prototype.then=function(e){return this.then=e,this},d.prototype.done=function(e){return this.done=e,this},e("view",o)});