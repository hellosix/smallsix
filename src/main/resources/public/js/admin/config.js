/**
 * Created by lzz on 2018/10/4.
 */

$(function(){
    autoGetUser(function(obj){
        window.user = obj.res;
        var username = obj.res["username"];
        window.database=obj.res["databaseName"];
        window.imageurl="http://localhost:8182/package/pack/" + window.database + "/";
        window.musicurl="http://localhost:8182/package/pack/" + window.database + "/";
        window.videourl="http://localhost:8182/package/pack/" + window.database + "/";

        $("#username").text(username);

        smarty.get("getMenu?database=" + window.database, "admin/admin_menu", "sidebar-menu",function () {
            var menuStr = window.user.menu;
            if( menuStr ){
                $(".menu-defult").remove();
                $("#sidebar-menu").prepend( menuStr );
            }
            initMenu();
        }, true);
    });

    window.setInterval("heartbeat()", 20000);
});


function heartbeat(){
    ajax.async_get('/user/heartbeat',function(obj){

    });
}
