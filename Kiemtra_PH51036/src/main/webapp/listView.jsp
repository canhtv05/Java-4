<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button><a href="/nv/view">Refresh</a></button>
<h2>Add new Nhan vien</h2>
<form action="/nv/add" method="post">
    ten: <input type="text" name="ten" required> <br>
    tuoi: <input type="text" name="tuoi" required> <br>
    luong: <input type="text" name="luong" required> <br>
    ten phong ban:
    <select name="phongBan">
        <c:forEach items="${pb}" var="p">
            <option value="${p.id}" label="${p.ten}"></option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Add</button>
</form>

<h2>List</h2>
<table border="1" style="border-collapse: collapse">
    <tr>
        <th>ID</th>
        <th>Ten</th>
        <th>Tuoi</th>
        <th>Luong</th>
        <th>Ten phong ban</th>
        <th colspan="2">Hanh dong</th>
    </tr>
    <c:forEach items="${nv}" var="n">
        <tr>
            <td>${n.id}</td>
            <td>${n.ten}</td>
            <td>${n.tuoi}</td>
            <td>${n.luong}</td>
            <td>${n.phongBan.ten}</td>
            <td><a href="/nv/delete?id=${n.id}">Delete</a></td>
            <td><a href="/nv/view-update?id=${n.id}">Update</a></td>
        </tr>
    </c:forEach>
    <button><a href="/nv/view/paging?page=${pageNo - 1 < 1 ? 1: pageNo - 1}">Prev</a></button>
    <button><a href="/nv/view/paging?page=${pageNo + 1}">Next</a></button>
</table>
</body>
</html>
