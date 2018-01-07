<%@ page import="java.nio.file.Path" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/12/28
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台登录</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--css-->
    <link rel="stylesheet" href="resources/css/reset.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/supersized.css">

    <!--js-->

</head>
<body oncontextmenu="return false">
    <div class="page-container">
        <input type="hidden" id="error" value="${error}">
        <h1>login</h1>
        <form action="loginAdmin.html" method="post">
            <div>
                <input type="text" name="userName" class="usernma" placeholder="Username" autocomplete="off">
            </div>
            <div>
                <input type="password" name="password" class="password" placeholder="Password" oncontextmenu="return false" onpaste="return false">
            </div>
            <div>
                <input type="text" name="kaptcha" value=""><img src="kaptcha.jpg" id="kaptchaImage" title="看不清，换一张">
            </div>
            <div>
                <input type="checkbox" name="rememberMe"/>自动登录
            </div>
            <button id="submit" type="submit">sign in</button>
        </form>
        <div class="connect">
            <p>You never know what you can do till you try</p>
            <p style="margin-top:20px;">除非你亲自尝试一下,否则你永远不知道你能做什么。</p>
        </div>
    </div>
    <div class="alert" style="display: none">
        <h2>消息</h2>
        <div class="alert_con">
            <p id="ts"></p>
            <p style="line-height: 70px"><a class="btn">确定</a></p>
        </div>
    </div>

    <!--js-->
    <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"></script>
    <script src="resources/js/supersized.3.2.7.min.js"></script>
    <script src="resources/js/supersized-init.js"></script>
    <script>
        $(".btn").click(function(){
            is_hide();
        })
        var u = $("input[name=username]");
        var p = $("input[name=password]");
        $("#submit").live("click", function(){
            if(u.val() == "" || p.val() == ""){
                $("#ts").html("用户名或密码不能为空");
                is_show();
            }else{
                var reg = /^[0-9A-Za-z]+$/;
                if(!reg.exec(u.val())){
                    $("#ts").html("用户名错误");
                    is_show();
                    return false;
                }
            }
        });

        window.onload = function(){
            var error = $("#error").val();
            if(error != ""){
                $("#ts").html("用户名或密码错误");
                is_show();
            }
            $(".connect p").eq(0).animate({"left":"0%"});
            $(".connect p").eq(1).animate({"left":"0%"});
        }

        function is_hide(){
            $(".alert").animate({"top":"-40%"}, 300);
        }
        function is_show(){
            $(".alert").show().animate({"top":"45%"}, 300)
        }

        $("#kaptchaImage").click(function(){
            $(this).attr("src","kaptcha.jpg?" + Math.floor(Math.random()*100));
        })
    </script>
</body>
</html>
