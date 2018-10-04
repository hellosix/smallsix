/**
 * Created by lzz on 2018/10/1.
 */

window.database="finebi";
smarty.get("getMenu?database=" + window.database, "admin/admin_menu", "sidebar-menu",function () {
    initMenu();
}, true);

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
    smarty.post("getTableRowList",JSON.stringify(data), "admin/table_content_div", "main-container",function () {

    });
});

$(document).on("click", ".edit-field-button", function () {
    var fieldExtends = $("#field-extends-detail").data("detail");
    var fieldDetail = $(this).data("detail");
    var data = {};
    data.extends = fieldExtends;
    data.fields = fieldDetail;
    data.columns = $(this).data("columns");
    smarty.open("admin/field_form", data, { title: "Edit",width:700}, function(){
        $("#submit-field-form").click(function () {
            var formdata = sparrow_form.encode( "field-form", 2 );
            if( formdata ){
                formdata.id=fieldDetail.id;
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
});


smarty.register_function( 'field_note', function( params ){
    var field = params['field'];
    var res = field;
    var arr = params['field_extend'];
    if( arr ){
        for(var i in arr){
            if(arr[i].fieldName == field){
                res = arr[i].note;
            }
        }
    }
    return res;
});