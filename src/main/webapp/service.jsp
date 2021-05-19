<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 16.05.21
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Services</title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<table border="1">
    <c:forEach var="item" items="${catalog}">
        <tr>
            <th>${item.login}</th>
            <th>${item.service.name}</th>
            <th>${item.service.description}</th>
            <th>${item.rate}</th>
        </tr>
    </c:forEach>
</table>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
