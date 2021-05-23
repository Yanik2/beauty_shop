<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 23.05.21
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<c:forEach var="item" items="${feedbacks}">
    <p><fmt:message key="master"/>: ${item.masterName} </p>
    <p><fmt:message key="client"/>: ${item.clientName} </p>
    <p><fmt:message key="rate"/>: ${item.rate}</p>
    <p><fmt:message key="feedback"/>: ${item.comment}</p>
    <hr>
</c:forEach>
<p><fmt:message key="${message}"/> </p>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
