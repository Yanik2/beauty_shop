<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 16.05.21
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">Beauty Shop</h1>
<a href="/Servlet?command=main">Main page</a>
<c:if test="${userLoggedIn}">
    <div style="display: flex; justify-content: flex-end">
        <a href="/Servlet?command=logout">Log out</a>
    </div>
</c:if>
<hr>
</body>
</html>
