<%-- 
    Document   : jCreateGroupPage
    Created on : 21.01.2021, 12:36:32
    Author     : nico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gruppe erstellen</title>
    </head>
    <body>
        <c:if test="${createGroupError == true}">
            Name nicht gefunden oder noch nicht Angemeldet!
        </c:if>
        <form  action="./BetslyServlet" method="POST">
            <input type="text" name="joinGroupName" value="" placeholder="Name"/>
            <input type="text" name="joinGroupPW" value="" placeholder="Password"/>
            <input type="submit" value="Gruppe beitreten" name="joinGroup" />
            <input type="submit" value="Zurück" name="backJoin" />
        </form>
    </body>
</html>
