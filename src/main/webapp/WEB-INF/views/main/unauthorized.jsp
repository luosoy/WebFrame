<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>没有权限</title>
    <link href="${stc}/main/css/unauthorized.css" rel="stylesheet">
</head>
<body style="background-color:#FFFFFF;">
	<div class="error-container">
		<div class="well">
			<br/><br/><br/><br/><br/><br/>
			<div class="center" >
				<img src="${stc}/main/images/noAuthLockIcon.png" />
				<hr/>
				<div class="space"></div>
				<h4 class="smaller">
				<span class="bigger-125"> <i class="icon-warning-sign" style="color:#fee188"></i>
					抱歉...当前页面您没有访问权限，请联系管理员
				</span>
				</h4>
			</div>
			<br/><br/><br/>
		</div>
	</div>	
</body>
</html>