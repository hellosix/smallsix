/**
 * Created by lzz on 2018/10/1.
 */

smarty.get("getMenu", "super/super_menu", "sidebar-menu",function () {
    initMenu();

}, true);

$(document).on("click", ".table-detail", function(res){
    var table = $(this).data("table");
    smarty.get("getApiList?database=a&table=b", "super/table_content", "main-container",function () {

    }, true);
});
