/**
 * Created by lzz on 2018/10/19.
 */

$(document).on("click", ".top-link", function () {
    var src = $(this).data("target");
    $("a[data-src='" + src +"']").click();
});

$(document).on("click", ".system-notify", function () {
    smarty.open("/admin/system_notify", {}, { title: "Notify",width:700}, function(){

    });
});
