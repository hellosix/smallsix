/**
 * Created by lzz on 2018/10/1.
 */

smarty.get("getMenu", "super/super_menu", "sidebar-menu",function () {
    initMenu();

}, true);

$(document).on("click", ".table-detail", function(res){
    var table = $(this).data("table");
    window.table = table;
    smarty.get("getApiList?database="+ window.database +"&table=" + window.table, "super/table_content", "main-container",function () {
        $("#table-title").text( window.table );
        $("#setting-field-button").trigger("click");
    }, true);
});

$(document).on("click", "#js-sdk-button", function (res) {
    var url = "createJsSDK?database="+ window.database +"&table=" + window.table;
    smarty.fopen(url, "super/result_content", true, { title: "Run Result", width:700})
});

$(document).on("click", "#api-list-button", function (res) {

    smarty.get("getSqlModelList?database="+ window.database +"&table=" + window.table, "super/api_list", "table-content",function () {
        $("#run-sql-detail").click(function () {
            var data = sparrow_form.encode( "edit-sql-detail-form",0 );
            if(data){
                if( !data.param ){
                    data.param = "{}";
                }
                data.database = window.database;
                data.table = window.table;
                runSql(data, function (response) {
                    smarty.open("super/result_content", response, { title: "Run Result", width:700}, function(){

                    });
                })
            }
        });

        $("#save-sql-detail-form").click(function () {
            var data = sparrow_form.encode( "edit-sql-detail-form",0 );
            if(data){
                data.databaseName = window.database;
                data.tableName = window.table;
                saveSql(data, function (response) {
                    console.log(response);
                })
            }
        });

        $(".run-sql-test").click(function () {
            var id = $(this).data("id");
            var data = {};
            data.database = window.database;
            data.table = window.table;
            data.param = JSON.stringify($("#sql-param-" + id).data("detail"));
            data.sqlDetail = $("#sql-detail-" + id).data("detail");
            runSql(data, function (response) {
                smarty.open("super/result_content", response, { title: "Run Result", width:700}, function(){

                });
            })
        });

        $(".remove-sql-api").click(function () {
            var id = $(this).data("id");
            removeSql(id, function (response) {

            });
        });
    });
});


$(document).on("click", "#search-form-button", function (res) {
    var url = "getSearchForm?database=" + window.database + "&table=" + window.table;
    smarty.get(url, "super/search_form", "table-content", function(){
        $("#save-search-form").click(function (res) {
            var data = sparrow_form.encode( "searchForm-form",0 );
            var detail = {};
            detail.id = $(this).data("id");
            detail.htmlContent = data.html_content;
            detail.active = data.active;
            detail.databaseName = window.database;
            detail.tableName = window.table;
            updateSearchForm(detail, function (response) {

            });
        });
        $("#search-form-preview").click(function () {
            var content = $("[name='html_content']").val();
            $("#form-html-content").html(content);
        });
    });
});


$(document).on("click", "#setting-field-button", function (res) {
    var url = "getColumnExtendList?database=" + window.database + "&table=" + window.table;
    smarty.get(url, "super/field_set", "table-content", function(){

    });
});

$(document).on("click", "#edit-table-name-button", function () {
    var url = "getTableExtendDetail?database=" + window.database + "&table=" + window.table;
    smarty.fopen(url,"super/table_edit",true,{ title: "Alias",width:700}, function () {

    });
});

$(document).on("click", "#edit-table-name-submit", function () {
    var data = sparrow_form.encode( "table-alias-form",0 );
    if( data.note ){
        data.databaseName = window.database;
        data.tableName = window.table;
        updateTableExtend(data, function (data) {
            setTimeout(function(){
                layer.closeAll();
            }, 3000);
        })
    }
});

$(document).on("click", ".edit-field-button", function (res) {
    var detail = $(this).data("detail");
    console.log(detail);
    smarty.open("super/field_edit", detail, { title: "Edit",width:700}, function () {

    });
});

$(document).on("click", "#save-edit-field", function (res) {
    var data = sparrow_form.encode( "edit-field-form",0 ); //0 表示所有字段都提交， 2 表示有改变的才提交
    data.databaseName = window.database;
    data.tableName = window.table;
    updateFieldExtend(data, function (data) {
        setTimeout(function(){
            layer.closeAll();
        }, 3000);
    });
});