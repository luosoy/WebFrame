var view = View.extend({
    uis: {
        contentTab: {
            el: "#contentTab",
            type: "Tabs",
            config: {
                width: "100%",
                height: "600px"
            },
            addTab: function (title, url) {
                var tab = {title: title, url: url, showCloseButton: true};
                var tabs = this.mui;
                tabs.addTab(tab);
                tabs.activeTab(tab);
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
    view.uis.contentTab.addTab("test",aurl);
    $(".nav>li").css({"borderColor": "#dbe9f1"});
    $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
    return false;
});
