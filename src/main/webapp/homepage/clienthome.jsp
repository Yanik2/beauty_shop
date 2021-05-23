<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 17.05.21
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title><fmt:message key="client"/></title>
    <jsp:include page="/common/tablestyle.jsp"/>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<form action="/Servlet" method="post">
    <c:choose>
        <c:when test="${hidedate}">
            <a href="/Servlet?command=homepage"><fmt:message key="homePage"/></a>
        </c:when>
        <c:otherwise>
            <label><fmt:message key="appointmentDate"/>: <input type="date" name="appointment_date"/></label>
            <br><br>
        </c:otherwise>
    </c:choose>

    <table>
        <c:forEach var="item" items="${catalog}">
            <tr>
                <th>${item.login}</th>
                <th>${item.service.name}</th>
                <th>${item.service.description}</th>
                <th>${item.rate}</th>
                <th>
                    <button type="submit" name="master" value="${item.login}"><fmt:message key="makeAnAppointment"/></button>
                    <input type="hidden" name="command" value="appointment"/>
                </th>
                <th>
                    <form action="/Servlet" method="post">
                        <button type="submit" name="command" value="leaveFeedback"><fmt:message key="leaveFeedback"/></button>
                        <input type="hidden" name="masterId" value="${item.id}"/>
                    </form>
                </th>
            </tr>
        </c:forEach>
    </table>
</form>

<h2><fmt:message key="${message}"/></h2>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
