$(document).ready(function () {

    var cache = {
        risk: {},
        projectUserList: [],
        typeMap: {
            1: {
                text: '低',
                type: 'default'
            },
            2: {
                text: '中',
                type: 'warning'
            },
            3: {
                text: '高',
                type: 'danger'
            },
        },
        typeList: [{val: 1, text: '低'}, {val: 2, text: '中'}, {val: 3, text: '高'}]
    };

    $('#js-modal-risk').modal({
        show: false,
        backdrop: 'static'
    });

    $('#js-modal-risk-state').modal({
        show: false,
        backdrop: 'static'
    });

    $.ajax({
        type: 'GET',
        url: $('#prefixUrl').val() + '/api/project/getById?id=' + $('#js-pid').val(),
        success: function(ret) {
            if (ret.code == 0) {
                if (ret.data) {
                    $('title').text('项目[' + ret.data.name + '] - 软件项目风险管理系统');
                }
            } else {
                toaster(ret.msg || '系统繁忙', 'error');
            }
        },
        error: function() {
            toaster('系统繁忙', "error");
        }
    });

    loadRisk();
    function loadRisk() {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/risk/getById?id=' + $('#js-rid').val(),
            success: function(ret) {
                if (ret.code == 0) {
                    if (ret.data) {
                        cache.risk = ret.data;
                        $('#js-panel-intro p[data-item]').each(function () {
                            var item = $(this).attr('data-item');
                            var text = ret.data[item];
                            if (item == 'committer') {
                                var committer = ret.data.committer;
                                $(this).text(committer.name + '（' + committer.username + '）');
                            } else if (item == 'followers') {
                                var followers = ret.data.followers;
                                if (followers.length == 0) {
                                    return;
                                }
                                var followerText = '';
                                for (var i = 0, one; one = followers[i]; i++) {
                                    followerText += one.name + '（' + one.username + '）；'
                                }
                                $(this).text(followerText);
                            } else if (item == 'createTime' || item == 'updateTime') {
                                $(this).text(formatDateTime(text/1000));
                            } else if (item == 'possibility' || item == 'impact') {
                                $(this).html('<span class="label label-' + cache.typeMap[String(text)].type + '">' +
                                    cache.typeMap[String(text)].text + '</span>');
                            } else {
                                $(this).text(text);
                            }
                        });
                        if (ret.data.isUser == 1) {
                            $('#js-btn-committer').show();
                            $('#js-btn-add').show();
                            if (ret.data.isCommitter == 0) {
                                $('#js-select-follower').prop('disabled', true);
                                $('#js-btn-delete').hide();
                            }
                        }
                    }
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    }

    $.ajax({
        type: 'GET',
        url: $('#prefixUrl').val() + '/api/project/getUsersByPid?id=' + $('#js-pid').val(),
        success: function(ret) {
            if (ret.code == 0) {
                if (ret.data) {
                    for (var i = 0, one; one = ret.data[i]; i++) {
                        cache.projectUserList.push({
                            id: one.id,
                            text: one.name + '（' + one.username + '）'
                        });
                    }
                    $('#js-select-follower').select2({
                        language:"zh-CN",
                        data: cache.projectUserList
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

    $('#js-btn-edit').on('click', function () {
        $('#js-modal-risk [name="content"]').val(cache.risk.content);
        $('#js-modal-risk [name="possibility"]').val(cache.risk.possibility);
        $('#js-modal-risk [name="impact"]').val(cache.risk.impact);
        $('#js-modal-risk [name="trigger"]').val(cache.risk.trigger);
        var users = [];
        for (var i = 0, one; one = cache.risk.followers[i]; i++) {
            users.push(one.id);
        }
        $('#js-select-follower').val(users).trigger("change");
        $('#js-modal-risk').modal('show');
    });

    $('#js-btn-add-risk-submit').on('click', function () {
        var data = {
            id: $('#js-rid').val(),
            pid: $('#js-pid').val()
        };
        $('#js-modal-risk [name]').each(function () {
            data[$(this).attr('name')] = $(this).val();
        });
        data.followers = $('#js-select-follower').val() || [];
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/risk/modify',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('编辑成功', 'success');
                    $('#js-modal-risk').modal('hide');
                    setTimeout(function () {
                        window.location.reload();
                    }, 500);
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    });

    $('#js-btn-delete').on('click', function () {
        showConfirm('真的要删除这个风险条目吗？（删除后不可恢复）', '删除风险条目', function() {
            ajaxDelete();
        });
        var ajaxDelete = function() {
            $.ajax({
                type: 'GET',
                url: $('#prefixUrl').val() + '/api/risk/delete?id=' + $('#js-rid').val(),
                success: function(ret) {
                    if (ret.code == 0) {
                        toaster('删除成功', 'success');
                        setTimeout(function () {
                            window.location.href = $('#prefixUrl').val() + '/project/' +
                                $('#js-pid').val();
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

    $('#js-btn-add').on('click', function () {
        $('#js-modal-risk-state [name="name"]').val('');
        $('#js-modal-risk-state [name="content"]').val('');
        $('#js-modal-risk-state').modal('show');
    });

    $('#js-btn-add-state-submit').on('click', function () {
        var data = {
            id: $('#js-rid').val(),
            name: $('#js-modal-risk-state [name="name"]').val(),
            content: $('#js-modal-risk-state [name="content"]').val()
        };
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/riskState/add',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('创建成功', 'success');
                    $('#js-modal-risk-state').modal('hide');
                    loadRiskState();
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    });

    loadRiskState();
    function loadRiskState() {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/riskState/getRiskStatesByRid?id=' + $('#js-rid').val(),
            success: function(ret) {
                if (ret.code == 0) {
                    if (ret.data) {
                        // TODO
                    }
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