<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>主页</title>
        <link href="${stc}/main/css/index.css" rel="stylesheet">
    </head>
    <body style="width: 100%;height: 100%">
        <div id="layout1" class="mini-layout" style="width: 100%;height: 100%;">
            <div title="north" region="north" height="76" showHeader="false" showSplit="false" splitSize="0" style="border: 0px;">
                <div id="hd">
                    <div class="hd-wrap ue-clear">
                        <div class="top-light"></div>
                        <div class="logo">
                            <span>system</span>
                        </div>
                        <div class="toolbar ue-clear">
                            <a id="home-btn" href="javascript:;" class="home-btn"></a>
                            <a id="quit-btn" href="javascript:;" class="quit-btn exit"></a>
                        </div>
                        <div class="login-info ue-clear">
                            <div class="welcome ue-clear"><span>欢迎您,</span>
                                <a href="javascript:;" class="user-name">Admin</a>
                            </div>
                            <div class="login-msg ue-clear">
                                <a href="javascript:;" class="msg-txt">消息</a>
                                <a href="javascript:;" class="msg-num">10</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div title="功能导航" showProxyText="true" region="west" width="200" showSplitIcon="true" >
                <div class="sidebar">
                    <ul class="nav">
                        <li class="office current">
                            <div class="nav-header">
                                <a href="javascript:;" class="ue-clear">
                                    <span>日常办公</span>
                                    <i class="icon"></i>
                                </a>
                            </div>
                            <ul class="subnav">
                                <li><a href="javascript:;" date-src="info-reg.html" >信息录入</a></li>
                                <li><a href="javascript:;" date-src="info-mgt.html" >信息管理</a></li>
                                <li><a href="javascript:;" date-src="info-det.html" >领导值岗管理</a></li>
                                <li><a href="javascript:;">中层领导管理</a></li>
                                <li><a href="javascript:;">领导值班记录</a></li>
                            </ul>
                        </li>
                        <li class="gongwen">
                            <div class="nav-header">
                                <a href="javascript:;" class="ue-clear">
                                    <span>公文起草</span>
                                    <i class="icon"></i>
                                </a>
                            </div>
                            <ul class="subnav">
                                <li><a href="javascript:;" date-src="info-reg.html" >信息录入</a></li>
                                <li><a href="javascript:;" date-src="info-mgt.html" >信息管理</a></li>
                                <li><a href="javascript:;" date-src="info-det.html" >领导值岗管理</a></li>
                                <li><a href="javascript:;">中层领导管理</a></li>
                                <li><a href="javascript:;">领导值班记录</a></li>
                            </ul>
                        </li>
                        <li class="nav-info">
                            <div class="nav-header">
                                <a href="javascript:;" class="ue-clear">
                                    <span>导航信息管理</span>
                                    <i class="icon"></i>
                                </a>
                            </div>
                            <ul class="subnav">
                                <li><a href="javascript:;" date-src="info-reg.html" >信息录入</a></li>
                                <li><a href="javascript:;" date-src="info-mgt.html" >信息管理</a></li>
                                <li><a href="javascript:;" date-src="info-det.html" >领导值岗管理</a></li>
                                <li><a href="javascript:;">中层领导管理</a></li>
                                <li><a href="javascript:;">领导值班记录</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>

            <div title="center" region="center" style="border: 0px;"  >
                <div id="contentTab" style="width: 100%;height: 100%"></div>
            </div>
        </div>
        <script type="text/javascript" src="${stc}/main/js/index.js"></script>
    </body>
</html>
