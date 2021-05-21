<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 17.05.21
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Admin Home</h1>
<jsp:include page="/common/header.jsp"/>
<c:choose>
    <c:when test="${showlink}">
        <a href="/Servlet?command=homepage">Home page</a>
    </c:when>
    <c:otherwise>
        <form action="/Servlet" method="post">
            <input type="date" name="date"/>
            <button type="submit" name="command" value="filterByDate">Filter by date</button>
        </form>
    </c:otherwise>
</c:choose>
<table border="1">
    <tr>
        <th>Master</th>
        <th>Client</th>
        <th>Service</th>
        <th>Time</th>
        <th>Date</th>
        <th>Change time</th>
        <th>Cancel</th>
        <th>Confirm Payment</th>
    </tr>
    <c:forEach var="item" items="${catalog}">
        <tr>
            <form action="/Servlet" method="post">
                <td>${item.masterName}</td>
                <td>${item.clientName}</td>
                <td>${item.serviceName}</td>
                <td>${item.time}</td>
                <td>${item.date}</td>
                <td><button type="submit" name="action" value="changeTime">Change time</button></td>
                <td><button type="submit" name="action" value="cancel">Cancel</button></td>
                <td>
                    <c:choose>
                        <c:when test="${item.paid}">
                            Paid
                        </c:when>
                        <c:otherwise>
                            <button type="submit" name="action" value="confirm">Confirm</button>
                        </c:otherwise>
                    </c:choose>
                </td>
                <input type="hidden" name="masterName" value="${item.masterName}"/>
                <input type="hidden" name="masterId" value="${item.masterId}"/>
                <input type="hidden" name="clientId" value="${item.clientId}"/>
                <input type="hidden" name="timeslotId" value="${item.timeslotId}"/>
                <input type="hidden" name="date" value="${item.date}"/>
                <input type="hidden" name="command" value="update"/>
            </form>
        </tr>
    </c:forEach>
</table>
<h2>${message}</h2>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
