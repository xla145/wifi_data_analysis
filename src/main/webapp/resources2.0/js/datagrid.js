layui.config({
    base: '/resources2.0/js/'
}).define(['form', 'layer', 'laypage'], function(exports) {
    var $ = layui.jquery,
        form = layui.form(),
        layer = layui.layer;

    var Datagrid = function(options) {
    };

    Datagrid.defaults = {
        url: undefined, 		//数据远程地址【必填】
        type: 'post', 			//数据的获取方式  get or post
        dataType: 'json',		//返回模板格式
        pageEm: undefined,		//分页模板容器 【暂时只支持id】【必填】
        pageGroups: 5,			//连续显示分页数
        pageSize: 10,			//每页数量
        tempEm: undefined, 		//内容容器 用于存放模板的容器	【暂时只支持id】【必填】
        filterForm: undefined,	//页面筛选form表单 （用于分页时筛选条件参数传递）	【暂时只支持id】
        params: undefined, 		//获取数据时传递的额外参数（分页时参数传递，当该项值指定后则filterForm失效）
        openWait: false, 		//加载数据时是否显示等待框
        success: undefined,		//分页获取数据成功回调
        tableFlex: true			//是否设置表格可伸缩    默认true


    };

    Datagrid.openConf = {
        url: undefined,						//表单提交url 【必填】
        content: undefined,					//内容 【必填】
        formEm:$(this.content).find('form'),//表单容器 【暂时只支持id
        type: 1,							//弹框类型 http://www.layui.com/doc/modules/layer.html
        title: '提示',						//标题
        btn: ['保存', '取消'],					//按钮
        area: ['700px', 'auto'],			//宽高
        maxmin: false,						//是否可以最大化
        isFull: true,						//初始是否最大化
        beforeFn: undefined,                //确定按钮执行前fn
        yes: function(index, layero) {		//确认按钮回调
            this.formEm = $(layero).find('form');
            var that = this;
            if(!form.onVerify(this.formEm)){
                return;
            }
            if(this.beforeFn){
                this.beforeFn();
            }
            //表单提交
            $.post(this.url,this.formEm.serialize(),function(result){
                if(result.code == 0){
                    datagrid.refresh(); //刷新表格数据
                    that.formEm[0].reset();	//清空弹框表单内容
                    layer.close(index);	//关闭弹框
                }
                layer.msg(result.msg);
            });
        }
    };

    Datagrid.prototype = {
        init: function(options){
            this.config = $.extend(true, {}, Datagrid.defaults, options);
            var that = this;
            if(that.config.url === undefined) {
                layer.msg('Datagrid Error:请配置远程URL!');
                return;
            }
            if(that.config.pageEm === undefined) {
                layer.msg('Datagrid Error:请配置分页模板容器!');
                return;
            }
            if(that.config.tempEm === undefined) {
                layer.msg('Datagrid Error:请配置内容容器!');
                return;
            }
            //初始化全选复选框
            form.on('checkbox(allChoose)', function(data){
                var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
                child.each(function(index, item){
                    item.checked = data.elem.checked;
                });
                form.render('checkbox');
            });
            //初始化表格数据
            that.run();
        },
        //加载表格数据
        run : function(pageNo){
            var that = this;
            var _data = that.config.params == undefined ? that.config.filterForm.serializeObject() : that.config.params,
                _pageNo = pageNo == undefined ? 1 : pageNo;
            this.refresh(_pageNo, _data);
        },
        //刷新表格数据
        refresh: function(pageNo, data){
            var that = this;
            var _data = data || {},
                _pageNo = pageNo == undefined ? 1 : pageNo;
            _data['pageNo'] = _pageNo;
            _data['pageSize'] = that.config.pageSize;
            var that = this;
            $.ajax({
                url: that.config.url,
                type: that.config.type,
                dataType: that.config.dataType,
                async: true,
                data: _data,
                success: function(result) {
                    var $twrap = that.config.tempEm, $tbody = $twrap.children('table').children('tbody');
                    $twrap.find('tbody').empty();
                    $twrap.find('#page').empty();
                    if(result && result.code == 0){
                        //渲染数据
                        $tbody.append(result.html);
                        //初始化分页
                        layui.laypage({
                            cont: that.config.pageEm
                            ,curr:	result.pageNo			//当前页
                            ,pages: result.pageTotle 		//总页数
                            ,groups: that.config.pageGroups //连续显示分页数
                            ,skip: true //开启跳页
                            ,jump: function(obj, first){
                                var curr = obj.curr;
                                if(!first) {
                                    that.run(curr);
                                }
                            }
                        });
                        if(that.config.tableFlex){
                            that.flexTable($twrap);
                        }
                        form.render('checkbox');
                    }else{
                        layer.msg(result.msg);
                    }
                },
                error:function(){
                    layer.msg('加载失败！');
                }
            })
        },
        //条件搜索表格数据
        search: function(){
            this.run(1);
        },
        //打开编辑、新增框
        open: function(options){
            var that = this;
            that.openConf = $.extend(true, {}, Datagrid.openConf, options);
            if(that.openConf.url === undefined) {
                layer.msg('Datagrid Error:表单提交URL必填!');
                return;
            }
            if(that.openConf.content === undefined) {
                layer.msg('Datagrid Error:内容 必填!');
                return;
            }
            var conf = {
                success: function(){
                    //弹出窗口成功后渲染表单
                    var form = layui.form();
                    form.render();
                },
                end : function(){
                	//console.log("======================")
                   // Datagrid.defaults.formEm.reset();
                    layer.closeAll()
                }
            };
            $.extend(conf, that.openConf);
            if(that.openConf.isFull){
                layer.full(layer.open(conf));
            }else{
                layer.open(conf);
            }

        },
        //获取选择行数据
        getCheckedVal: function(){
            var data = [],that = this;
            that.config.tempEm.find("input[type='checkbox'].data-ckx").each(function() {
                var $cbx = $(this)[0].checked;
                if($cbx) {
                    data.push($(this).attr('data'))
                }
            });
            return data;
        },
        //初始化表格
        flexTable : function($twrap){
            var	$realTable = $twrap.children('table'),
                $table = $realTable.clone(true).show(),
                $fixedLefts =  $table.children('thead').find('*[fixed=left]'),
                $fixedRights = $table.children('thead').find('*[fixed=right]');

            $realTable.hide().children('tbody').empty();
            $twrap.addClass('layui-table-flex');
            $twrap.children('layui-table-wrap').remove();
            var
                tableWrap = $('<div class="layui-table-wrap"></div>'),
                colgroup = $('<colgroup></colgroup>');

            tableFloat = $('<div class="layui-table-float"></div>'),
                floatBox = $('<div class="float-box"></div>'),

                tableLeft = $('<div class="layui-table-fixed fixed-left"></div>'),
                leftBox = $('<div class="left-box"></div>'),

                tableRight = $('<div class="layui-table-fixed fixed-right"></div>'),
                rightBox = $('<div class="right-box"></div>');

            //先丢进去活动表格
            var activeTable = $table.clone(true);
            if($fixedLefts.length > 0){
                //activeTable.find("input[type='checkbox'].data-ckx").removeAttr('class').removeAttr('data');
            }
            tableFloat.append(floatBox.append(activeTable));
            tableWrap.append(tableFloat);
            $twrap.children('.layui-table-wrap').remove();
            $twrap.prepend(tableWrap)

            var $table = $twrap.find('.layui-table-float table'),
                $thead = $table.children('thead'),
                $theadThs = $thead.find('th'),
                $fixedLefts = $thead.find('*[fixed=left]'),
                $fixedRights = $thead.find('*[fixed=right]');
            //计算固定列宽（left）
            var tableLeftWidth = 0;
            for (var i = 0; i < $fixedLefts.length; i++){
                var $thWidth = $($fixedLefts[i]).outerWidth(true);
                tableLeftWidth += $thWidth
                colgroup.append('<col width='+$thWidth+'px>');
            }
            tableLeft.width(tableLeftWidth);

            for (var i = 0; i < ($theadThs.length - $fixedLefts.length - $fixedRights.length); i++){
                colgroup.append('<col width="auto">');
            }
            //计算固定列宽（right）
            var tableRightWidth = 0;
            for (var i = 0; i < $fixedRights.length; i++){
                var $thWidth = $($fixedRights[i]).outerWidth(true);
                tableRightWidth += $thWidth;
                colgroup.append('<col width='+$thWidth+'px>');
            }
            tableRight.width(tableRightWidth);

            //加入colgroup
            $table.prepend(colgroup);

            //设置表格理想宽度
            var $tableWidth = $table.outerWidth(true);
            $table.width('100%')
            floatBox.width('100%');
            $table.css('min-width',$tableWidth+100)
            floatBox.css('min-width',$tableWidth+100)
            if($fixedLefts.length > 0){
                tableLeft.append(leftBox.append($table.clone(true).width('auto')));
                tableWrap.append(tableLeft);
            }
            if($fixedRights.length > 0){
                var rightTable = $table.clone(true);
                //rightTable.find("input[type='checkbox'].data-ckx").removeAttr('class').removeAttr('data');
                tableRight.append(rightBox.append(rightTable.width('auto')));
                tableWrap.append(tableRight);
            }
        }

    }
    //序列化form表单 为json数据
    $.fn.serializeObject = function(){
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    var datagrid = new Datagrid();
    exports('datagrid', function(options) {
        return datagrid;
    });
});