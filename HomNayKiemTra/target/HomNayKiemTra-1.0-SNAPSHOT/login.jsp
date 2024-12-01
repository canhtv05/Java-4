<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/28/2024
  Time: 1:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span>${msg}</span> <br>
<form action="/login" method="post">
    tai khoan: <input type="text" name="tk"> <br>
    mat khau: <input type="text" name="mk"> <br>
    <button type="submit">Login</button>
</form>
</body>
</html>
