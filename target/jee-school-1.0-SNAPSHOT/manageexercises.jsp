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

<a href="<c:url value="/manageExercises?action=add"/>">Dodaj</a>
<table>
    <thead>
    <tr>
        <th>Nazwa zadania:</th>
        <th>Akcje:</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${allExercises}" var="allExercises" >
        <tr>
            <td>${allExercises.getTitle()}</td>
            <td>
                <a href="<c:url value="/manageExercises?action=edit&exid=${allExercises.getId()}"/>">Edytuj</a>
                <a href="<c:url value="/manageExercises?action=delete&exid=${allExercises.getId()}"/>">Usuń</a>

            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="WEB-INF/fragments/footer.jspf" %>

</body>
</html>
