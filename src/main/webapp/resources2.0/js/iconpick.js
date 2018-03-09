layui.define(function(exports){
  	var $ = layui.$;
    var allicons=["layui-e-yunshangchuan","layui-e-bangzhu","layui-e-renminbi","layui-e-dasuolvetuliebiao","layui-e-suoxiao","layui-e-fangda","layui-e-quanping1","layui-e-suoxiao1","layui-e-fangda1","layui-e-xinjianchuangkou","layui-e-xinchuangkoudakai","layui-e-dingyue1","layui-e-xinhao","layui-e-html","layui-e-css","layui-e-tuihuobaozhang","layui-e-huodongyouxian","layui-e-tianmaopaidui","layui-e-shengriliwu","layui-e-jisutuikuan","layui-e-kefuyouxian","layui-e-chaozhijifen","layui-e-tianmaohaoquan","layui-e-liebiao","layui-e-chongzhi","layui-e-shafa","layui-e-tushu","layui-e-shouji","layui-e-xiai","layui-e-liebiao1","layui-e-gouwucheman","layui-e-huangguan","layui-e-tesefuwu","layui-e-zhengpinbaozhang","layui-e-shuru","layui-e-chongshuidian","layui-e-wodezichan","layui-e-0","layui-e-9","layui-e-8","layui-e-7","layui-e-6","layui-e-5","layui-e-4","layui-e-3","layui-e-2","layui-e-1","layui-e-you2","layui-e-re2","layui-e-huanyipi","layui-e-explorefill","layui-e-game","layui-e-selectionfill","layui-e-similar","layui-e-appreciatefill","layui-e-24","layui-e-30","layui-e-tao","layui-e-voicefill","layui-e-sousuo3","layui-e-gongnengjianyi","layui-e-msnui-rmb","layui-e-msnui-menu","layui-e-msnui-db","layui-e-brandfill","layui-e-brand","layui-e-rankfill","layui-e-rank","layui-e-friendfill","layui-e-rectangle390","layui-e-qinmifu","layui-e-taobao1","layui-e-qianbaopengyou","layui-e-huankuan","layui-e-zhifubaoa","layui-e-zhifubaob","layui-e-fuwuchuangb","layui-e-caifub","layui-e-browser","layui-e-check","layui-e-copy","layui-e-down","layui-e-download","layui-e-emoji","layui-e-file","layui-e-folder","layui-e-home","layui-e-left","layui-e-like","layui-e-message","layui-e-minus","layui-e-refresh","layui-e-right","layui-e-search","layui-e-sitting","layui-e-toleft","layui-e-toright","layui-e-unfold","layui-e-unlock","layui-e-upload","layui-e-all","layui-e-delete1","layui-e-set","layui-e-viewgallery","layui-e-markfill","layui-e-mark","layui-e-wangwang","layui-e-money","layui-e-people3","layui-e-favorite","layui-e-xinrenkecheng","layui-e-xinrenzhinan","layui-e-iconfont5","layui-e-gongnengqiehuan2","layui-e-rizhi","layui-e-weixin","layui-e-yonghuzhongxin","layui-e-fenlei1","layui-e-wode1","layui-e-caidan1","layui-e-emailfilling","layui-e-accountfilling","layui-e-iconset0194","layui-e-iconset0195","layui-e-xitongguanli","layui-e-rizhi1","layui-e-tishifill","layui-e-wenhaofill","layui-e-wenhao","layui-e-biaoxingfill","layui-e-biaoxing","layui-e-dianpufill","layui-e-dingdan1","layui-e-fankui","layui-e-gonglve","layui-e-gouwuchefill","layui-e-gouwuche1","layui-e-shanchu","layui-e-shezhi1","layui-e-shenfen","layui-e-shizhongfill","layui-e-shizhong","layui-e-sousuo2","layui-e-sousuoleimufill","layui-e-sousuoleimu","layui-e-taobao","layui-e-taojinbi","layui-e-tongzhifill","layui-e-tongzhi","layui-e-tuikuan","layui-e-wangwangfill","layui-e-wangwang1","layui-e-wuliu","layui-e-xihuanfill","layui-e-xihuan","layui-e-xinxifill","layui-e-xinxi","layui-e-youhuiquan","layui-e-zhengli","layui-e-zuji","layui-e-zuobiaofill","layui-e-zuobiao","layui-e-dibu","layui-e-dingbu","layui-e-xiangji1fill","layui-e-xiangji1","layui-e-xiangji2","layui-e-shuaxin1","layui-e-wifi","layui-e-shoujitaobao","layui-e-salefill","layui-e-sale","layui-e-androidgengduo","layui-e-bangzhuzhongxin","layui-e-caidan","layui-e-weibiaoti5","layui-e-shezhi2","layui-e-liebiaolist29","layui-e-msnui-history","layui-e-gouwuche","layui-e-gouwuchetianjia","layui-e-cuowu","layui-e-erweima","layui-e-lajixiang","layui-e-moban","layui-e-naozhong","layui-e-saoyisao1","layui-e-shezhi","layui-e-shengyin","layui-e-shijian","layui-e-shouhuodizhi","layui-e-shouye1","layui-e-shuaxin","layui-e-sousuo1","layui-e-suo","layui-e-tishi","layui-e-wancheng","layui-e-wodedingdan","layui-e-wodefankui","layui-e-wodehongbao","layui-e-wodejuhuasuan","layui-e-wodeyouhuiquan","layui-e-xiafan","layui-e-xiangshangjiantou","layui-e-xiangxiajiantou","layui-e-xiangyoujiantou","layui-e-xiangzuojiantou","layui-e-yanjing","layui-e-yijianfankui","layui-e-zhaoxiangji","layui-e-zhengque","layui-e-xiaoxizhongxin","layui-e-lingcunwei","layui-e-yuanquanjiahao","layui-e-canyin","layui-e-zhusu","layui-e-duoyuyan","layui-e-feiji","layui-e-kongdiao","layui-e-mudedi","layui-e-wuxianwangluo","layui-e-xiangji","layui-e-youji","layui-e-shouye2","layui-e-tixing","layui-e-zanfill","layui-e-roundadd","layui-e-roundclosefill","layui-e-dangdi","layui-e-matou","layui-e-suoding","layui-e-zengjia","layui-e-zhuyi","layui-e-ziyouanpai","layui-e-dianhua","layui-e-chazhaobiaodanliebiao","layui-e-leimupinleifenleileibie","layui-e-icon1","layui-e-ai-weixin","layui-e-xianjin","layui-e-icon14","layui-e-qq","layui-e-4fayuanheimingdan272636","layui-e-zelvgongyongyonghuzhongxinz003","layui-e-danpin","layui-e-301","layui-e-renminbi1","layui-e-jushoucanggift","layui-e-liwu","layui-e-yuyin","layui-e-zhifubao","layui-e-xianjin1","layui-e-shujuku","layui-e-dingdan2","layui-e-heimingdan","layui-e-wenzhang-copy","layui-e-qq-copy","layui-e-weixin-copy","layui-e-icondd1","layui-e-full","layui-e-mail","layui-e-wenjianjia","layui-e-iconfontyouxihudong","layui-e-icon_article","layui-e-quanxianguanli","layui-e-logo","layui-e-newsfill","layui-e-newshotfill","layui-e-newshot","layui-e-zhifubao1","layui-e-xitong2","layui-e-xitong","layui-e-iconfontzhizuobiaozhun023104","layui-e-rizhi2","layui-e-caidan2","layui-e-zhifu","layui-e-qq1","layui-e-shouye4","layui-e-gongnengkaiguan","layui-e-liebiao2","layui-e-biaoge","layui-e-caidan3","layui-e-caidan4","layui-e-zhifubao2","layui-e-heimingdan1","layui-e-component-filling","layui-e-color","layui-e-shouye5","layui-e-quanxianguanli1","layui-e-color-filling","layui-e-wenzhang","layui-e-liebiao3","layui-e-liebiao4","layui-e-pic-filling","layui-e-scan_light","layui-e-wang_light","layui-e-qq2","layui-e-logistic","layui-e-gangweiquanxian","layui-e-weixin1","layui-e-heimingdan3","layui-e-biaoge1","layui-e-youxi","layui-e-quanxianguanli2","layui-e-activity","layui-e-activity_fill","layui-e-addition_fill","layui-e-addition","layui-e-addpeople_fill","layui-e-addressbook_fill","layui-e-barrage_fill","layui-e-browse_fill","layui-e-brush","layui-e-brush_fill","layui-e-businesscard_fill","layui-e-camera_fill","layui-e-clock_fill","layui-e-coordinates_fill","layui-e-coordinates","layui-e-createtask_fill","layui-e-createtask","layui-e-delete_fill","layui-e-delete","layui-e-document_fill","layui-e-empty","layui-e-empty_fill","layui-e-enterinto","layui-e-enterinto_fill","layui-e-setup","layui-e-deliver_fill","layui-e-system-copy","layui-e-wenjianjia1","layui-e-wenzhang1","layui-e-moban1","layui-e-wenjianjia2","layui-e-customs-clearance","layui-e-fenxiang","layui-e-fanhui","layui-e-guanbi","layui-e-bofang","layui-e-kefu","layui-e-shenfenzheng","layui-e-quanping","layui-e-duigou","layui-e-shuoming","layui-e-zanting","layui-e-shoucang","layui-e-jiantoushang","layui-e-jiantoushang1","layui-e-jiantouyou","layui-e-jiantouxia","layui-e-saoyisao","layui-e-wode","layui-e-fenlei","layui-e-xiaoxi","layui-e-faxian","layui-e-sousuo","layui-e-liulan","layui-e-zhiding","layui-e-xuanzhong","layui-e-tabguanbi","layui-e-kexuanzuobiankuang","layui-e-zuoweibeijing","layui-e-bukexuanzuo","layui-e-jinqian","layui-e-yigouxuan","layui-e-weigouxuan","layui-e-shouye7","layui-e-guanyanren","layui-e-dingyue","layui-e-dizhiguanli","layui-e-daifukuan","layui-e-daishouhuo","layui-e-pingjia","layui-e-yanchurili","layui-e-xitong1","layui-e-changyonggoupiaorenbianji","layui-e-changyonggoupiaorenshanchu","layui-e-dingdan","layui-e-weigouxuan1","layui-e-yigouxuan1","layui-e-tishishuoming","layui-e-guanbi1","layui-e-jiajianzujianjiahao","layui-e-huanyihuan","layui-e-wodedamaijihuo","layui-e-shujuku2","layui-e-left-template","layui-e-jinqian1","layui-e-setting-permissions","layui-e-moban2","layui-e-wode3","layui-e-bank-card","layui-e-bar-chart","layui-e-camera","layui-e-businesscard","layui-e-forward-sorting","layui-e-last-page","layui-e-like1","layui-e-location","layui-e-RMB","layui-e-service","layui-e-time-o","layui-e-view-list-o","layui-e-vertical-retraction-","layui-e-arrow-left","layui-e-arrow-right","layui-e-qq3"];
    var ihtml='<div class="popover"><div class="popover-title"><input type="search" class="form-control iconpicker-search" placeholder="查找图标"></div>'
        ihtml+='<div class="iconpicker-items"><ul class="icon_lists"></ul></div></div>';
        if($('.popover').length<=0){
            $('body').append(ihtml)
        }

        for (var i in allicons) {
            $('.icon_lists').append('<li class="hov" data-icon="iconfont '+allicons[i]+'"><i class="iconfont '+allicons[i]+'"></i></li>');
        }
        
    var picksel={
        pickinit:function(es,els){
            var e,el;
            if ($('#'+es).length>0) {
                e=$('#'+es);
            }else{
                e=$('.'+es);
            }
            if ($('#'+els).length>0) {
                el=$('#'+els);
            }else{
                el=$('.'+els);
            }
            e.click(function(){
                if($('.popover').css('display')=="none"){
                    $('.popover').css({
                        'left':e.offset().left,
                        'top':e.offset().top+e[0].clientHeight+5
                    });
                    $('.popover').slideDown(150);;
                }else{
                    $(".popover").slideUp(150);
                }
                
            }),$(document).click(function(){
                $(".popover").slideUp(150);
            }),$(e).click(function(event){
                event.stopPropagation();
            }),$('.popover').click(function(event){
                event.stopPropagation();
            }),$('.icon_lists li').click(function(){
                $(this).addClass('selectd').removeClass('hov').siblings().removeClass('selectd');
                // e.find('i').attr('class',$(this).attr("data-icon"));
                el.val($(this).attr("data-icon"));
            }),$('.iconpicker-search').keyup(function(){
                var str=$(this).val();
                var icons = [];
                $('.icon_lists li').each(function(){
                    var b = $(this);
                    var e = b.attr("data-icon").toLowerCase();
                    var f = false;
                    try {
                        f = new RegExp(str, "g");
                    } catch (a) {
                        f = false;
                    }
                    if (f !== false && e.match(f)) {
                        icons.push(b);
                        b.show();
                    } else {
                        b.hide();
                    }
                })

            })
        }
    }
    exports('iconpick', picksel);
});

