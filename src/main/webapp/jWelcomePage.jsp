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
        <h1>Willkommen bei Betsly</h1>
        <c:forEach var="user" items="${dbTestung}">
            ${user.username}
            ${user.email}
            ${user.pw}
        </c:forEach>
        ${test1}
        <form  action="./BetslyServlet" method="POST">
            <input type="submit" value="Registrieren" name="registration"/>
            <input type="submit" value="Login" name="login" />
        </form>
<<<<<<< HEAD
        
     
=======


>>>>>>> main
    </body>
</html>
