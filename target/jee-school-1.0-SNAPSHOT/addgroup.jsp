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
    <title>Dodaj grupę</title>
</head>
<body>
<%@ include file="WEB-INF/fragments/header.jspf" %>
<h1>Dodaj grupę</h1>
<form method="post" action="manageGroupUsers">
    <label> Nazwa grupy:
        <input type="text" name="newGroupName"  placeholder="${currentGroup.getName()}">
    </label>
    <button type="submit">Zapisz</button>
</form>
</body>
<%@ include file="WEB-INF/fragments/footer.jspf" %>
</html>
