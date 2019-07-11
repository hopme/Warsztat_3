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



<h2>Tytuł zadania: ${currentExercise.getTitle()} </h2>
<h3>Szczegóły rozwiązania:</h3>
<p>${currentExercise.getDescription()}</p>

<%@ include file="WEB-INF/fragments/footer.jspf" %>

</body>
</html>
