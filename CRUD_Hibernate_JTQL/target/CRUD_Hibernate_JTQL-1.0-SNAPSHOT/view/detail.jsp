<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/4/2024
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<label for="id">
    id
    <input type="text" readonly id="id" name="id" value="${pk.id}">
</label>
<br>
<label for="ten">
    ten
    <input type="text" readonly id="ten" name="ten" value="${pk.ten}">
</label>
<br>
<label for="soNha">
    soNha
    <input type="number" readonly id="soNha" name="soNha" value="${pk.soNha}">
</label>
</body>
</html>
