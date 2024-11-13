<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/13/2024
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/dh/update" method="post">
    <input type="hidden" name="id" value="${dh.id}">
    name: <input type="text" name="name" required value="${dh.ten}"> <br>
    price: <input type="text" name="price" required value="${dh.gia}"> <br>
    quantity: <input type="text" name="quantity" required value="${dh.soLuong}"> <br>
    air conditioner brand name:
    <select name="brand">
        <c:forEach items="${ldh}" var="b">
            <option value="${b.id}" label="${b.ten}"></option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Update</button>
</form>
</body>
</html>
