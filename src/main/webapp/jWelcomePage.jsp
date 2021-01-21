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
    </head>
    <body>
        <h1>Willkommen bei Betsly <c:if test="${jwtUser != null}">${jwtUser}</c:if></h1>     

        <c:if test="${createGroupError == true}">
            Bitte zuerst Anmelden !!
        </c:if>
        <c:if test="${joinGroupError == true}">
            Bitte zuerst Anmelden !!
        </c:if>
        <c:if test="${databaseError == true}">
            Datenbankfehler !!
        </c:if>

        <form  action="./BetslyServlet" method="POST">
            <input type="submit" value="Registrieren" name="registration"/>
            <input type="submit" value="Login" name="login" />
            <input type="submit" value="Gruppe erstellen" name="createGroupForm" />
            <input type="submit" value="Abmelden" name="logout" />
            <input type="submit" value="Gruppe beitreten" name="joinGroupForm" />
            <input type="submit" value="anzeigen Gruppen" name="showGroups" />
            
        <c:if test="${joinedGroups != null}">
            <c:forEach var="group" items="${joinedGroups}">
                <br>${group}
            </c:forEach>
        </c:if>
            
        </form>
    </body>
</html>
