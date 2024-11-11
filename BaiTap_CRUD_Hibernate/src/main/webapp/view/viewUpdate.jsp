<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/5/2024
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/danh-muc/update" method="post">
    <input type="hidden" value="${dm.id}" name="id">
    ten: <input type="text" name="ten" value="${dm.ten}"><br>
    so luong: <input type="number" name="soLuong" value="${dm.soLuong}"><br>
    <button type="submit">Update</button>
</form>
</body>
</html>
