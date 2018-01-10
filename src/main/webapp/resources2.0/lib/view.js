/**

 @Name：layuiAdmin 视图模块
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：GPL-2
    
 */
 
layui.define(['laytpl', 'layer'], function(exports){
  var $ = layui.jquery
  ,laytpl = layui.laytpl
  ,layer = layui.layer
  ,setter = layui.setter
  ,device = layui.device()
  ,hint = layui.hint()
  
  //对外接口
  ,view = function(id){
    return new Class(id);
  }
  
  ,LAY_BODY = 'LAY_app_body'
  
  //构造器
  ,Class = function(id){
    this.id = id;
    this.container = $('#'+(id || LAY_BODY));
  };
  
  //加载中
  view.loading = function(elem){
    elem.append(
      this.elemLoad = $('<i class="layui-anim layui-anim-rotate layui-anim-loop layui-icon layui-icon-loading layadmin-loading"></i>')
    );
  };
    
    //移除加载
  view.removeLoad = function(){
    this.elemLoad && this.elemLoad.remove();
  };
  
  //Ajax请求
  view.req = function(options){
    var that = this
    ,success = options.success
    ,error = options.error
    ,request = setter.request
    ,response = setter.response;
    
    options.data = options.data || {};
    
    //自动传入默认 token
    options.data[request.tokenName] = request.tokenName in options.data 
      ?  options.data[request.tokenName]
    : (layui.data(setter.tableName)[request.tokenName] || '');
    
    delete options.success;
    delete options.error;

    return $.ajax($.extend({
      type: 'get'
      ,dataType: 'json'
      ,success: function(res){
        if(res[response.statusName] == response.statusCode) {
          typeof options.done === 'function' && options.done(res); //只有 response 的 code 正常才执行 done
        } else {
          var error = '<cite>Error：</cite> ' + (res[response.msgName] || '返回状态码异常') + ' <br><cite>URL：</cite>' + options.url;
          view.error(error);
        }
        typeof success === 'function' && success(res); //只要 http 状态码正常，无论 response 的 code 是否正常都执行 success
      }
      ,error: function(e, code){
        var error = '请求异常，请重试<br><cite>错误信息：</cite>'+ code +'<br><cite>URL：</cite>' + options.url;
        view.error(error);
        typeof error === 'function' && error(res);
      }
    }, options));
  };
  
  //弹窗
  view.popup = function(options){
    var success = options.success
    ,skin = options.skin;
    
    delete options.success;
    delete options.skin;
    
    layer.open($.extend({
      type: 1
      ,title: '提示'
      ,content: ''
      ,id: 'LAY-system-view-popup'
      ,skin: 'layui-layer-admin' + (skin ? ' ' + skin : '')
      ,shadeClose: true
      ,closeBtn: false
      ,success: function(layero, index){
        var elemClose = $('<i class="layui-icon" close>&#x1006;</i>');
        layero.append(elemClose);
        elemClose.on('click', function(){
          layer.close(index);
        });
        typeof success === 'function' && success.apply(this, arguments);
      }
    }, options))
  };
  
  //异常提示
  view.error = function(content, options){
    return view.popup($.extend({
      content: content
      ,maxWidth: 300
      //,shade: 0.01
      ,offset: 't'
      ,anim: 6
      ,id: 'LAY_adminError'
    }, options))
  };
  
  
  //请求模板文件渲染
  Class.prototype.render = function(views){
    var that = this, router = layui.router();
    views = setter.views + views + setter.engine;
    
    $('#'+ LAY_BODY).children('.layadmin-loading').remove();
    view.loading(that.container); //loading
    
    //请求模板
    $.ajax({
      url: views
      ,type: 'get'
      ,dataType: 'html'
      ,data: {
        v: layui.cache.version
      }
      ,success: function(html){
        html = '<div>' + html + '</div>';
        
        var elemTitle = $(html).find('title')
        ,title = elemTitle.text();
        
        var res = {
          title: title
          ,body: html
        };
        
        elemTitle.remove();
        
        if(that.then){
          that.then(res);
          delete that.then; 
        }

        that.parse(html);
        view.removeLoad();
        
        if(that.done){
          that.done(res);
          delete that.done; 
        }
        
      }
      ,error: function(e){
        view.removeLoad();
        
        if(that.render.isError){
          return view.error('请求视图文件异常，状态：'+ e.status);
        };
        
        if(e.status === 404){
          that.render('template/tips/404');
        } else {
          that.render('template/tips/error');
        }
        
        that.render.isError = true;
      }
    });
    return that;
  };
  
  //解析模板
  Class.prototype.parse = function(html, refresh, callback){
    var that = this
    ,isScriptTpl = typeof html === 'object' //是否模板元素
    ,elem = isScriptTpl ? html : $(html)
    ,elemTemp = isScriptTpl ? html : elem.find('*[template]')
    ,fn = function(options){
      var tpl = laytpl(options.dataElem.html());
      options.dataElem.after(tpl.render(options.res || {}));

      typeof callback === 'function' && callback();
      
      try {
        options.done && new Function('d', options.done)(options.res);
      } catch(e){
        console.error(options.dataElem[0], '\n存在错误回调脚本\n\n', e)
      }
    };
    
    elem.find('title').remove();
    that.container[refresh ? 'after' : 'html'](elem.children());
    
    //遍历模板区块
    for(var i = elemTemp.length; i > 0; i--){
      (function(){
        var dataElem = elemTemp.eq(i - 1)
        ,layDone = dataElem.attr('lay-done') || dataElem.attr('lay-then') //获取回调
        ,url = laytpl(dataElem.attr('lay-url')|| '').render(layui.router()) //接口 url
        ,data = laytpl(dataElem.attr('lay-data')|| '').render(layui.router()) //接口参数
        ,headers = laytpl(dataElem.attr('lay-headers')|| '').render(layui.router()); //接口请求的头信息
        
        try {
          data = new Function('return '+ data + ';')();
        } catch(e) {
          hint.error('lay-data: ' + e.message);
          data = {};
        };
        
        try {
          headers = new Function('return '+ headers + ';')();
        } catch(e) {
          hint.error('lay-headers: ' + e.message);
          headers = headers || {}
        };
        
        if(url){
          view.req({
            type: dataElem.data('lay-type') || 'get'
            ,url: url
            ,data: data
            ,dataType: 'json'
            ,headers: headers
            ,success: function(res){
              fn({
                dataElem: dataElem
                ,res: res
                ,done: layDone
              });
            }
          });
        } else {
          fn({
            dataElem: dataElem
            ,done: layDone
          });
        }
      }());
    }
    
    return that;
  };
  
  //直接渲染字符
  Class.prototype.send = function(views, data){
    var tpl = laytpl(views || this.container.html()).render(data || {});
    this.container.addClass(SHOW).html(tpl);
    return this;
  };
  
  //局部刷新模板
  Class.prototype.refresh = function(callback){
    var that = this
    ,next = that.container.next()
    ,templateid = next.attr('lay-templateid');
    
    if(that.id != templateid) return that;
    
    that.parse(that.container, 'refresh', function(){
      that.container.siblings('[lay-templateid="'+ that.id +'"]:last').remove();
      typeof callback === 'function' && callback();
    });
    
    return that;
  };
  
  //视图请求成功后的回调
  Class.prototype.then = function(callback){
    this.then = callback;
    return this;
  };
  
  //视图渲染完毕后的回调
  Class.prototype.done = function(callback){
    this.done = callback;
    return this;
  };
  
  //对外接口
  exports('view', view);
});