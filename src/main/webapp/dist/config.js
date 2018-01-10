/**

 @Name：全局配置
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：GPL-2
    
 */
 
layui.define(['laytpl', 'layer', 'element', 'util'], function(exports){
  exports('setter', {
    container: 'LAY_app' //容器ID
    ,base: layui.cache.base //记录layuiAdmin文件夹所在路径
    ,views: layui.cache.base + 'views/' //视图所在目录
    ,entry: 'index' //入口文件名
    ,engine: '.html' //模板文件后缀名
    ,pageTabs: true //是否开启页面选项卡功能
    
    ,name: 'layuiAdmin Pro'
    ,tableName: 'layuiAdmin' //本地存储表名
    ,MOD_NAME: 'admin' //模块事件名
    
    //自定义请求字段
    ,request: {
      tokenName: 'access_token' //记录 token 的字段名
    }
    
    //自定义响应字段
    ,response: {
      statusName: 'code' //数据状态的字段名称
      ,statusCode: 0 //成功的状态码
      ,msgName: 'msg' //状态信息的字段名称
      ,dataName: 'data' //数据详情的字段名称
    }
    
    //扩展的第三方模块
    ,extend: [
      'echarts', //echarts 核心包
      'echartsTheme' //echarts 主题
    ]
    
    //主题配置
    ,theme: {
      //配色方案，如果用户未设置主题，第一个将作为默认
      color: [{
        main: '#20222A' //主题色
        ,selected: '#009688' //选中色
        ,alias: 'default' //默认别名
      },{
        main: '#03152A'
        ,selected: '#3B91FF'
        ,alias: 'dark-blue' //藏蓝
      },{
        main: '#2E241B'
        ,selected: '#A48566'
        ,alias: 'coffee' //咖啡
      },{
        main: '#50314F'
        ,selected: '#7A4D7B'
        ,alias: 'purple-red' //紫红
      },{
        main: '#344058'
        ,logo: '#1E9FFF'
        ,selected: '#1E9FFF'
        ,alias: 'ocean' //海洋
      },{
        main: '#3A3D49'
        ,logo: '#2F9688'
        ,selected: '#5FB878'
        ,alias: 'green' //墨绿
      },{
        main: '#20222A'
        ,logo: '#F78400'
        ,selected: '#F78400'
        ,alias: 'red' //橙色
      },{
        main: '#28333E'
        ,logo: '#AA3130'
        ,selected: '#AA3130'
        ,alias: 'fashion-red' //时尚红
      },{
        main: '#24262F'
        ,logo: '#3A3D49'
        ,selected: '#009688'
        ,alias: 'classic-black' //经典黑
      }]
    }
  });
});
