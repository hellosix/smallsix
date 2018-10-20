/**
 * Created by lzz on 2018/10/5.
 */

smarty.get("getSystemAdviseList", "super/systemAdvise/system_advise_list", "content-container",function () {
    $(".edit-system-advise").click(function () {
        var id = $(this).data("id");
        addOrUpdateSystemAdvise(id);
    });
    $(".remove-system-advise").click(function () {
        var id = $(this).data("id");
        sparrow_win.confirm("确定删除？", function(){
            removeSystemAdvise(id, function () {

            });
        });
    });

}, true);

$("#add-system-advise").click(function () {
    addOrUpdateSystemAdvise(0);
});


function addOrUpdateSystemAdvise(id) {
    smarty.fopen("getSystemAdvise?id=" + id, "super/systemAdvise/add_system_advise",true,{title: "Update systemAdvise",width:600},function () {

        $("#save-form").click(function () {
            var data = sparrow_form.encode( "system-advise-form",0 );
            if(data){
                updateSystemAdvise(data, function (response) {

                });
            }

        });
    });
}
