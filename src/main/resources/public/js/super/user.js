/**
 * Created by lzz on 2018/10/5.
 */

smarty.get("getUserList", "super/user/user_list", "menu-content",function () {
    $(".edit-user").click(function () {
        var id = $(this).data("id");
        addOrUpdateUser(id);
    });
    $(".remove-user").click(function () {
        var id = $(this).data("id");
        removeUser(id);
    });

}, true);

$("#add-user").click(function () {
    addOrUpdateUser(0);
});


function addOrUpdateUser(id) {
    smarty.fopen("getUser?id=" + id, "super/user/add_user",true,{title: "Update User",width:600},function () {
        $("#menu-form-preview").click(function () {
            $("#form-html-content").html($('[name="menu"]').val());
        });

        $("#save-menu-form").click(function () {
            var data = sparrow_form.encode( "menu-form",0 );
            if(data){
                updateUser(data, function (response) {

                });
            }

        });
    });
}
