<%--
  Created by IntelliJ IDEA.
  User: CanhPC
  Date: 11/28/2024
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/song-management/playlists">home</a> <br>
    id: <span>${bh.id}</span> <br>
    ten: <span>${bh.ten}</span> <br>
    gia: <span>${bh.gia}</span> <br>
    thoi luong: <span>${bh.thoiLuong}</span> <br>
    ngay ra mat: <span>${bh.ngayRaMat}</span> <br>
    ten ca si: <span>${bh.caSi.ten}</span> <br>
    tuoi ca si: <span>${bh.caSi.tuoi}</span> <br>
</body>
</html>
