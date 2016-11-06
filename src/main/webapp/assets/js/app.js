(function (win, doc, undefined) {

    win.toaster = function(message, type) {
        var toaster = $("#toaster");
        toaster.append('<div class="toast-item"><div class="message">' + message + '</div>' +
            '<i class="close fa fa-close"></i></div>');
        var thisItem = toaster.children().last();
        $(thisItem.children(".close").eq(0)).bind("click", function () {
            thisItem.slideUp(function() {
                thisItem.remove();
            });
        });
        if (type == "success") thisItem.addClass("alert alert-success");
        else if (type == "error") thisItem.addClass("alert alert-danger");
        thisItem.fadeIn();
        setTimeout(function() {
            thisItem.slideUp(function() {
                thisItem.remove();
            });
        }, 3000);
    };

})(window, document);

$(document).ready(function () {
    
    $('#js-btn-logout').on('click', function () {
        $.ajax({
            type: 'GET',
            url: $('#prefixUrl').val() + '/api/auth/logout',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('登出成功' , 'success');
                    setTimeout(function () {
                        window.location.href = $('#prefixUrl').val() + '/login';
                    }, 1000);
                } else {
                    toaster(ret.msg || '系统繁忙' , 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    });

});