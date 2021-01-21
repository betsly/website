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
        
        <form  action="./BetslyServlet" method="POST">
            <input type="text" name="createGroupName" value="" placeholder="Name"/>
            <input type="text" name="createGroupDes" value="" placeholder="Beschreibung"/>
            <input type="submit" value="Gruppe erstellen" name="createGroup" />
            <input type="submit" value="Zurück" name="backCreate" />
        </form>
    </body>
</html>
