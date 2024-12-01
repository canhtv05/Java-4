<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/26/2024
  Time: 1:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dang nhap</title>
</head>
<body>
<span>${msg}</span> <br>
<form action="/login" method="post">
    tai khoan: <input type="text" name="taiKhoan" required> <br>
    mat khau: <input type="text" name="matKhau" required> <br>
    <button type="submit">Dang nhap</button>
</form>
</body>
</html>
