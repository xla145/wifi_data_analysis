/** layuiAdmin-v1.0.0-beta4 GPL-v2 License By http://www.layui.com/admin/ */
;layui.extend({setter: "config", admin: "lib/admin", view: "lib/view"}).define(["setter", "admin"], function (e) {
    var a = layui.setter, n = layui.element, i = layui.admin, t = i.tabsPage, l = layui.view, o = function () {
        var e = layui.router(), r = e.path, c = e.href || a.entry;
        r.length || (r = [""]), "" === r[r.length - 1] && (r[r.length - 1] = a.entry), layui.config({base: a.base + "controller/"});
        var h = function (e) {
            o.haveInit && layer.closeAll(), o.haveInit = !0, s(d).scrollTop(0), delete t.type
        };
        return "tab" === t.type && (c !== a.entry || c === a.entry && i.tabsBody().html()) ? (i.tabsBodyChange(t.index), h(t.type)) : (l().render(r.join("/")).then(function (l) {
            var o, r = s("#LAY_app_tabsheader>li");
            r.each(function (e) {
                var a = s(this), n = a.attr("lay-id");
                n === c && (o = !0, t.index = e)
            }), a.pageTabs && c !== a.entry && (o || (s(d).append('<div class="layadmin-tabsbody-item layui-show"></div>'), t.index = r.length, n.tabAdd(y, {
                title: "<span>" + (l.title || "新标签页") + "</span>",
                id: e.href
            }))), this.container = i.tabsBody(t.index), n.tabChange(y, c), i.tabsBodyChange(t.index)
        }).done(function () {
            layui.use("common", layui.cache.callback.common), u.on("resize", layui.data.resize), n.render("breadcrumb", "breadcrumb"), i.tabsBody(t.index).on("scroll", function () {
                var e = s(this), a = s(".layui-laydate"), n = s(".layui-layer")[0];
                a[0] && (a.each(function () {
                    var e = s(this);
                    e.hasClass("layui-laydate-static") || e.remove()
                }), e.find("input").blur()), n && layer.closeAll("tips")
            })
        }), void h())
    }, r = function (e) {
        var n = layui.router(), t = l(a.container);
        "user/login" === n.path.join("/") ? t.render("user/login").done(function () {
            i.pageType = "alone"
        }) : "console" === i.pageType ? o() : t.render("layout").done(function () {
            o(), layui.element.render(), i.screen() < 2 && i.sideFlexible(), i.pageType = "console"
        })
    }, d = "#LAY_app_body", y = "layadmin-layout-tabs", s = layui.$, u = s(window);
    layui.link(a.base + "style/admin.css?v=" + (i.v + "-2"), function () {
        r()
    }, "layuiAdmin"), window.onhashchange = function () {
        r(), layui.event.call(this, a.MOD_NAME, "hash({*})", layui.router())
    }, layui.each(a.extend, function (e, n) {
        var i = {};
        i[n] = "{/}" + a.base + "lib/extend/" + n, layui.extend(i)
    }), e("index", {render: o})
});
