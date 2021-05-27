<%-- 
    Document   : jShowBets
    Created on : 27.05.2021, 09:30:30
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
        <form action="./BetslyServlet" method="post">
             <h1>Hello World!</h1>
             <c:forEach var="entry" items="${betMapGroupPhase}">
                                    <c:if test="${entry.key == group.id && entry.value.size() > 0}">
                                        Group Phase Bets
                                        <c:forEach var="bet" items="${entry.value}">
                                            <c:if test="${bet.closed != null && !bet.closed}">
                                                <p>Bet ${bet.id}: 
                                                    ${bet.name} <button type="submit" value="makeBet: ${bet.id} ${group.id}" name="makeBetGroup">make Bet</button>
                                                </p>
                                                <c:if test="${jwtUser.equals(group.host)}">
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
                                                    <button type="submit" value="${bet.id} ${group.id}" name="closeBetGroup" >close Bet</button>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
        </form>
       
    </body>
</html>
