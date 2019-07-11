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

<a href="<c:url value="/manageUsers?action=add"/>">Dodaj</a>
<table>
    <thead>
    <tr>
        <th>Nazwa użytkownika:</th>
        <th>Akcje:</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${allUsers}" var="allUsers" >
        <tr>
            <td>${allUsers.getUserName()}</td>
            <td>
                <a href="<c:url value="/manageUsers?action=edit&userid=${allUsers.getId()}"/>">Edytuj</a>
                <a href="<c:url value="/manageUsers?action=delete&userid=${allUsers.getId()}"/>">Usuń</a>

            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="WEB-INF/fragments/footer.jspf" %>

</body>
</html>
