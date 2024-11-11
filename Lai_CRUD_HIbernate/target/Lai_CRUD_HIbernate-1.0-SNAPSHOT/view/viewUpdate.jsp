<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/7/2024
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/xe-may/update" method="post">
    <input type="hidden" value="${xm.id}" name="id"><br>

    ten: <input type="text" value="${xm.ten}" name="ten" required><br>
    gia: <input type="number" value="${xm.gia}" name="gia" required><br>
    <button type="submit">Update</button>
</form>
</body>
</html>
