<%-- 
    Document   : jHomePage
    Created on : 21.01.2021, 12:28:37
    Author     : zmugg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="./BetslyServlet" method="POST">
        
        <center>
            <h1 class="ü1">Home</h1>
            
      
        
           
            <input type="submit" value="neue Gruppe erstellen" name="createNewGroup"/></br>
            <input type="submit" value="Gruppe beitreten" name="joinGroup"/></br>
            <input type="submit" value="zurück" name="back" /></br>
        
        </form>
        </center>
    </body>
</html>
