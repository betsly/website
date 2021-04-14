<%-- 
    Document   : jMakeBetGroupPage
    Created on : 14.04.2021, 20:51:56
    Author     : nico
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
            First:<select name="first">
                <option value="${bet.country1.id}">${bet.country1.name}</option>
                <option value="${bet.country2.id}">${bet.country2.name}</option>
                <option value="${bet.country3.id}">${bet.country3.name}</option>
                <option value="${bet.country4.id}">${bet.country4.name}</option>
            </select>
            Second:<select name="second">
                <option value="${bet.country1.id}">${bet.country1.name}</option>
                <option value="${bet.country2.id}">${bet.country2.name}</option>
                <option value="${bet.country3.id}">${bet.country3.name}</option>
                <option value="${bet.country4.id}">${bet.country4.name}</option>
            </select>
            Third:<select name="third">
                <option value="${bet.country1.id}">${bet.country1.name}</option>
                <option value="${bet.country2.id}">${bet.country2.name}</option>
                <option value="${bet.country3.id}">${bet.country3.name}</option>
                <option value="${bet.country4.id}">${bet.country4.name}</option>
            </select>
            Fourth:<select name="fourth">
                <option value="${bet.country1.id}">${bet.country1.name}</option>
                <option value="${bet.country2.id}">${bet.country2.name}</option>
                <option value="${bet.country3.id}">${bet.country3.name}</option>
                <option value="${bet.country4.id}">${bet.country4.name}</option>
            </select>
            <input type="submit" value="make bet" name="makeBetDBGroup" />
        </form> 
    </body>
</html>
