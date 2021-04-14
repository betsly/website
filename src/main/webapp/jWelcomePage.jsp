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
                            <p>Host: ${group.host}</p>
                            <c:forEach var="entry" items="${betMapGroupPhase}">
                                <c:if test="${entry.key == group.id && entry.value.size() > 0}">
                                    Group Phase Bets
                                    <c:forEach var="bet" items="${entry.value}">
                                        <p>Bet ${bet.id}: 
                                            ${bet.name} <input type="submit" value="makeBet: ${bet.id} ${group.id}" name="makeBetGroup"/>
                                        </p>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                            <c:forEach var="entry" items="${betMapKOPhase}">
                                <c:if test="${entry.key == group.id && entry.value.size() > 0}">
                                    Ko Phase Bets
                                    <c:forEach var="bet" items="${entry.value}">
                                        <p>Bet ${bet.betId}: 
                                            ${bet.name} <input type="submit" value="makeBet: ${bet.betId} ${group.id}" name="makeBetKO"/>
                                        </p>
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
