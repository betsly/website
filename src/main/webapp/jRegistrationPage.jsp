<%-- 
    Document   : jRegistrationPage
    Created on : 12.11.2020, 14:42:18
    Author     : zmugg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrieren</title>
    </head>
    <body>
        <form action="./BetslyServlet" method="POST">
        
        <h1>Registrieren</h1>
        <center>
        Username
        </br>
        <input type="text" name="username" value="" placeholder="Username" />
        </br> </br>
        Email
        </br>
        <input type="text" name="email" value="" placeholder="betsly@gmail.com" />
        </br> </br>
        Passwort
        </br>
        <input type="password" name="passwort" value="" placeholder="Passwort" />
        </br> </br>
        <input type="password" name="confirm password" value="" placeholder="Passwort bestätigen"/>
        </center>
        <input type="submit" value="zurück" name="back" />
        <input type="submit" value="bestätigen" name="bestätigen"/>
        </form>
        </body>
</html>
