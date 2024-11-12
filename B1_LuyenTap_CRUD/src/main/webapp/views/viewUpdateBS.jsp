<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h2>Update bac si</h2>
<form action="/bs/update" method="post">
    <input type="hidden" name="id" value="${bs.id}">
    ten: <input type="text" required name="ten" value="${bs.ten}"> <br>
    dia chi: <input type="text" required name="diaChi"  value="${bs.diaChi}"> <br>
    luong: <input type="text" required name="luong"  value="${bs.luong}"> <br>
    id phong kham:
    <select name="phongKham">
        <c:forEach items="${pk}" var="p">
            <option value="${p.id}">${p.id}</option>
        </c:forEach>
    </select>
    <button type="submit">Update</button>
</form>
</body>
</html>
