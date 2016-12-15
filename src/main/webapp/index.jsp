<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            response.sendRedirect(request.getContextPath() + "/web/main/index");
        %>
    </body>
</html>
