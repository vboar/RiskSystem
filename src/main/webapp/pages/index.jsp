<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的项目 - 软件项目风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<div class="container project-container">
    <div class="panel panel-default">
        <div class="panel-heading">我的项目</div>
        <div class="panel-body project-panel-body">
            <button class="btn btn-primary" id="js-btn-add">创建项目</button>

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
<%@include file="common/project_modal.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/i18n/zh-CN.js"></script>
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

<script>
    $(document).ready(function () {

        var cache = {
            userList: []
        };

        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/user/getAllUsers',
            success: function(ret) {
                if (ret.code == 0) {
                    if (ret.data) {
                        for (var i = 0, one; one = ret.data[i]; i++) {
                            cache.userList.push({
                                id: one.id,
                                text: one.name + '（' + one.username + '）'
                            });
                        }
                        $('#js-select-user').select2({
                            language:"zh-CN",
                            data: cache.userList
                        });
                    }
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });

        $('#js-modal-project').modal({
            show: false,
            backdrop: 'static'
        });

        $('#js-btn-add').on('click', function () {
            $('#js-input-name').val('');
            $('#js-textarea-description').val('');
            $('#js-select-user').val(null).trigger("change");
            $('#js-modal-project').modal('show');
        });

        $('#js-btn-add-submit').on('click', function () {
            var data = {
                name: $('#js-input-name').val(),
                description: $('#js-textarea-description').val(),
                users: $('#js-select-user').val() || []
            };
            console.log(data);
        });
    });
</script>

</body>
</html>
