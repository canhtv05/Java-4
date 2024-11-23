<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/16/2024
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/nv/update" method="post">
    id: <input type="text" readonly name="id" value="${nv.id}"> <br>
    ten: <input type="text" required name="ten" value="${nv.ten}"> <br>
    tuoi: <input type="text" required name="tuoi" value="${nv.tuoi}"> <br>
    luong: <input type="text" required name="luong" value="${nv.luong}"> <br>
    ten phong ban:
    <select name="phongBan">
        <c:forEach items="${pb}" var="p">
            <option value="${p.id}" label="${p.ten}"></option>
        </c:forEach>
    </select><br>
    <button type="submit">Update</button>
</form>
</body>
</html>
