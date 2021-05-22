<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 19.05.21
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title><fmt:message key="client"/> </title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<h2><fmt:message key="master"/>: ${master.login}</h2>
<h2><fmt:message key="date"/>: ${date}</h2>
<h2><fmt:message key="chooseTime"/> :</h2>
<form action="/Servlet" method="post">
    <table border="1">
        <c:forEach var="item" items="${timeslots}">
            <tr>
                <th>${item}</th>
                <th>
                    <button type="submit" name="timeslot" value="${item}"><fmt:message key="book"/></button>
                    <input type="hidden" name="command" value="book_time"/>
                </th>
            </tr>
        </c:forEach>
    </table>
</form>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
