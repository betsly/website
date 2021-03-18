<%-- 
    Document   : jSchowJoinedGroupsPage
    Created on : 17.03.2021, 17:46:05
    Author     : nico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Joined Groups</title>
    </head>
    <body>
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
                    </details>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
