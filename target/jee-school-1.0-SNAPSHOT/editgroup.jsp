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
    <title>Edytuj grupę</title>
</head>
<body>
<%@ include file="WEB-INF/fragments/header.jspf" %>
<h1>Edytuj grupę</h1>
<form method="post" action="manageGroupUsers">
    <label> Nazwa grupy:
        <input type="text" name="newGroupName"  placeholder="${currentGroup.getName()}">
    </label>
    <input name="groupid" hidden value="${currentGroup.getId()}"/>

            <button type="submit">Zapisz</button>
</form>
</body>
<%@ include file="WEB-INF/fragments/footer.jspf" %>
</html>
