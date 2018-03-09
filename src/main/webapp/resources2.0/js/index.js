var tab;
layui.config({
	base: '/resources2.0/js/',
	version:new Date().getTime()
}).use(['element', 'layer', 'navbar', 'tab'], function() {
	var element = layui.element,
		$ = layui.jquery,
		layer = layui.layer,
		navbar = layui.navbar();
		tab = layui.tab({
			elem: '.admin-nav-card' //设置选项卡容器
			,contextMenu:true
		});
		
	//iframe自适应
	$(window).on('resize', function() {
		var $content = $('.admin-nav-card .layui-tab-content');
		$content.height($(this).height() - 147);
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	}).resize();

	//设置navbar
	navbar.set({
		spreadOne: true,
		elem: '#admin-navbar-side',
		cached: false,
		url: '/sysNavbar/getNavbar'
	});
	//渲染navbar
	navbar.render();
	//监听点击事件
	navbar.on('click(side)', function(data) {
		tab.tabAdd(data.field);
	});
    //锁屏
    function lockPage(){
        layer.open({
            title : false,
            type : 1,
            content : '	<div class="admin-header-lock" id="lock-box">'+
            '<div class="admin-header-lock-img"><img src="/resources2.0/test/0.jpg"/></div>'+
            '<div class="admin-header-lock-name" id="lockUserName">悦龄会后台</div>'+
            '<div class="input_btn">'+
            '<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />'+
            '<button class="layui-btn" id="unlock">解锁</button>'+
            '</div>'+
            '<p>请输入登录密码，否则不会解锁成功哦！！！</p>'+
            '</div>',
            closeBtn : 0,
            shade : 0.9
        })
        $(".admin-header-lock-input").focus();
    }
    $(".lockcms").on("click",function(){
        window.sessionStorage.setItem("lockcms",true);
        lockPage();
    })
    // 判断是否显示锁屏
    if(window.sessionStorage.getItem("lockcms") == "true"){
        lockPage();
    }
    // 解锁
    $("body").on("click","#unlock",function(){
        if($(this).siblings(".admin-header-lock-input").val() == ''){
            layer.msg("请输入解锁密码！");
            $(this).siblings(".admin-header-lock-input").focus();
        }else{
            var pwd = $(this).siblings(".admin-header-lock-input").val();
            $.post("/sysuser/checkPwd",{pwd:pwd},function (result) {
                if (result.code == 0) {
                    window.sessionStorage.setItem("lockcms",false);
                    layer.closeAll("page");
                    $(this).siblings(".admin-header-lock-input").val('');
                    return;
                }
                layer.msg(result.msg);
                $(this).siblings(".admin-header-lock-input").val('').focus();
            });
        }
    });
    $(document).on('keydown', function() {
        if(event.keyCode == 13) {
            $("#unlock").click();
        }
    });
});
