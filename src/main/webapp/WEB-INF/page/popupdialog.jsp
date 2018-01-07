<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/7
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>弹出式对话框</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
    <a  id="btn-login" class="btn btn-success" href="#">我要登录</a>
    <div id="dialog-login" title="用户登录">
        <form action="">
            用户名:<input type="text" >
            密码：<input type="password">
        </form>
    </div>
    <script >
        var $dialog = $("#dialog-login");
        $dialog.dialog({
                    autoOpen: false,
                    modal: false,
                    show: {effect: "bling", durationn: 1000},
                    hide: {effect: "explode", duration: 1000},
                    buttons: {
                    "登录": function () {
                        setTimeout(function () {
                            alert("登录成功");
                            $dialog.dialog("close");
                        }, 1000)
                    },
                    "取消": function () {
                        $dialog.dialog("close")
                    }
                },
                close:function(){
                    $("form").get(0).reset()
                }
        })
        $("#btn-login").click(function(){
            $dialog.dialog("open");
        })
    </script>
</body>
</html>
