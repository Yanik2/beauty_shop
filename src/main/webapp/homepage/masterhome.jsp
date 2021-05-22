<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 17.05.21
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<c:if test="${showlink}">
    <a href="/Servlet?command=homepage"><fmt:message key="homePage"/></a>
</c:if>
<table border="1">
    <c:forEach var="item" items="${catalog}">
        <tr>
            <th>${item.timeslot}</th>
            <th><c:choose>
                <c:when test="${item.availability}">
                    <fmt:message key="notBooked"/>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${item.done}"><fmt:message key="done"/> </c:when>
                        <c:otherwise>
                            <form action="/Servlet" method="post">
                                <button type="submit" name="command" value="markAsDone"><fmt:message key="markAsDone"/></button>
                                <input type="hidden" name="timeslot_id" value="${item.timeslot_id}"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose></th>
            <th><c:choose>
                <c:when test="${item.availability}">
                    <fmt:message key="notBooked"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="booked"/>
                </c:otherwise>
            </c:choose></th>
        </tr>
    </c:forEach>
</table>
<c:if test="${message != null}">
    <h2><fmt:message key="${message}"/></h2>
</c:if>

<%--<h2>${message}</h2>--%>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
