<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 17.05.21
  Time: 22:28
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
<jsp:include page="../common/header.jsp"/>
<h1><fmt:message key="accessFailed"/> </h1>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>
