<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/12/2024
  Time: 12:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Update phong kham</h2>
<form action="/pk/update" method="post">
    <input type="hidden" name="id" value="${pk.id}">
    ten: <input type="text" required name="ten" value="${pk.ten}"> <br>
    so nha: <input type="text" required name="soNha"  value="${pk.soNha}"> <br>
    <button type="submit">Update</button>
</form>
</body>
</html>
