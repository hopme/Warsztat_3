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
    <title>Edytuj użytkownika</title>
</head>
<body>
<%@ include file="WEB-INF/fragments/header.jspf" %>
<h1>Dodaj użytkownika</h1>
<form method="post" action="manageUsers">
    <label> Nazwa użytkownika:
        <input type="text" name="newUserName"  placeholder="${currentUser.getUserName()}">
    </label>
    <label> Email użytkownika:
        <input type="text" name="newEmail"  placeholder="${currentUser.getEmail()}">
    </label>
    <label> Hasło użytkownika:
        <input type="text" name="newPassword"  placeholder="${currentUser.getPassword()}">
    </label>
    <button type="submit">Zapisz</button>
</form>
<%@ include file="WEB-INF/fragments/footer.jspf" %>
</body>
</html>
