<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/26/2024
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cap nhat</title>
</head>
<body>
<form action="/gv/update" method="post">
    id: <input type="text" name="id" readonly value="${gv.id}"> <br>
    ten: <input type="text" name="ten" required value="${gv.ten}"> <br>
    tuoi: <input type="text" name="tuoi" required value="${gv.tuoi}"> <br>
    luong: <input type="text" name="luong" required value="${gv.luong}"> <br>
    ten truong hoc:
    <select name="truongHoc">
        <c:forEach items="${th}" var="t">
            <option value="${t.id}" label="${t.ten}"></option>
        </c:forEach>
    </select> <br>
    <button type="submit">Cap nhat</button>
</form>
</body>
</html>
