/**
 * Created by lzz on 2018/10/14.
 */

smarty.html("admin/user/user_content", {}, "main-container", function () {

});

$("#show-user").click(function () {
    addActive("show-user");
    smarty.html("admin/user/user_content", {}, "main-container", function () {

    });
});

$("#edit-user").click(function () {
    addActive("edit-user");
    smarty.html("admin/user/edit_user", {}, "main-container", function () {

    });
});

function addActive(target) {
    $(".user-link").removeClass("active");
    $('[data-target="'+ target +'"]').addClass("active");
}