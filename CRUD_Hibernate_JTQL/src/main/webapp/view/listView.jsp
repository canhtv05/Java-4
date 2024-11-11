<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/phongKham/search" method="get">
    ten: <input type="text" name="ten"> <br>
    <button type="submit">Search</button>
</form>

<a href="/phongKham/view-add">Add new</a>
<a href="/bac-si/hien-thi">Bac si list</a>
<table border="1" style="border-collapse: collapse">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Số Nhà</th>
        <th colspan="3">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="l">
        <tr>
            <td>${l.id}</td>
            <td>${l.ten}</td>
            <td>${l.soNha}</td>
            <td><a href="/phongKham/view-update?id=${l.id}">update</a></td>
            <td><a href="/phongKham/detail?id=${l.id}">detail</a></td>
            <td><a href="/phongKham/delete?id=${l.id}">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="/phongKham/paging?page=${pageNo > 1 ? pageNo - 1 : 1}">Prev</a><br>
<a href="/phongKham/paging?page=${pageNo + 1}">Next</a><br>

</body>
</html>
