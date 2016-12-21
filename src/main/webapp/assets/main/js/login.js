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

    $("#login").click(function () {
        var loginMsg = $("#login-msg-span");
        loginMsg.html("");
        var msg = "";
        var bool = true;
        var loginName = $("#userInput").val();
        var pwd = $("#pwdInput").val();
        if (!loginName || loginName == "") {
            msg += "用户名不能为空！";
            bool = false;
        }
        if (!pwd || pwd == "") {
            msg += "密码不能为空！";
            bool = false;
        }
        loginMsg.html(msg);
        if (bool) {
            $.submit({
                url: "main/dologin",
                type: 'post',
                data: {
                    loginName: loginName,
                    password: pwd,
                    remember: $("#remember").is(':checked')
                }
            });
        }
    });
});
