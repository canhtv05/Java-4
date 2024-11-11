<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/7/2024
  Time: 1:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/xe-may/view-add">Add</a>
<table style="border-collapse: collapse" border="1">
    <tr>
        <th>Id</th>
        <th>Ten</th>
        <th>Gia</th>
        <th colspan="3">Hanh dong</th>
    </tr>
    <c:forEach items="${list}" var="l">
        <tr>
            <td>${l.id}</td>
            <td>${l.ten}</td>
            <td>${l.gia}</td>
            <td><a href="/xe-may/delete?id=${l.id}">Delete</a></td>
            <td><a href="/xe-may/view-update?id=${l.id}">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
