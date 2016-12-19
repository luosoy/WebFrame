var view = View.extend({
    tabMap: {},
    uis: {
        contentTab: {
            el: "#contentTab",
            type: "Tabs",
            config: {
                width: "100%",
                height: "600px"
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
    }
});


$(".nav").on("click", "li", function () {
    $(this).siblings().removeClass("current");
    $(this).siblings().removeClass("hasChild");
    var hasChild = !!$(this).find(".subnav").size();
    if (hasChild) {
        $(this).addClass("hasChild");
    }
    $(this).addClass("current");
});

$(window).resize(function (e) {
    $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height() - 6);
    $(".wrap").height($("#bd").height() - 6);
    $(".nav").css("minHeight", $(".sidebar").height() - $(".sidebar-header").height() - 1);
    $("#iframe").height($(window).height() - $("#hd").height() - $("#ft").height() - 12);
}).resize();

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
    return false;
});
