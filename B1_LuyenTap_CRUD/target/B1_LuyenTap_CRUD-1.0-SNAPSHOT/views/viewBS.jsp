<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/12/2024
  Time: 12:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add new bac si</h2>
<h2><a href="/pk/view">View phog kham</a></h2>
<form action="/bs/add" method="post">
    ten: <input type="text" required name="ten"> <br>
    dia chi: <input type="text" required name="diaChi"> <br>
    luong: <input type="text" required name="luong"> <br>
    id phong kham:
    <select name="phongKham">
        <c:forEach items="${pk}" var="p">
            <option value="${p.id}">${p.id}</option>
        </c:forEach>
    </select>
    <button type="submit">add</button>
</form>

<table border="1" style="border-collapse: collapse">
    <tr>
        <th>Id</th>
        <th>ten</th>
        <th>dia chi</th>
        <th>luong</th>
        <th>id phog kham</th>
        <th colspan="2">hanh dong</th>
    </tr>

    <c:forEach items="${bs}" var="l">
        <tr>
            <td>${l.id}</td>
            <td>${l.ten}</td>
            <td>${l.diaChi}</td>
            <td>${l.luong}</td>
            <td>${l.phongKham.id}</td>
            <td><a href="/bs/delete?id=${l.id}">Delete</a></td>
            <td><a href="/bs/view-update?id=${l.id}">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
