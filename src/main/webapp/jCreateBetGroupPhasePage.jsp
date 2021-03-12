<%-- 
    Document   : jCreateBetPage
    Created on : 12.03.2021, 18:08:51
    Author     : nico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Bet</title>
    </head>
    <body>
        <form action="./Controller" method="POST">

            <datalist id="countries_id">
                <c:forEach var="country" items="countries">
                    <option value="${country}">
                </c:forEach>
            </datalist>

            <input type="text" name="countryName" placeholder="Name" value="name"/> 
            <input type="text" name="country1" value="Country1" list="countries_id"/> 
            <input type="text" name="country2" value="Country2" list="countries_id"/> 
            <input type="text" name="country3" value="Country3" list="countries_id"/> 
            <input type="text" name="country4" value="Country4" list="countries_id"/> 

            <input type="submit" value="submit" name="createBetGroupPhase" />
        </form>

    </body>
</html>
