function gotoplay(scene) {
    var gourl = "http://shihaijiang.com/boguswxfriends";
    location.href = gourl;
};
function safetostring(str) {
    return String(str).replace(/&amp;/g, '&').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&quot;/g, '"').replace(/&#39;/g, "'");
}

$(".data-name").text(safetostring(nickname));
$(".data-avt").attr("src", headimgurl);
var cw = $('.list-img').width();
$('.list-img').css({ 'height': cw + 'px' });

$(window).resize(function () {
    var cw = $('.list-img').width();
    $('.list-img').css({ 'height': cw + 'px' });
});

$(document.body).show();
function hideActionSheet(weuiActionsheet, mask) {
    weuiActionsheet.removeClass('weui_actionsheet_toggle');
    mask.removeClass('weui_fade_toggle');
    weuiActionsheet.on('transitionend', function () {
        mask.hide();
    }).on('webkitTransitionEnd', function () {
        mask.hide();
    })
}
function showActionSheet() {
    var mask = $('#mask');
    var weuiActionsheet = $('#weui_actionsheet');
    weuiActionsheet.addClass('weui_actionsheet_toggle');
    mask.show().addClass('weui_fade_toggle').click(function () {
        hideActionSheet(weuiActionsheet, mask);
    });
    $('#actionsheet_cancel').click(function () {
        hideActionSheet(weuiActionsheet, mask);
    });
    weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');
}
$('#list').on('click', function () {
    //   showActionSheet();
});
$('img').on('click', function () {
    //     showActionSheet();
});

$('.play_pyq').on('click', function () {
    var scene = $(this).data("scene");
    gotoplay(scene);
});


$('body').on('click', function (e) {

    var tar = e.target || window.event.srcElement;
    var tid = $(tar).attr("id")
    var tcl = $(tar).attr("class")

    if (tid != "gotoplay" && tid != "actionsheet_cancel" && tid != "xq" && tcl != "weui_actionsheet_cell play_pyq") {

        if ($("#shopping-box").is(":visible")) {
            $("#shopping-box").slideUp();
        } else {
            $("#shopping-box").hide();
            $("#shopping-box").slideDown();
        }
    }
});
$(window).scroll(function () {
    //   $("#shopping-box").hide();
    $("#shopping-box").slideUp();
})
