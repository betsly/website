<%-- 
    Document   : homepage
    Created on : 20.05.2021, 13:38:09
    Author     : zmugg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="homepage.css" rel="stylesheet" type="text/css"/>
        <title>Betsly</title>
    </head>
    <body style="background-color: #404040">
        <form action="./BetslyServlet" method="POST">
            <img id="picture" src="Group_7.png"/>
            <p id="title">Wilkommen bei Betsly!<p>
            <a id="description">Betsly garantiert großen Wettspaß!</a>
            <div class="divbuttons"><input class="bt" type="submit" value="Registrieren" name="registration"/></di>
            <div class="divbuttons"><input class="bt" type="submit" value="Login" name="login" /></div>
        </form>
    </body>
</html>
