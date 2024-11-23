<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/21/2024
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message} <br>
<form action="/demo-filter/login" method="post">
    ten dang nhap: <input type="text" name="username"> <br>
    mat khau: <input type="text" name="password"> <br>
    <button type="submit">Login</button>
</form>
</body>
</html>
