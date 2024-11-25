<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/23/2024
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>DemoAjax</h1>
<button onclick="getData()">GetData</button>
<div id="data"></div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    function getData() {
        $.ajax({
            url: "/api/ajax/demo",
            type: "get",
            dataType: "json",
            success: function (response) {
                console.log(response)
                document.getElementById("data").innerHTML
                    = "MSSV" + response.ma + "ten" + response.ten + "tuoi" + response.tuoi
            },
            error: function () {
                console.log("err");
            }
        })
    }
</script>
</html>