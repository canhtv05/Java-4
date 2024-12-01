<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/26/2024
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xem tat ca</title>
</head>
<body>
<h1>Them giao vien moi</h1>
<form action="/gv/add" method="post">
    ten: <input type="text" name="ten" required> <br>
    tuoi: <input type="text" name="tuoi" required> <br>
    luong: <input type="text" name="luong" required> <br>
    ten truong hoc:
    <select name="truongHoc">
        <c:forEach items="${th}" var="t">
            <option value="${t.id}" label="${t.ten}"></option>
        </c:forEach>
    </select> <br>
    <button type="submit">Them</button>
</form>

<h1>Danh sach giao vien</h1>
<table border="1" style="border-collapse: collapse">
    <tr>
        <th>ID</th>
        <th>Ten</th>
        <th>Tuoi</th>
        <th>Luong</th>
        <th>Ten truong hoc</th>
        <th colspan="3">Hanh dong</th>
    </tr>
    <c:forEach items="${gv}" var="g">
        <tr>
            <td>${g.id}</td>
            <td>${g.ten}</td>
            <td>${g.tuoi}</td>
            <td>${g.luong}</td>
            <td>${g.truongHoc.ten}</td>
            <td>
                <a href="/gv/delete?id=${g.id}">Xoa</a>
                <a href="/gv/view-update?id=${g.id}">Cap nhat</a>
                <a href="/gv/detail?id=${g.id}&id_truong_hoc=${g.truongHoc.id}">Xem chi tiet</a>
            </td>
        </tr>
        <td>
            <button><a href="/gv/view-all/page?"></a></button>
        </td>
    </c:forEach>
</table>
<a href="/logout">Logout</a> <br>
<button onclick="getData()">Ajax console.log</button>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    const getData = () => {
        $.ajax({
            url: "/gv/api/all-res",
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
            },
            error: function () {
                throw new Error("Not found data")
            }
        })
    }
</script>
</html>
