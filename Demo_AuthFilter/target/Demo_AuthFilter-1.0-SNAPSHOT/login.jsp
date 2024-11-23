<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/23/2024
  Time: 12:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1> <br>
<p>${msg}</p> <br>
<form action="/login" method="post">
    username: <input type="text" name="username"> <br>
    password: <input type="text" name="password"> <br>
    <button type="submit">Login</button>
</form>
</body>
</html>
