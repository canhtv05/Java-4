<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/14/2024
  Time: 1:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add new</h2>
    <form action="/hdct/add" method="post">
        tenSP: <input type="text" name="ten" required> <br>
        soluong: <input type="text" name="soLuong" required> <br>
        gia: <input type="text" name="gia" required> <br>
        ghi chu: <input type="text" name="ghiChu" required> <br>
        ten khach hang:
        <select name="hoaDon">
            <c:forEach items="${hd}" var="h">
                <option value="${h.id}" label="${h.tenKhachHang}"></option>
            </c:forEach>
        </select>
        <br>
        <button type="submit">Add</button>
    </form>

    <br>
    <h2>list</h2>
<table border="1" style="border-collapse: collapse">
    <tr>
        <th>ID</th>
        <th>ten SP</th>
        <th>so luong</th>
        <th>gia</th>
        <th>ghi chu</th>
        <th>ten kh</th>
        <th colspan="2">hanh dong</th>
    </tr>
    <c:forEach items="${hdct}" var="hd">
        <tr>
            <td>${hd.id}</td>
            <td>${hd.tenSanPham}</td>
            <td>${hd.soLuong}</td>
            <td>${hd.gia}</td>
            <td>${hd.ghiChu}</td>
            <td>${hd.hoaDon.tenKhachHang}</td>
            <td><a href="/hdct/delete?id=${hd.id}">Delete</a></td>
            <td><a href="/hdct/view-update?id=${hd.id}">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
