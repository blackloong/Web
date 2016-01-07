var $slide = $('.slide'),
    $slideGroup = $('.slideup');

var slidesTotal = ($slide.length - 1),
    current = 0;

var updateIndex = function () 
{
    if (current === slidesTotal)
    {
        current = 0;
    }
    else
    {
        current++;
    }
    transition(current);
};

var transition = function (slidePosition) {
    $slideGroup.animate({
        'top': '-' + slidePosition + '7%'
    });
};

window.setInterval(updateIndex, 2000);

function btnTopHover() {
    window.location.href = "http://mp.weixin.qq.com/s?__biz=MzI2NjAwOTEyMw==&mid=414512791&idx=1&sn=96bb6ec7001d58a65ead8fb805248041#rd";
}
function btnRegister() {
    window.location.href = "http://www.uncn88.com/reg";
}
function btnNotifyNext() {
    $('.shareBackground').show();
}
function btnCloseShare() {
    $('.shareBackground').hide();
}
var updateBackgroundHeight = function () 
{
    document.getElementsByClassName('pageBackground')[0].style.height = window.innerWidth * 3 + 'px';
}
updateBackgroundHeight();

window.addEventListener("resize", updateBackgroundHeight, false);