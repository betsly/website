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
        <form action="./BetslyServlet" method="POST">
            <select name="betType" onchange="submit()">
                <option value="group" ${betType.equals('group') ? 'selected' : ''}>Group Phase</option>
                <option value="ko" ${betType.equals('ko') ? 'selected' : ''}>KO Phase</option>
            </select>
            <br>
            <datalist id="countries_id">
                <c:forEach var="country" items="${countries}">
                    <option value="${country.name}"/>
                </c:forEach>
            </datalist>
            <input type="text" name="betName" placeholder="Name" value="name"/> 
            <input type="text" name="country1" value="" placeholder="Country1" list="countries_id"/> 
            <input type="text" name="country2" value="" placeholder="Country2" list="countries_id"/> 

            <c:if test="${betType.equals('group')}">
                <input type="text" name="country3" value="" placeholder="Country3" list="countries_id"/> 
                <input type="text" name="country4" value="" placeholder="Country4" list="countries_id"/> 
            </c:if>
            <input type="submit" value="einreichen" name="createBetDB" />
            <input type="submit" value="Abbrechen" name="back" />
        </form>

    </body>
</html>
