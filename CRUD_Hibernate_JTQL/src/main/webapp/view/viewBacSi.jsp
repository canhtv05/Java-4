<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/9/2024
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/phongKham/view">Phong kham</a>

<h2>Them bac si</h2>
<form action="/bac-si/add" method="post">
    ten <input type="text" name="ten" > <br>
    dia chi <input type="text" name="diaChi"> <br>
    luong <input type="text" name="luong"> <br>
    id phong kham
    <select name="phongKham">
        <c:forEach items="${pk}" var="p">
            <option value="${p.id}">${p.id}</option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Add</button>
</form>

<h2>bang bac si</h2>
<table border="1" style="border-collapse: collapse">
    <tr>
        <th>id</th>
        <th>ten</th>
        <th>dia chi</th>
        <th>luong</th>
        <th>id_phong kham</th>
        <th>so nha phong kham</th>
        <th colspan="2">actions</th>
    </tr>
    <c:forEach items="${bs}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.ten}</td>
            <td>${b.diaChi}</td>
            <td>${b.luong}</td>
            <td>${b.pk.id}</td>
            <td>${b.pk.soNha}</td>
            <td><a href="/bac-si/delete?id=${b.id}">Delete</a></td>
            <td><a href="/bac-si/view-update?id=${b.id}">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
