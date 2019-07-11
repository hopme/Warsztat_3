<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 01.07.19
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>


<%@ include file="WEB-INF/fragments/header.jspf" %>

<%--<h1>Lista użytkowników grupy: ${usersInGroup.getGroupName()}</h1>--%>

<h2>Nazwa grupy: ${currentGroup.getName()} </h2>


<table>
    <thead>
    <tr>
        <th>Nazwa użytkownika</th>
        <th>Akcje:</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${usersInGroup}" var="usersInGroup" >
        <tr>
            <td>${usersInGroup.getUserName()}</td>
            <td><a href="<c:url value="/groupUsersDetails?userid=${usersInGroup.getId()}"/>">Szczegóły</a></td>

        </tr>
    </c:forEach>
    </tbody>
</table>


<%@ include file="WEB-INF/fragments/footer.jspf" %>

</body>
</html>
