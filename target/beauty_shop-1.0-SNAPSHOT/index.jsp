<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html>
<head>
    <title>Beauty Shop</title>
</head>
<body>
<form action="/Servlet" method="post">
    <button type="submit" name="language" value="en">EN</button>
    <button type="submit" name="language" value="uk">UA</button>
    <input type="hidden" name="command" value="changeLanguage"/>
</form>
<jsp:include page="common/header.jsp"/>
<br/>
<div style="display: flex; justify-content: space-between; margin: 0 20px;">
    <div>
        <h2><fmt:message key="serviceCatalog"/></h2>
        <form action="/Servlet" method="post">
            <input name="command" value="getServiceCatalog" type="hidden"/>
            <fmt:message key="sortBy"/>: <fmt:message key="name"/><input name="sortMethod" value="sortByName" type="radio" checked/>
            <fmt:message key="rate"/><input name="sortMethod" value="sortByRate" type="radio"/>
            <br>

            <p><fmt:message key="filterBy"/>: <br><fmt:message key="master"/> <input type="radio" name="filterMethod" value="filterByMaster"/><br>
                <fmt:message key="service"/> <input type="radio" name="filterMethod" value="filterByService"/></p>
            <fmt:message key="filterParameter"/> : <input type="text" name="filter"/>
            <button name="submit" type="submit"><fmt:message key="submit"/></button>
        </form>
    </div>
    <c:choose>
        <c:when test="${userLoggedIn}">
            <a href="/Servlet?command=homepage"><fmt:message key="homePage"/></a>
        </c:when>
        <c:otherwise>
            <div>
                <form action="/Servlet" method="post">
                    <input type="hidden" name="command" value="login"/>
                    <input name="username" type="text" placeholder="<fmt:message key="username"/>"/><br>
                    <input name="password" type="password" placeholder="<fmt:message key="password"/>"/><br>
                    <button type="submit"><fmt:message key="login"/> </button>
                </form>
                <p style="color: red">${errorMessage}</p>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>