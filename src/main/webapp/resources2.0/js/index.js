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
});