<%-- 
    Document   : Start
    Created on : 12.11.2020, 14:05:45
    Author     : zmugg
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Betsly</title>
    <a>Betsly garantiert großen Wettspaß!!</a>

</head>
<body>
    <form  action="./BetslyServlet" method="POST">
        <c:choose>
            <c:when test="${jwtUser == null}">
                <input type="submit" value="Registrieren" name="registration"/>
                <input type="submit" value="Login" name="login" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="show groups" name="showGroups" />
                <input type="submit" value="create group" name="createGroupForm" />
                <input type="submit" value="join group" name="joinGroupForm" />
            </c:otherwise>
        </c:choose>
        ${exe}
        <c:if test="${joinedGroups != null}">
            <h1>Joined Groups</h1>
            <ul id="groups">
                <c:forEach var="group" items="${joinedGroups}">
                    <li>
                        <div class="groupName">
                            <h3>${group.id}: ${group.name}</h3>
                        </div>
                        <details>
                            <p>${group.description}</p>
                            <p>Punkte: ${group.points}</p>
                            <p>Host: ${group.host}</p>
                            <c:forEach var="entry" items="${betMapGroupPhase}">
                                <c:if test="${entry.key == group.id && entry.value.size() > 0}">
                                    Group Phase Bets
                                    <c:forEach var="bet" items="${entry.value}">
                                        <c:if test="${bet.closed != null && !bet.closed}">
                                            <p>Bet ${bet.id}: 
                                                ${bet.name} <button type="submit" value="makeBet: ${bet.id} ${group.id}" name="makeBetGroup">make Bet</button>
                                            </p>
                                            <c:if test="${jwtUser.equals(group.host)}">
                                                1. <select name="1">
                                                    <option value="${bet.country1.id}">${bet.country1.name}</option>
                                                    <option value="${bet.country2.id}">${bet.country2.name}</option>
                                                    <option value="${bet.country3.id}">${bet.country3.name}</option>
                                                    <option value="${bet.country4.id}">${bet.country4.name}</option>
                                                </select>
                                                2. <select name="2">
                                                    <option value="${bet.country2.id}">${bet.country2.name}</option>
                                                    <option value="${bet.country1.id}">${bet.country1.name}</option>
                                                    <option value="${bet.country3.id}">${bet.country3.name}</option>
                                                    <option value="${bet.country4.id}">${bet.country4.name}</option>
                                                </select>
                                                3. <select name="3">
                                                    <option value="${bet.country3.id}">${bet.country3.name}</option>
                                                    <option value="${bet.country1.id}">${bet.country1.name}</option>
                                                    <option value="${bet.country2.id}">${bet.country2.name}</option>
                                                    <option value="${bet.country4.id}">${bet.country4.name}</option>
                                                </select>
                                                4. <select name="4">
                                                    <option value="${bet.country4.id}">${bet.country4.name}</option>
                                                    <option value="${bet.country1.id}">${bet.country1.name}</option>
                                                    <option value="${bet.country2.id}">${bet.country2.name}</option>
                                                    <option value="${bet.country3.id}">${bet.country3.name}</option>
                                                </select>
                                                <button type="submit" value="${bet.id} ${group.id}" name="closeBetGroup" >close Bet</button>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                            <c:forEach var="entry" items="${betMapKOPhase}">
                                <c:if test="${entry.key == group.id && entry.value.size() > 0}">
                                    Ko Phase Bets
                                    <c:forEach var="bet" items="${entry.value}">
                                        <c:if test="${bet.closed != null && !bet.closed}">
                                            <p>Bet ${bet.betId}: 
                                                ${bet.name} <button type="submit" value="makeBet: ${bet.betId} ${group.id}" name="makeBetKO">make Bet</button>
                                            </p>
                                            <c:if test="${jwtUser.equals(group.host)}">
                                                Gewinner: <select name="winner">
                                                    <option value="${bet.country1.id}">${bet.country1.name}</option>
                                                    <option value="${bet.country2.id}">${bet.country2.name}</option>
                                                </select>
                                                Endergebnis: <input type="text" name="endscore" value="" placeholder="0:0"/>
                                                <button type="submit" value="${bet.betId} ${group.id}" name="closeBetKO" >close Bet</button>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </details>
                        <c:if test="${jwtUser.equals(group.host)}">
                            <input type="submit" value="create Bet${group.id}" name="createBetForm" />
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </form>
</body>
</html>
