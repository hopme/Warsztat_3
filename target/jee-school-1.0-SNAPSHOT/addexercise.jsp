<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 10.07.19
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj zadanie</title>
</head>
<body>
<%@ include file="WEB-INF/fragments/header.jspf" %>

<h1>Dodaj zadanie</h1>
<form method="post" action="manageExercises">
    <label> Tytuł zadania:
        <input type="text" name="newtitle"  placeholder="${currentExercise.getTitle()}">
    </label>
    <label> Opis zadania:
        <textarea name="newdescription" rows="10" required>${currentExercise.getDescription()}</textarea>
    </label>
    <button type="submit">Zapisz</button>
</form>
<%@ include file="WEB-INF/fragments/footer.jspf" %>
</body>
</html>
