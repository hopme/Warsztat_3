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

<a href="<c:url value="/manageGroupUsers?action=add"/>">Dodaj</a>
<table>
    <thead>
    <tr>
        <th>Nazwa grupy:</th>
        <th>Akcje:</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${allGroups}" var="allGroups" >
        <tr>
            <td>${allGroups.getName()}</td>
            <td>
                <a href="<c:url value="/manageGroupUsers?action=edit&groupid=${allGroups.getId()}"/>">Edytuj</a>
                <a href="<c:url value="/manageGroupUsers?action=delete&groupid=${allGroups.getId()}"/>">Usuń</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="WEB-INF/fragments/footer.jspf" %>

</body>
</html>
