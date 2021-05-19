<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Beauty Shop</title>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<br/>
<div style="display: flex; justify-content: space-between; margin: 0px 20px;">
    <div>
        <h2>Service Catalog</h2>
        <form action="/Servlet" method="post">
            <input name="command" value="getServiceCatalog" type="hidden"/>
            Sort by: name<input name="sortMethod" value="sortByName" type="radio" checked/>
            rate<input name="sortMethod" value="sortByRate" type="radio"/>
            <br>

            <p>Filter by: <br>Master <input type="radio" name="filterMethod" value="filterByMaster"/><br>
                Service <input type="radio" name="filterMethod" value="filterByService"/></p>
            Filter parameter: <input type="text" name="filter"/>
            <button name="submit" type="submit">Submit</button>
        </form>
    </div>
    <c:choose>
        <c:when test="${userLoggedIn}">
            <a href="/Servlet?command=homepage">Home page</a>
        </c:when>
        <c:otherwise>
            <div>
                <form action="/Servlet" method="post">
                    <input type="hidden" name="command" value="login"/>
                    <input name="username" type="text" placeholder="Username"/><br>
                    <input name="password" type="password" placeholder="Password"/><br>
                    <button type="submit">Login</button>
                </form>
                <p style="color: red">${errorMessage}</p>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>