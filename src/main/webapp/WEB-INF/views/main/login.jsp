<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>系统登陆</title>
        <link href="${stc}/main/css/login.css" rel="stylesheet">
    </head>
    <body>
        <div id="container">
            <div id="bd">
                <div class="login">
                    <div class="login-top"><h1 class="logo"></h1></div>
                    <div class="login-input">
                        <p class="user ue-clear">
                            <label>用户名</label>
                            <input id="userInput" type="text" />
                        </p>
                        <p class="password ue-clear">
                            <label>密&nbsp;&nbsp;&nbsp;码</label>
                            <input id="pwdInput" type="password" />
                        </p>
                    </div>
                    <div class="login-btn ue-clear">
                        <a id="login" class="btn">登录</a>
                        <div class="remember ue-clear">
                            <input type="checkbox" id="remember" />
                            <em></em>
                            <label for="remember">记住密码</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="ft">CopyRight&nbsp;2014&nbsp;&nbsp;版权所有&nbsp;&nbsp;samxinnet.com专注于ui设计&nbsp;&nbsp;皖ICP备09001111号</div>
        <script type="text/javascript" src="${stc}/main/js/login.js"></script>
    </body>
</html>
