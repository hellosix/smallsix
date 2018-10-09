/**
 * Created by lzz on 2018/10/9.
 */

$(document).on("click", ".load-iframe", function () {
    var data = {};
    data.src = $(this).data("src");
    smarty.html("admin/load_iframe", data, "main-container", function () {

    });
});