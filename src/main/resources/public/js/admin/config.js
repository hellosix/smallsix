/**
 * Created by lzz on 2018/10/4.
 */
window.database="finebi";
window.imageurl="http://localhost:8182/package/pack/" + window.database + "/";

$(function(){
    window.setInterval("heartbeat()", 20000);
    autoGetUser(function(obj){
        var username = obj.res["username"];
        $("#username").text(username);
    });
});


function heartbeat(){
    ajax.async_get('/user/heartbeat',function(obj){

    });
}
