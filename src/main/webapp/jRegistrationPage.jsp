<%-- 
    Document   : jRegistrationPage
    Created on : 12.11.2020, 14:42:18
    Author     : zmugg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="betslyScript.js" type="text/javascript"></script>
    <title>Registrieren</title>
    </head>
    <body style="background-color: #186C1D">
        <form action="./BetslyServlet" method="POST" onsubmit="return validate()">
        
        <center>
            <h1 class="ü1">REGISTRIEREN</h1>
      
        Username
        </br>
        <input class="input" type="text" name="username" value="" placeholder="Username" required>
        </br> </br>
        Email
        </br>
        <input class="input" type="text" name="email" value="" placeholder="betsly@gmail.com" required>
        </br> </br>
        Passwort
        </br>
        <input class="input" type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" value="" placeholder="Passwort" required>
        </br> </br>
        <input class="input" type="password" name="confirmation" value="" placeholder="Passwort bestätigen" required>
        </center>
        <input type="submit" value="zurück" name="back" />
<<<<<<< HEAD
        <input type="submit" value="bestätigen" name="confirm"/>    
=======
        <input type="submit" value="bestätigen" name="confirmRegistration"/>
>>>>>>> 991937cefc70704ba3579a33b094e4c7013a3517
        </form>
        
        
        </body>
</html>
