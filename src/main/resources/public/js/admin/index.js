/**
 * Created by lzz on 2018/10/1.
 */

$(document).on("click", ".load-iframe", function () {
    var data = {};
    data.src = $(this).data("src");
    smarty.html("admin/load_iframe", data, "main-container", function () {

    });
});

$(document).on("click", ".table-content", function (res) {
    var detail = $(this).data("detail");
    window.table = detail.tableName;
    var data = {};
    data.table = window.table;
    data.database = window.database;
    data.page = 1;
    data.conditions = [];
    data.sortList = [];
    window.queryConditions = [];
    window.sortList = [];
    smarty.post("getTableRowList",JSON.stringify(data), "admin/table_content_div", "main-container",function (res,res2) {
        console.log(res2);
        $("#add-field-button").click(function () {
            getInitFieldForm(window.database, window.table, function (data) {
                field_form(data.res);
            });
        });
    });
});

$(document).on("click", ".edit-field-button", function () {
    var fieldExtends = $("#field-extends-detail").data("detail");
    var fieldDetail = $(this).data("detail");
    var data = {};
    data.extends = fieldExtends;
    data.fields = fieldDetail;
    data.columns = $(this).data("columns");
    field_form(data);
});


function field_form(data) {
    smarty.open("admin/field_form", data, { title: "Edit",width:700}, function(){
        $("[data-plugin='image']").upload(
            function(_this,data){
                alert(data)
            }
        );
        $("[data-plugin='music']").upload(
            function(_this,data){
                alert(data)
            }
        );
        $("[data-plugin='video']").upload(
            function(_this,data){
                alert(data)
            }
        );
        $("#submit-field-form").click(function () {
            var formdata = sparrow_form.encode( "field-form", 2 );
            if( formdata ){
                if($("[name=id]").val()){
                    formdata.id = $("[name=id]").val();
                }
                var req = {};
                req.database = window.database;
                req.table = window.table;
                req.fieldMap = formdata;
                req.columns = data.columns;

                updateFieldForm(req, function(response){
                    console.log(response);
                })
            }
        });
    });
}

smarty.register_function( 'field_note', function( params ){
    var field = params['field'];
    var res = field;
    var arr = params['field_extend'];
    if( arr ){
        for(var i in arr){
            if(arr[i].fieldName == field){
                if( arr[i].note ){
                    res = arr[i].note;
                }
            }
        }
    }
    return res;
});
