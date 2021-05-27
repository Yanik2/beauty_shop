<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 23.05.21
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    <fmt:message key="404error"/>
</h1>

</body>
</html>
