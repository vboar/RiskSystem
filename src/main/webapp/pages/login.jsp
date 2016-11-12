<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录 - 软件项目风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<div class="container form-container">
    <div class="form-signin">
        <h3 class="form-signin-heading">登录</h3>
        <div class="form-group">
            <label for="inputUsername" class="sr-only">用户名</label>
            <input type="email" id="inputUsername" class="form-control" placeholder="用户名" autofocus>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="sr-only">密码</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="密码">
        </div>
        <button class="btn btn-primary btn-block" type="button" id="js-btn-submit">登录</button>
        <p style="margin-top: 10px;">测试帐号用户名和密码均为check</p>
    </div>
</div>

<%@include file="common/toaster.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

<style>
    .form-container {
        margin-top: 80px;
    }
    .form-signin {
        width: 300px;
        margin: 0 auto;
    }
</style>

<script>
$(document).ready(function () {
    $('#js-btn-submit').on('click', function () {
        login();
    });

    $(document).keydown(function (e) {
        if (e.keyCode == 13) {
            login();
        }
    });

    function login() {
        var data = {
            username: $('#inputUsername').val(),
            password: $('#inputPassword').val()
        };
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/auth/login',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('登录成功', 'success');
                    setTimeout(function () {
                        window.location.href = $('#prefixUrl').val() + '/';
                    }, 500);
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    }
});
</script>

</body>
</html>