<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/4/2024
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/phongKham/add" method="post">
    <label for="ten">
        ten
    </label><br>
    <input type="text" required id="ten" name="ten">
    <br>
    <label for="soNha">
        soNha
    </label><br>
    <input type="number" required id="soNha" name="soNha">
    <br>
    <button type="submit">Add</button>
</form>
</body>
</html>
