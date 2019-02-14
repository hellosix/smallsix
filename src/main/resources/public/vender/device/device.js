/**
 * Created by lzz on 2018/12/6.
 */

$(document).ready(function () {
    function fixHeight() {
        var headerHeight = $("#switcher").height();
        $("#iframe").attr("height", $(window).height() + 200 + "px");
    }
    $(window).resize(function () {
        // 不要变化
        //fixHeight();
    }).resize();

    $(document).on("click",".icon-mobile-3", function () {
        $('#iframe-wrap').removeClass().addClass('mobile-width-3');
        $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
        $(this).addClass('active');
        return false;
    });

    $(document).on( "click",".icon-mobile-2",function () {
        $('#iframe-wrap').removeClass().addClass('mobile-width-2');
        $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
        $(this).addClass('active');
        return false;
    });

    $(document).on( "click",".icon-mobile-1",function () {
        $('#iframe-wrap').removeClass().addClass('mobile-width');
        $('.icon-tablet,.icon-mobile,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
        $(this).addClass('active');
        return false;
    });

    $(document).on("click",".icon-mobile-0", function () {
        $('#iframe-wrap').removeClass().addClass('tablet-width');
        $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
        $(this).addClass('active');
        return false;
    });

    $(document).on("click",".icon-monitor", function () {
        fixHeight();
        $('#iframe-wrap').removeClass().addClass('full-width');
        $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
        $(this).addClass('active');
        return false;
    });


});
