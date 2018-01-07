<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/7
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>模态框插件</title>

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <style type="text/css">
        *{
            margin: 0px;
            padding: 0px;
        }
        body{
            font-size: 14px;;
        }
    </style>
</head>
<body>
    <!--tabindex = -1 表示关闭焦点，esc可以关闭对话框-->
    <div class="modal fade" id="mod1" tabindex="-1">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="model-title">用户登录</h4>
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon">用户名:</span>
                        <input type="text" class="form-control" placeholder="请输入用户名">
                    </div>
                    <p></p>
                    <div class="input-group">
                        <span class="input-group-addon">密码:</span>
                        <input type="text" class="form-control" placeholder="请输入密码">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default">注册</button>
                    <button class="btn btn-primary">登录</button>
                </div>
            </div>
        </div>
    </div>

    <hr>
    <button class="btn btn-primary" data-toggle="modal" data-target="#mod1">用户登录</button>

    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
</body>
</html>
