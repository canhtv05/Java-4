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
<h2>Add new phong kham</h2>
<h2><a href="/bs/view">View bac si</a></h2>
<form action="/pk/add" method="post">
    ten: <input type="text" required name="ten"> <br>
    so nha: <input type="text" required name="soNha"> <br>
    <button type="submit">add</button>
</form>

<table border="1" style="border-collapse: collapse">
    <tr>
        <th>Id</th>
        <th>ten</th>
        <th>so nha</th>
        <th colspan="2">hanh dong</th>
    </tr>

    <c:forEach items="${list}" var="l">
        <tr>
            <td>${l.id}</td>
            <td>${l.ten}</td>
            <td>${l.soNha}</td>
            <td><a href="/pk/delete?id=${l.id}">Delete</a></td>
            <td><a href="/pk/view-update?id=${l.id}">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
