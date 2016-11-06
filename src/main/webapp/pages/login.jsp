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
        <button class="btn btn-primary btn-block" type="button" id="js-submit">登录</button>
    </div>
</div>

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
    $('#js-submit').on('click', function () {
        var data = {
            username: $('#inputUsername').val(),
            password: $('#inputPassword').val()
        };
        $.ajax({
            type: "POST",
            url: $('#prefixUrl').val() + "/api/auth/login",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(ret) {
                if (ret.code == 0) {
                    window.location.href = $('#prefixUrl').val() + '/';
                } else {

                }
            },
            error: function() {
                // toaster("服务器出现问题，请稍微再试！", "error");
            }
        });
    });
});
</script>

</body>
</html>