/**
 * Created by Administrator on 2017/8/28/028.
 */

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

Date.prototype.addTime = function (type,number) { //author: meizz
    // type 1 年 2 月 3天
    if (type == 3) {
        var time  = this.getTime() + (number*24* 60 * 60 * 1000);
        return new Date(time);
    }
}

stampToDate = function (stamp) {
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
}




