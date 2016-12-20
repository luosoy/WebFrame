var view = View.extend({
    tabMap: {},
    uis: {
        contentTab: {
            el: "#contentTab",
            type: "Tabs",
            config: {
                width: "100%",
                height: "100%"
            },
            addTab: function (title, url) {
                var tabs = this.mui;
                var tmpTab = view.tabMap[title + url];
                if (tmpTab) {
                    tabs.activeTab(tmpTab);
                } else {
                    var tab = {title: title, url: url, showCloseButton: true};
                    tab.ondestroy = function (e) {
                        delete view.tabMap[e.tab.title + e.tab.url];
                    };
                    tabs.addTab(tab);
                    tabs.activeTab(tab);
                    view.tabMap[title + url] = tab;
                }
            }
        }
    },
    render: function () {
        $(".nav").on("click", "li", function () {
            $(this).siblings().removeClass("current");
            $(this).siblings().removeClass("hasChild");
            var hasChild = !!$(this).find(".subnav").size();
            if (hasChild) {
                $(this).addClass("hasChild");
            }
            $(this).addClass("current");
        });
        $(".nav>li").css({"borderColor": "#dbe9f1"});
        $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
        $(".nav").on("click", "li", function (e) {
            var aurl = $(this).find("a").attr("date-src");
            var title = $(this).find("a").html();
            if (aurl && aurl !== "") {
                view.uis.contentTab.addTab(title, aurl);
            }
            $(".nav>li").css({"borderColor": "#dbe9f1"});
            $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
        });

        $("#quit-btn").click(function () {
            mini.confirm("你确定要退出系统？", "提示", function (result) {
                if (result === "ok") {
                    $.submit({
                        url: "main/exitlogin",
                        ajaxType: 'post'
                    });
                }
            });
        });
    }
});


