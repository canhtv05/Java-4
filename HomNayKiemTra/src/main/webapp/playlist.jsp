<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/28/2024
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/song-management/add" method="post">
    ten bai hat: <input type="text" name="ten"> <br>
    thoi luong: <input type="text" name="thoiLuong"> <br>
    gia: <input type="text" name="gia"> <br>
    ngay ra mat: <input type="date" name="ngayRaMat"> <br>
    ten ca si:
    <select name="caSi">
        <c:forEach items="${cs}" var="c">
            <option value="${c.id}" label="${c.ten}"></option>
        </c:forEach>
    </select> <br>
    <button type="submit">Add</button>
</form>

<a href="/song-management/playlists">home view all</a> <br>

<a href="/song-management/playlists/paging?page=${pageNo > 1 ? pageNo - 1 : 1}">prev</a>
<a href="/song-management/playlists/paging?page=${pageNo < total + 1 ? pageNo + 1 : total + 1}">next</a>
<table border="1" style="border-collapse: collapse">
    <tr>
        <th>#</th>
        <th>ten bai hat</th>
        <th>gia</th>
        <th>thoi luong</th>
        <th>ngay ra mat</th>
        <th>ten ca si</th>
        <th>tuoi</th>
        <th>hanh dong</th>
    </tr>
    <c:forEach items="${bh}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.ten}</td>
            <td>${b.gia}</td>
            <td>${b.thoiLuong}</td>
            <td>${b.ngayRaMat}</td>
            <td>${b.caSi.ten}</td>
            <td>${b.caSi.tuoi}</td>
            <td><a href="/song-management/detail?id=${b.id}">Detail</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
