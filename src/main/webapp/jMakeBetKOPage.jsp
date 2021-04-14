<%-- 
    Document   : jMakeBetPage
    Created on : 14.04.2021, 20:05:11
    Author     : nico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Bet</title>
    </head>
    <body>
        <form action="./BetslyServlet" method="POST">
            Winner: <select name="tip">
                <option value="${bet.country1.id}">${bet.country1.name}</option>
                <option value="${bet.country2.id}">${bet.country2.name}</option>
            </select>
            Score: <input type="text" name="score" value="" placeholder="0:0"/> 
            <input type="submit" value="make bet" name="makeBetDBKO" />
        </form> 
    </body>
</html>
