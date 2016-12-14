<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="stc" value="${pageContext.request.contextPath}/assets" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${stc}/lib/miniui/themes/default/miniui.css" rel="stylesheet"/>
        <link href="${stc}/lib/miniui/themes/icons.css" rel="stylesheet"/>
        <script type="text/javascript">
            var SYS = {
                 ctx: '${ctx}',
                 path: '${ctx}/web/',
                 stc: '${stc}'
            };
        </script>
        <!--[if lt IE 8]><script src="${stc}/lib/json/json3.min.js" type="text/javascript"></script><![endif]-->
        <script src="${stc}/lib/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="${stc}/lib/miniui/miniui.js" type="text/javascript"></script>
        <style>
            html,body{ margin:0px; height:100%;} 
        </style>
    </head>
    <body>
        <div id="layout1" class="mini-layout" style="width: 100%; height: 100%;" >
            <div region="north" height="60" showSplit="false" splitSize="0" style="border:0px;" showHeader="false">
                north
            </div>
            <div showProxyText="true" region="west" width="200" showSplitIcon="true"  showHeader="false">
                west
            </div>
            <div title="center" region="center"  >
            </div>
        </div>
    </body>
</html>
