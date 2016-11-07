<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>软件项目风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<input id="js-pid" hidden value="${id}">
<div class="container project-container" >
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default intro-panel" id="js-panel-intro">
                <div class="panel-heading">项目概述</div>
                <div class="panel-body project-panel-body">
                    <div class="item">
                        <p class="title">项目名称：</p>
                        <p class="content" data-item="name">-</p>
                    </div>
                    <div class="item">
                        <p class="title">项目简介：</p>
                        <p class="content" data-item="description">-</p>
                    </div>
                    <div class="item">
                        <p class="title">创建者：</p>
                        <p class="content" data-item="creator">-</p>
                    </div>
                    <div class="item">
                        <p class="title">参与者：</p>
                        <p class="content" data-item="users">-</p>
                    </div>
                    <div class="item">
                        <p class="title">创建时间：</p>
                        <p class="content" data-item="createTime">-</p>
                    </div>
                    <div class="pull-right">
                        <button class="btn btn-primary btn-sm" id="js-btn-edit">编辑</button>
                        <button class="btn btn-danger btn-sm" id="js-btn-delete">删除</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">风险一览</div>
                <div class="panel-body project-panel-body">
                    <button class="btn btn-primary btn-sm" id="js-btn-add">创建条目</button>
                    <table class="table table-bordered" style="margin-top: 15px;">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Username</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th><td>Mark</td> <td>Otto</td> <td>@mdo</td> </tr> <tr> <th scope="row">2</th> <td>Jacob</td> <td>Thornton</td> <td>@fat</td> </tr> <tr> <th scope="row">3</th> <td>Larry</td> <td>the Bird</td> <td>@twitter</td> </tr>
                        </tbody>
                    </table>
                    <button class="btn btn-primary btn-sm pull-right" id="js-btn-more">加载更多</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/toaster.jsp"%>
<%@include file="common/project_modal.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/i18n/zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/project.js"></script>

<style>
    .project-container {
        margin-top: 60px;
    }
    .intro-panel .item {
        margin-bottom: 25px;
    }
    .intro-panel .item .title {
        font-size: 15px;
    }
    .intro-panel .item .content {
        font-size: 14px;
    }
</style>

</body>
</html>

