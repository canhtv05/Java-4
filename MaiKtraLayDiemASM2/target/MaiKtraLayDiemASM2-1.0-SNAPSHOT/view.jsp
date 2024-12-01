<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/27/2024
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <td>id</td>
            <td>ten</td>
        </tr>
        <c:forEach items="${pk}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.ten}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/pk/all?page=${pageNo < totalCount + 1 ? pageNo + 1 : totalCount + 1}">Next</a> <br>
    <a href="/pk/all?page=${pageNo > 1 ? pageNo - 1 : 1}">Prev</a> <br>
</body>
</html>
