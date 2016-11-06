<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>软件项目风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<div class="container project-container">
    <div class="panel panel-default">
        <div class="panel-heading">我的项目</div>
        <div class="panel-body project-panel-body">
            <button class="btn btn-primary">创建项目</button>

            <h4 class="title">我创建的</h4>
            <div class="content">
                <div class="row">
                    <div class="col-md-3">
                        <div class="thumbnail">我的项目</div>
                    </div>
                    <div class="col-md-3">
                        <div class="thumbnail">我的项目</div>
                    </div>
                    <div class="col-md-3">
                        <div class="thumbnail">我的项目</div>
                    </div>
                    <div class="col-md-3">
                        <div class="thumbnail">我的项目</div>
                    </div>
                    <div class="col-md-3">
                        <div class="thumbnail">我的项目</div>
                    </div>
                </div>
            </div>

            <h4 class="title">我参与的</h4>
            <div class="content">
                无
            </div>

        </div>
    </div>

</div>

<%@include file="common/toaster.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

<style>
    .project-container {
        margin-top: 60px;
    }
    .project-panel-body .title {
        margin-top: 30px;
    }
    .project-panel-body .content {
        min-height: 100px;
    }
    .project-panel-body .content .thumbnail {
        height: 120px;
        cursor: pointer;
        text-align: center;
        line-height: 120px;
        font-size: 18px;
        background-color: #eee;
    }
    .project-panel-body .content .thumbnail:hover {
        background-color: #fff;
        -webkit-transition: 0.5s;
        transition: 0.5s;
    }
</style>

</body>
</html>
