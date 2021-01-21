<%-- 
    Document   : jCreateGroupPage
    Created on : 21.01.2021, 12:15:22
    Author     : zmugg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CreateGroupPage</title>
    </head>
    <body>
        <form action="./BetslyServlet" method="POST">
        
        <center>
            <h1 class="ü1">Neue Gruppe erstellen</h1>
      
        Email
        </br>
        <input class="input" type="text" name="email" value="" placeholder="betsly@gmail.com" />
        </br> </br>
        Passwort
        </br>
        <input class="input" type="password" name="password" value="" placeholder="Passwort" />
        </br> </br>
        Name der Gruppe
        </br>
        <input class="input" type="text" name="text" value="" placeholder="gruppe1" />
        </br> </br>
        <input type="submit" value="zurück" name="back" />
        <input type="submit" value="erstellen" name="createGroup"/>
        </form>
        </center>
        
    </body>
</html>
