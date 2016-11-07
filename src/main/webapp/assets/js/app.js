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

    win.tmplCache = {};

    win.tmpl = function(strTmpl, args) {
        var argNames = [];
        var argValues = [];
        for (var a in args) {
            argNames.push(a);
            argValues.push(args[a]);
        }
        var funcs = win.tmplCache[strTmpl] || function() {
                var f = [ 'var __out__ = [];' ];
                strTmpl.replace(/<%=([\d\D]*?)%>|<%([\d\D]*?)%>|([\d\D]+?)(?=<\%|$)/g, function($0, $1, $2, $3) {
                    if ($3) {
                        f.push('__out__.push(unescape("', escape($3), '"));');
                    } else if ($1) {
                        f.push('__out__.push(', $1, ');');
                    } else if ($2) {
                        f.push($2, ';');
                    }
                });
                f.push('return __out__.join("")');
                return new Function(argNames.join(', '), f.join(''));
            }();
        win.tmplCache[strTmpl] = funcs;
        return funcs.apply(args||{}, argValues);
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