/**
 * Created by lzz on 2018/10/5.
 */

smarty.get("getUserNotifyList", "super/userNotify/user_notify_list", "content-container",function () {
    $(".edit-user-notify").click(function () {
        var id = $(this).data("id");
        addOrUpdateUserNotify(id);
    });
    $(".remove-user-notify").click(function () {
        var id = $(this).data("id");
        sparrow_win.confirm("确定删除？", function(){
            removeUserNotify(id, function () {

            });
        });
    });

}, true);

$("#add-user-notify").click(function () {
    addOrUpdateUserNotify(0);
});


function addOrUpdateUserNotify(id) {
    smarty.fopen("getUserNotify?id=" + id, "super/userNotify/add_user_notify",true,{title: "Update UserNotify",width:600},function () {

        $("#save-form").click(function () {
            var data = sparrow_form.encode( "user-notify-form",0 );
            if(data){
                updateUserNotify(data, function (response) {

                });
            }

        });
    });
}
