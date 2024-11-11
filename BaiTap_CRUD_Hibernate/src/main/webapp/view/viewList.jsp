<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/5/2024
  Time: 1:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/danh-muc/view-add">Add</a>
<table border="1" style="border-collapse: collapse">
    <thead>
    <tr>
        <td>id</td>
        <td>ten</td>
        <td>soLuong</td>
        <td colspan="3">Hanh dong</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="l">
        <tr>
            <td>${l.id}</td>
            <td>${l.ten}</td>
            <td>${l.soLuong}</td>
            <td><a href="/danh-muc/delete?id=${l.id}">Delete</a></td>
            <td><a href="/danh-muc/view-update?id=${l.id}">Update</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
