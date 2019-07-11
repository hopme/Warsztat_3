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

    <table>
        <thead>
        <tr>
            <th>Tytuł zadania</th>
            <th>Autor rozwiązania</th>
            <th>Data dodania</th>
            <th> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mainPageResults}" var="mainPageResults" >
        <tr>
            <td>${mainPageResults.getTitle()}</td>
            <td>${mainPageResults.getAuthor()}</td>
            <td>${mainPageResults.getUpdated()}</td>
            <td><a href="<c:url value="/exerciseDetails?id=${mainPageResults.getExId()}"/>"> Szczegóły </a></td>

        </tr>
        </c:forEach>
        </tbody>
    </table>

<%@ include file="WEB-INF/fragments/footer.jspf" %>

</body>
</html>
