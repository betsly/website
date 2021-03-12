<%-- 
    Document   : Start
    Created on : 12.11.2020, 14:05:45
    Author     : zmugg
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Betsly</title>
    <a>Betsly garantiert großen Wettspaß!!</a>

</head>
<body>
    <form  action="./BetslyServlet" method="POST">
        <c:choose>
            <c:when test="${jwtUser == null}">
                <input type="submit" value="Registrieren" name="registration"/>
                <input type="submit" value="Login" name="login" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="show groups" name="showGroups" />
                <input type="submit" value="create group" name="createGroupForm" />
                <input type="submit" value="join group" name="joinGroupForm" />
                <input type="submit" value="create Bet Groupphase" name="createGroupPhaseBetForm" />
            </c:otherwise>
        </c:choose>
        ${joinedGroups}
    </form>

</body>
</html>
