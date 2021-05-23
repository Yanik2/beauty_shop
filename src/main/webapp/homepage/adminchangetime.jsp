<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 21.05.21
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
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
<table>
    <c:forEach var="item" items="${timeslots}">
        <form action="/Servlet" method="post">
            <tr>
                <th>${item}</th>
                <th>
                    <button type="submit" name="command" value="changeTimeslot"><fmt:message key="select"/></button>
                </th>
<%--                <input type="hidden" name="newTimeslot" value="${item}"/>--%>
            </tr>
            <input type="hidden" name="newTimeslot" value="${item}"/>
        </form>
    </c:forEach>
</table>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
