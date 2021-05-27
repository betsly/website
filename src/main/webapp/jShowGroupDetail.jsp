<%-- 
    Document   : jShowBets
    Created on : 27.05.2021, 09:30:30
    Author     : zmugg
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="./BetslyServlet" method="post">
            <ol>
                <c:set var="counter" value="1"></c:set>
                <c:forEach var="rank" items="${ranks}">
                    <c:if test="${counter < 10}">
                        <li class="text"><p>${rank.username}: ${rank.points} </p></li>
                            <c:set var="counter" value="${counter+1}"></c:set>
                        </c:if> 
                    </c:forEach>
            </ol>

            <c:if test="${userID.equals(currentGroup.host)}">
                Neue Wette erstellen
                <button  class="bt1" type="submit" value="${currentGroup.id}" name="createBetForm" >create Bet</button>
                <br>
            </c:if>

            <c:if test="${betListGroup != null || !betListGroup.isEmpty()}">
                Group Phase Bets
                <c:forEach var="bet" items="${betListGroup}">
                    <c:if test="${bet.closed != null && !bet.closed}">
                        <p>Bet ${bet.id}: 
                            ${bet.name} <button type="submit" value="makeBet: ${bet.id} ${currentGroup.id}" name="makeBetGroup">make Bet</button>
                        </p>
                        <c:if test="${userID.equals(currentGroup.host)}">
                            1. <select name="c1">
                                <option value="${bet.country1.id}">${bet.country1.name}</option>
                                <option value="${bet.country2.id}">${bet.country2.name}</option>
                                <option value="${bet.country3.id}">${bet.country3.name}</option>
                                <option value="${bet.country4.id}">${bet.country4.name}</option>
                            </select>
                            2. <select name="c2">
                                <option value="${bet.country2.id}">${bet.country2.name}</option>
                                <option value="${bet.country1.id}">${bet.country1.name}</option>
                                <option value="${bet.country3.id}">${bet.country3.name}</option>
                                <option value="${bet.country4.id}">${bet.country4.name}</option>
                            </select>
                            3. <select name="c3">
                                <option value="${bet.country3.id}">${bet.country3.name}</option>
                                <option value="${bet.country1.id}">${bet.country1.name}</option>
                                <option value="${bet.country2.id}">${bet.country2.name}</option>
                                <option value="${bet.country4.id}">${bet.country4.name}</option>
                            </select>
                            4. <select name="c4">
                                <option value="${bet.country4.id}">${bet.country4.name}</option>
                                <option value="${bet.country1.id}">${bet.country1.name}</option>
                                <option value="${bet.country2.id}">${bet.country2.name}</option>
                                <option value="${bet.country3.id}">${bet.country3.name}</option>
                            </select>
                            <button type="submit" value="${bet.id} ${currentGroup.id}" name="closeBetGroup" >close Bet</button>
                        </c:if>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${betListKo != null || !betListKo.isEmpty()}">
                Ko Phase Bets
                <c:forEach var="bet" items="${betListKo}">
                    <c:if test="${bet.closed != null && !bet.closed}">
                        <p>Bet ${bet.betId}: 
                            ${bet.name} <button type="submit" value="makeBet: ${bet.betId} ${currentGroup.id}" name="makeBetKO">make Bet</button>
                        </p>
                        <c:if test="${userID.equals(currentGroup.host)}">
                            1. <select name="winner">
                                <option value="${bet.country1.id}">${bet.country1.name}</option>
                                <option value="${bet.country2.id}">${bet.country2.name}</option>
                            </select>
                            <p>Score:</p> <input class="input" type="text" name="scoreClose" value="" placeholder="0:0"/>
                            <button type="submit" value="${bet.betId} ${currentGroup.id}" name="closeBetKO" >close Bet</button>
                        </c:if>
                    </c:if>
                </c:forEach>
            </c:if>
        </form>

    </body>
</html>
