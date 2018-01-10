/** common.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */
layui.config({
    base: '/resources2.0/js/'
}).define(['layer','form'], function(exports) {
	"use strict";

	var $ = layui.jquery,
		layer = layui.layer, form = layui.form;

	var common = {
		/**
		 * 抛出一个异常错误信息
		 * @param {String} msg
		 */
		throwError: function(msg) {
			throw new Error(msg);
			return;
		},
		/**
		 * 弹出一个错误提示
		 * @param {String} msg
		 */
		msgError: function(msg) {
			layer.msg(msg, {
				icon: 5
			});
			return;
		},
		/**
		 *   将post的数据转换成key-val的格式，支持基本数据类型的数组参数转换为多个key-val，不支持对象转换需自己转换后调用请求
		 * */
        arrayTokeyval: function (data) { // 将post的数据转换成key-val的格式，支持基本数据类型的数组参数转换为多个key-val，不支持对象转换需自己转换后调用请求
            console.log(data)
            let ret = ''
            for (let it in data) {
                if (data[it] instanceof Array) {
                    for (let arr of data[it]) {
                        ret += encodeURIComponent(it) + '=' + encodeURIComponent(arr) + '&'
                    }
                } else {
                    ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                }
            }
            return ret
        },
        layerIframeAuto: function (index) {
            layer.iframeAuto(index);
        },
        layerShowIframe: function (url,title,isFull) {//弹出新窗口
            var index = layer.open({
                type: 2,
                title: title,
                shadeClose: true,
                shade: false,
                maxmin: true, //开启最大化最小化按钮
                offset: ['0px'],
                area: ['1000px', '600px'],
                content: url
            });
            if (isFull){
                layer.full(index)
            }
            return index;
        },
        addForm: function (content,title,url,width,height,offset,isFull,callback) {//弹出窗口 类型为1
            var index = layer.open({
                content: $('#'+content),
                type: 1,
                title: title,
                offset: offset ,
                area: [width+'px',height+'px'],
                btn: ['保存', '取消'],
                maxmin: false,
                yes: function(index, layero) {
                    var formEm = $(layero).find('form');
                    if (!form.onVerify(formEm)) {
                        return false;
                    }
                    /**表单提交 **/
                    $.post(url, formEm.serialize(), function (result) {
                        if (result.code == 0) {
                            formEm[0].reset();	//清空弹框表单内容
                            layer.close(index);	//关闭弹框
                            callback;
                            return;
                        }
                        layer.msg(result.msg);
                    });
                }
            });
            if (isFull){
                layer.full(index)
            }
        },
        editForm: function (content,title,url,formId,callback,isFull) {//弹出窗口 类型为1
            var index = layer.open({
                type: 2,
                title: title,
                shadeClose: true,
                shade: false,
                maxmin: true, //开启最大化最小化按钮
                offset: ['0px'],
                area: ['1000px', '600px'],
                content: content,
                btn: ['保存', '取消'],
                yes: function(index, layero) {
                    var formEm = $(layero).find('iframe').contents().find(formId);
                    if (!form.onVerify(formEm)) {
                        return false;
                    }
                    /**表单提交 **/
                    $.post(url, formEm.serialize(), function (result) {
                        if (result.code == 0) {
                            formEm[0].reset();	//清空弹框表单内容
                            layer.close(index);	//关闭弹框
                            callback;
                        }
                        layer.msg(result.msg);
                    });
                }
            });
            if (isFull){
                layer.full(index)
            }
        },
        layerShow: function (url,id,title) {//弹出窗口 类型为1
            $.get(url, {id: id}, function (html) {
                //打开编辑页
                var index = layer.open({
                    type: 1,
                    title: title,
                    shadeClose: true,
                    shade: false,
                    area: ['893px', '600px'],
                    content: html
                });
                layer.full(index)
            }, "html");
        },
        formatForm: function (form) {
            var a = form.serializeArray();
            var o = {};
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
        },
        post: function (url,data,callback) {
            $.ajax({
                type: "POST",
                url: url,
                data: common.arrayTokeyval(data),
                success: callback
            });
        },
        stampToDate: function (stamp) {
            if (stamp == undefined || stamp == "") {
                return "";
            }
            var newDate = new Date();
            var stampArr = stamp.split(",");
            var dateArr = new Array();
            for (var i = 0; i < stampArr.length; i++) {
                var newDate = new Date();
                newDate.setTime(stampArr[i]);
                dateArr.push(newDate.Format("yyyy-MM-dd"));
            }
            return dateArr.join(" , ");
        },
        selImg: function (k) {
            null == k && (k = "select");
            $("" + k).each(function () {
                var e = $(this).find("option"), f = 0;
                for (var i = 1; i < e.length; i++) {
                    var d = $(this).siblings("div.layui-form-select").find("dd");
                    console.log(d)
                    var value = $(e[i]).val(),text = $(e[i]).text();
                    var img = '<span><i class="layui-icon">&#'+value+';</i>'+text+'</span>'
                    $(d[i]).attr("lay-value",'&#'+value+';');
                    $(d[i]).html(img);
                }
            })
        },
        showImage: function (url,showMaxWidth) {
            if (url !== null) {
                var image = new Image();
                image.src = url;
                var width = image.width;
                if (width > showMaxWidth) {
                    width = showMaxWidth;
                }
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    area: width,
                    skin: 'layui-layer-nobg', //没有背景色
                    shadeClose: true,
                    content: "<img src="+url+" width="+width+" />"
                });
            }
        }
	};
	exports('common', common);
});