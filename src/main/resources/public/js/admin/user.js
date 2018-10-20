/**
 * Created by lzz on 2018/10/14.
 */

autoGetUser(function(obj){
    window.user = obj.res;
    window.database=obj.res["databaseName"];
    window.imageurl="http://localhost:8182/package/pack/" + window.database + "/";
    $("#user-head").attr("src", window.imageurl + window.user.head);
    $("#user-name").text(window.user.username);
    $("#show-user").click();
});

$("#show-user").click(function () {
    addActive("show-user");
    smarty.get("../user/getUser?id=" + window.user.id,"admin/user/user_content","main-container",function () {

    });
});

$("#edit-user").click(function () {
    addActive("edit-user");

    smarty.get("../user/getUser?id=" + window.user.id,"admin/user/edit_user","main-container",function () {
        $("#save-menu-form").click(function () {
            var data = sparrow_form.encode( "menu-form",0 );
            if(data){
                updateUser(data, function (response) {

                });
            }
        });
    }, true);
});

function addActive(target) {
    $(".user-link").removeClass("active");
    $('[data-target="'+ target +'"]').addClass("active");
}

$("#user-head").click(function () {
   layer.closeAll();
   smarty.open("admin/user/update_head",{"head":window.user.head},{title:"update head",width:320,height:300},function () {
       $("[data-plugin='image']").upload(
           function(_this,data){
               alert(data)
           }
       );

       $("#update-form").click(function () {
           var data = sparrow_form.encode( "head-form",0 );
           if(data){
               window.user.head = data.head;
               updateUser(window.user, function (response) {

               });
           }
       });
   });
});