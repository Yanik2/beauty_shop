<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 17.05.21
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Client home</h1>
<jsp:include page="/common/header.jsp"/>
<form action="/Servlet" method="post">
    <c:choose>
        <c:when test="${hidedate}">
            <a href="/Servlet?command=homepage">Home page</a>
        </c:when>
        <c:otherwise>
            Appointment date: <input type="date" name="appointment_date"/><br><br>
        </c:otherwise>
    </c:choose>

    <table border="1">
        <c:forEach var="item" items="${catalog}">
            <tr>
                <th>${item.login}</th>
                <th>${item.service.name}</th>
                <th>${item.service.description}</th>
                <th>${item.rate}</th>
                <th>
                    <button type="submit" name="master" value="${item.login}">Make an appointment</button>
                    <input type="hidden" name="command" value="appointment"/>
                </th>
            </tr>
        </c:forEach>
    </table>
</form>
<h2>${message}</h2>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
