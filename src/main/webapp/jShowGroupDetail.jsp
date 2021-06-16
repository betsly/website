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
        <link href="styleShowGroupDetail.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body style="background-color: rgba(43,49,52,1)">
        <form action="./BetslyServlet" method="post">
            <img id="default-user-image" src="default-user-image.png" srcset="default-user-image.png 1x, default-user-image@2x.png 2x">
            <div id="Group_8">
                <img id="Group_7" src="Group_7.png" srcset="Group_7.png 1x, Group_7@2x.png 2x">
                </svg>
            </div>
            <p class="title">${currentGroup.name}</p>
            <div class="content">
                <div id="outerDiv">
                    <div class="split-screen">
                        <div id="divRanking">
                            <div id="innerDivRanking">
                                <p id="ranking">Ranking</p>
                                <ol>
                                    <c:set var="counter" value="1"></c:set>
                                    <c:forEach var="rank" items="${ranks}">
                                        <c:if test="${counter < 10}">
                                            <p class="rankingItem">${counter}.Platz: ${rank.username} - ${rank.points} Punkte</p>
                                            <c:set var="counter" value="${counter+1}"></c:set>
                                        </c:if> 
                                    </c:forEach>
                                </ol>
                            </div>
                        </div>
                        <div id="divbt">
                            <c:if test="${userID.equals(currentGroup.host)}">
                                <button  class="bt" type="submit" value="${currentGroup.id}" name="createBetForm" >Neue Wette erstellen</button>
                                <br>
                            </c:if>
                        </div>
                        <div id="alleWettenDiv">
                            <div id="InnerDivWetten"><p id="wetten">Alle Wetten</p>
                                <c:if test="${betListGroup != null || !betListGroup.isEmpty()}">
                                    <c:forEach var="bet" items="${betListGroup}">
                                        <c:if test="${bet.closed != null && !bet.closed}">
                                            <p class="wette">
                                                ${bet.name} <button class="makeBet" type="submit" value="makeBet: ${bet.id} ${currentGroup.id}" name="makeBetGroup">wetten</button>
                                                <c:if test="${userID.equals(currentGroup.host)}">
                                                    <button class="makeBet" type="submit" value="${bet.id}" name="closeGroupBet">Wette schließen</button>
                                                </c:if>
                                            </p>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${betListKo != null || !betListKo.isEmpty()}">
                                    <c:forEach var="bet" items="${betListKo}">
                                        <c:if test="${bet.closed != null && !bet.closed}">
                                            <p class="wette"> 
                                                ${bet.name} <button class="makeBet" type="submit" value="makeBet: ${bet.betId} ${currentGroup.id}" name="makeBetKO">wetten</button>
                                                <c:if test="${userID.equals(currentGroup.host)}">
                                                    <button class="makeBet" type="submit" value="${bet.betId}" name="closeKoBet">Wette schließen</button> 
                                                </c:if>
                                            </p>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div></div>
            </div>           
            <button class="back" type="submit" value="zurück" name="back" onclick="window.history.back()">zurück</button>
        </form>
    </body>
</html>
