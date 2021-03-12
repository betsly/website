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
        <script src="betslyScript.js" type="text/javascript"></script>
    </head>
    <body>
        
        <form  action="./BetslyServlet" method="POST" onsubmit="return validateCreateGroup()">
            <input type="text" name="createGroupName" id="createGroupName" value="" placeholder="Name"/>
            <input type="text" name="createGroupPassword" id="createGroupPassword" value="" placeholder="Password"/>
            <input type="text" name="createGroupDes" id="createGroupDes" value="" placeholder="Beschreibung"/>
            <input type="submit" value="Gruppe erstellen" name="createGroup" />
            <input type="submit" value="Zurück" name="backCreate" />
        </form>
    </body>
</html>
