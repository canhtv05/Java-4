<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add new air conditioner</h2><br>
<form action="/dh/add" method="post">
    name: <input type="text" name="name" required> <br>
    price: <input type="text" name="price" required> <br>
    quantity: <input type="text" name="quantity" required> <br>
    air conditioner brand name:
    <select name="brand">
        <c:forEach items="${ldh}" var="b">
            <option value="${b.id}" label="${b.ten}"></option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">ADD</button>
</form>

<h2>list</h2> <br>
<table style="border-collapse:  collapse" border="1">
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>price</th>
        <th>quantity</th>
        <th>air conditioner brand name</th>
        <th colspan="2">actions</th>
    </tr>
    <c:forEach items="${dh}" var="h">
        <tr>
            <td>${h.id}</td>
            <td>${h.ten}</td>
            <td>${h.gia}</td>
            <td>${h.soLuong}</td>
            <td>${h.loaiDieuHoa.ten}</td>
            <td><a href="/dh/delete?id=${h.id}">Delete</a></td>
            <td><a href="/dh/view-update?id=${h.id}">Update</a></td>
        </tr>
    </c:forEach>

    <a href="/dh/all?page=${pageNo > 1 ? pageNo - 1 : 1}">Prev</a>
    <h5>${pageNo}</h5>
    <a href="/dh/all?page=${pageNo < totalPage ? pageNo + 1 : totalPage}">Next</a>

</table>
</body>
</html>
