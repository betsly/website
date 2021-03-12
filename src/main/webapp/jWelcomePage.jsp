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
            <c:if test="${jwtUser == null}">
                <input type="submit" value="Registrieren" name="registration"/>
                <input type="submit" value="Login" name="login" />
            </c:if>
        </form>
       
    </body>
</html>
