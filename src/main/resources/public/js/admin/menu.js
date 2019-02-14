/**
 * Created by lzz on 2018/10/1.
 */

$(document).on("click", ".load-iframe", function () {
    var data = {};
    data.src = $(this).data("src");
    var type = $(this).data("type");
    if( type == "browser" ){
        smarty.html("admin/load_browser_iframe", data, "main-container", function () {

        });
    }else{
        smarty.html("admin/load_iframe", data, "main-container", function () {

        });
    }

});

function initMenu() {
    // custom scrollbar
    $("#sidebar").niceScroll({styler:"fb",cursorcolor:"#e8403f", cursorwidth: '3', cursorborderradius: '10px', background: '#404040', cursorborder: ''});

    jQuery('#sidebar .sub-menu').click(function () {
        jQuery('#sidebar .sub-menu').removeClass("active");
        $(this).addClass("active");
    });

    //    sidebar dropdown menu
    jQuery('#sidebar .sub-menu > a').click(function () {

        var last = jQuery('.sub-menu.open', $('#sidebar'));
        last.removeClass("open");
        jQuery('.arrow', last).removeClass("open");
        jQuery('.sub', last).slideUp(200);
        var sub = jQuery(this).next();
        if (sub.is(":visible")) {
            jQuery('.arrow', jQuery(this)).removeClass("open");
            jQuery(this).parent().removeClass("open");
            sub.slideUp(200);
        } else {
            jQuery('.arrow', jQuery(this)).addClass("open");
            jQuery(this).parent().addClass("open");
            sub.slideDown(200);
        }
        var o = ($(this).offset());
        diff = 200 - o.top;
        if(diff>0)
            $("#sidebar").scrollTo("-="+Math.abs(diff),500);
        else
            $("#sidebar").scrollTo("+="+Math.abs(diff),500);
    });
}

$(function() {
    function responsiveView() {
        var wSize = $(window).width();
        if (wSize <= 768) {
            $('#container').addClass('sidebar-close');
            $('#sidebar > ul').hide();
        }

        if (wSize > 768) {
            $('#container').removeClass('sidebar-close');
            $('#sidebar > ul').show();
        }
    }
    $(window).on('load', responsiveView);
    $(window).on('resize', responsiveView);
});

$('.icon-reorder').click(function () {
    if ($('#sidebar > ul').is(":visible") === true) {
        $('#main-content').css({
            'margin-left': '0px'
        });
        $('#sidebar').css({
            'margin-left': '-180px'
        });
        $('#sidebar > ul').hide();
        $("#container").addClass("sidebar-closed");
    } else {
        $('#main-content').css({
            'margin-left': '180px'
        });
        $('#sidebar > ul').show();
        $('#sidebar').css({
            'margin-left': '0'
        });
        $("#container").removeClass("sidebar-closed");
    }
});

$("html").niceScroll({styler:"fb",cursorcolor:"#e8403f", cursorwidth: '6', cursorborderradius: '10px', background: '#404040', cursorborder: '', zindex: '1000'});
