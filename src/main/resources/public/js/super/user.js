/**
 * Created by lzz on 2018/10/5.
 */


smarty.html("super/user/user_form", {}, "menu-content", function () {
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