<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/14/2024
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add new</h2>
<form action="/hdct/update" method="post">
    <input type="hidden" name="id" value="${hdct.id}">
    tenSP: <input type="text" name="ten" required value="${hdct.tenSanPham}"> <br>
    soluong: <input type="text" name="soLuong" required value="${hdct.soLuong}"> <br>
    gia: <input type="text" name="gia" required value="${hdct.gia}"> <br>
    ghi chu: <input type="text" name="ghiChu" required value="${hdct.ghiChu}"> <br>
    ten khach hang:
    <select name="hoaDon">
        <c:forEach items="${hd}" var="h">
            <option value="${h.id}" label="${h.tenKhachHang}"></option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Update</button>
</form>
</body>
</html>
