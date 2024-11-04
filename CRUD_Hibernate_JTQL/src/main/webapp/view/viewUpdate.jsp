<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/4/2024
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/phongKham/update" method="post">
    <input type="hidden" name="id" value="${pk.id}">
    <label for="ten">
        ten
        <input type="text" required id="ten" name="ten" value="${pk.ten}">
    </label>
    <br>
    <label for="soNha">
        soNha
        <input type="number" required id="soNha" name="soNha" value="${pk.soNha}">
    </label>
    <br>
    <button type="submit">Update</button>
</form>
</body>
</html>
