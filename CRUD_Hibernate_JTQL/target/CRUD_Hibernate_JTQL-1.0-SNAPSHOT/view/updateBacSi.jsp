<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/11/2024
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Update bac si</h2>
<form action="/bac-si/update" method="post">
    <input type="hidden" name="id" value="${bs.id}">
    ten <input type="text" name="ten" value="${bs.ten}"> <br>
    dia chi <input type="text" name="diaChi" value="${bs.diaChi}"> <br>
    luong <input type="text" name="luong" value="${bs.luong}"> <br>
    id phong kham
    ${pk}
    <select name="phongKham">
        <c:forEach items="${pk}" var="p">
            <option value="${p.id}">${p.id}</option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Update</button>
</form>
</body>
</html>
