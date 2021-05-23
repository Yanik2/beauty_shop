<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 16.05.21
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="text-align: center"><fmt:message key="beautyShop"/></h1>
<a href="/Servlet?command=main"><fmt:message key="mainPage"/></a>
<c:if test="${userLoggedIn}">
    <div style="display: flex; justify-content: flex-end">
        <a href="/Servlet?command=logout"><fmt:message key="logOut"/></a>
    </div>
</c:if>
<hr>
</body>
</html>
