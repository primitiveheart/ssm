<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/8
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导出到cvs文件</title>
    <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"></script>
</head>
<body>
    <button class="export">导出用户</button>
    <script>
       $(document).ready(function () {
           $(".export").bind("click", function () {
               $(location).attr("href","exportCSV.html");
           })
       })
    </script>
</body>
</html>
