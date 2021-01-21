<%-- 
    Document   : jLoginPage
    Created on : 12.11.2020, 14:42:54
    Author     : zmugg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="./BetslyServlet" method="POST">
        
        <center>
            <h1 class="ü1">Login</h1>
      
        Email
        </br>
        <input class="input" type="text" name="email" value="" placeholder="betsly@gmail.com" />
        </br> </br>
        Passwort
        </br>
        <input class="input" type="password" name="password" value="" placeholder="Passwort" />
        </center>
        <input type="submit" value="zurück" name="backLogin" />
        <input type="submit" value="bestätigen" name="confirmLogin"/>
        </form>
        </body>
    </body>
</html>
