$(function () {
    var height = $(window).height();
    $("#container").height(height);
    $("#bd").css("padding-top", height / 2 - $("#bd").height() / 2);

    $(window).resize(function () {
        var height = $(window).height();
        $("#bd").css("padding-top", $(window).height() / 2 - $("#bd").height() / 2);
        $("#container").height(height);

    });

    $('#remember').focus(function () {
        $(this).blur();
    });

    $('#remember').click(function (e) {
        checkRemember($(this));
    });

    function checkRemember($this) {
        if ($this.prop("checked")) {
            $this.parent().addClass('checked');
        } else {
            $this.parent().removeClass('checked');
        }
    }
    
    $("#login").click(function(){
        $.submit({
           url:"main/dologin",
           ajaxType:'post'
        });
    });
});
