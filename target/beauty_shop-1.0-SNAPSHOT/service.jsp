<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 16.05.21
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Services</title>
    <jsp:include page="/common/tablestyle.jsp"/>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<table>
    <c:forEach var="item" items="${catalog}">
        <tr>
            <th>${item.login}</th>
            <th><fmt:message key="${item.service.name}"/></th>
            <th><fmt:message key="${item.service.description}"/> </th>
            <th><fmt:formatNumber type="number" maxFractionDigits="2" value="${item.rate}"/></th>
        </tr>
    </c:forEach>
</table>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
