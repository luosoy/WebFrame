$(".nav").on("click","li",function(){
	$(this).siblings().removeClass("current");
    $(this).siblings().removeClass("hasChild");
	var hasChild = !!$(this).find(".subnav").size();
	if(hasChild){
		$(this).addClass("hasChild");
	}
	$(this).addClass("current");
});

$(window).resize(function(e) {
    $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height()-6);
	$(".wrap").height($("#bd").height()-6);
	$(".nav").css("minHeight", $(".sidebar").height() - $(".sidebar-header").height()-1);
	$("#iframe").height($(window).height() - $("#hd").height() - $("#ft").height()-12);
}).resize();

$(".nav>li").css({"borderColor":"#dbe9f1"});
$(".nav>.current").prev().css({"borderColor":"#7ac47f"});
$(".nav").on("click","li",function(e){
	var aurl = $(this).find("a").attr("date-src");
	$(".nav>li").css({"borderColor":"#dbe9f1"});
	$(".nav>.current").prev().css({"borderColor":"#7ac47f"});
	return false;
});
