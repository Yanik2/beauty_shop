<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 23.05.21
  Time: 13:02
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
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<form action="/Servlet" method="post">
    <fmt:message key="rate"/>:
    <label>1<input type="radio" name="rate" value="1"/></label>
    <label>2<input type="radio" name="rate" value="2"/></label>
    <label>3<input type="radio" name="rate" value="3"/></label>
    <label>4<input type="radio" name="rate" value="4"/></label>
    <label>5<input type="radio" name="rate" value="5"/></label>
    <br>
    <label><textarea name="comment" cols="40" rows="3"></textarea></label>
    <br>
    <button type="submit" name="command" value="submitFeedback"><fmt:message key="submit"/></button>
</form>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
