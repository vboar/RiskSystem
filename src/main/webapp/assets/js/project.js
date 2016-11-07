$(document).ready(function () {

    var cache = {
        project: {},
        userList: []
    };

    $.ajax({
        type: 'GET',
        url: $('#prefixUrl').val() + '/api/project/getById?id=' + $('#js-pid').val(),
        success: function(ret) {
            if (ret.code == 0) {
                if (ret.data) {
                    cache.project = ret.data;
                    $('#js-panel-intro p[data-item]').each(function () {
                        var item = $(this).attr('data-item')
                        var text = ret.data[item];
                        if (item == 'creator') {
                            var creator = ret.data.creator;
                            $(this).text(creator.name + '（' + creator.username + '）');
                        } else if (item == 'users') {
                            var users = ret.data.users;
                            var userText = '';
                            for (var i = 0, one; one = users[i]; i++) {
                                userText += one.name + '（' + one.username + '）；'
                            }
                            $(this).text(userText);
                        } else if (item == 'createTime') {
                            $(this).text(text);
                        } else {
                            $(this).text(text);
                        }
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

    $('#js-btn-delete').on('click', function () {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/project/delete?id=' + $('#js-pid').val(),
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('删除成功', 'success');
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
    });

    $('#js-btn-edit').on('click', function () {
        $('#js-input-name').val(cache.project.name);
        $('#js-textarea-description').val(cache.project.description);
        var users = [];
        for (var i = 0, one; one = cache.project.users[i]; i++) {
            users.push(one.id);
        }
        $('#js-select-user').val(users).trigger("change");
        $('#js-modal-project').modal('show');
    });

    $('#js-btn-add-submit').on('click', function () {
        var data = {
            id: $('#js-pid').val(),
            name: $('#js-input-name').val(),
            description: $('#js-textarea-description').val(),
            users: $('#js-select-user').val() || []
        };
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/project/modify',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('编辑成功', 'success');
                    setTimeout(function () {
                        window.location.href = $('#prefixUrl').val() + '/project/' + ret.data.id;
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

    $('#js-btn-add').on('click', function () {

    });

});