<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 17.05.21
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/common/tablestyle.jsp"/>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<c:choose>
    <c:when test="${showlink}">
        <a href="/Servlet?command=homepage"><fmt:message key="homePage"/></a>
    </c:when>
    <c:otherwise>
        <form action="/Servlet" method="post">
            <label><input type="date" name="date"/></label>
            <button type="submit" name="command" value="filterByDate"><fmt:message key="filterByDate"/></button>
        </form>
    </c:otherwise>
</c:choose>
<table>
    <tr>
        <th><fmt:message key="master"/></th>
        <th><fmt:message key="client"/></th>
        <th><fmt:message key="service"/></th>
        <th><fmt:message key="time"/></th>
        <th><fmt:message key="date"/></th>
        <th><fmt:message key="changeTime"/></th>
        <th><fmt:message key="cancel"/></th>
        <th><fmt:message key="confirmPayment"/></th>
    </tr>
    <c:forEach var="item" items="${catalog}">
        <form action="/Servlet" method="post">
            <tr>
                <td>${item.masterName}</td>
                <td>${item.clientName}</td>
                <td><fmt:message key="${item.serviceName}"/> </td>
                <td>${item.time}</td>
                <td>${item.date}</td>
                <td>
                    <button type="submit" name="action" value="changeTime"><fmt:message key="changeTime"/></button>
                </td>
                <td>
                    <button type="submit" name="action" value="cancel"><fmt:message key="cancel"/></button>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${item.paid}">
                            <fmt:message key="paid"/>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" name="action" value="confirm"><fmt:message key="confirm"/></button>
                        </c:otherwise>
                    </c:choose>
                </td>
<%--                <input type="hidden" name="masterName" value="${item.masterName}"/>--%>
<%--                <input type="hidden" name="masterId" value="${item.masterId}"/>--%>
<%--                <input type="hidden" name="clientId" value="${item.clientId}"/>--%>
<%--                <input type="hidden" name="timeslotId" value="${item.timeslotId}"/>--%>
<%--                <input type="hidden" name="date" value="${item.date}"/>--%>
<%--                <input type="hidden" name="command" value="update"/>--%>
            </tr>
            <input type="hidden" name="masterName" value="${item.masterName}"/>
            <input type="hidden" name="masterId" value="${item.masterId}"/>
            <input type="hidden" name="clientId" value="${item.clientId}"/>
            <input type="hidden" name="timeslotId" value="${item.timeslotId}"/>
            <input type="hidden" name="date" value="${item.date}"/>
            <input type="hidden" name="command" value="update"/>
        </form>
    </c:forEach>
</table>
<c:if test="${message != null}">
    <h2><fmt:message key="${message}"/></h2>
</c:if>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
