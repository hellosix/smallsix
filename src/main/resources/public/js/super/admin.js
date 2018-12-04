/**
 * Created by lzz on 2018/10/1.
 */

$(document).on("click", ".load-iframe", function () {
    var data = {};
    data.src = $(this).data("src");
    smarty.html("super/load_iframe", data, "main-container", function () {

    });
});

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
                data.id = $("#save-sql-detail-form").data("id");
                saveSql(data, function (response) {
                    $("#api-list-button").click();
                })
            }
        });

        $(".run-sql-test").click(function () {
            var id = $(this).data("id");
            var data = {};
            data.database = window.database;
            data.table = window.table;
            var param_detail = $("#sql-param-" + id).data("detail");
            if( !param_detail ){
                data.param = "{}";
            }else{
                data.param = JSON.stringify(param_detail);
            }
            data.sqlDetail = $("#sql-detail-" + id).data("detail");
            runSql(data, function (response) {
                smarty.open("super/result_content", response, { title: "Run Result", width:700}, function(){

                });
            })
        });

        $(".remove-sql-api").click(function () {
            var id = $(this).data("id");
            sparrow_win.confirm("确定删除？", function(){
                removeSql(id, function (response) {
                    $("#api-list-button").click();
                });
            })
        });

        $(".edit-sql-api").click(function () {
            var id = $(this).data("id");
            $("#save-sql-detail-form").data("id", id);
            getSqlModel(id, function (response) {
                console.log(response);
                var res = response.res;
                $('[name="sqlDetail"]').val(res.sqlDetail);
                $('[name="param"]').val(res.param);
                $('[name="apiName"]').val(res.apiName);
                $('[name="note"]').val(res.note);
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
    smarty.get(url, "super/field_list", "table-content", function(){
        $(".edit-field-button").click(function (res) {
            var field = $(this).data("field");
            getColumnExtendDetail(window.database, window.table, field, function (obj) {
                smarty.open("super/field_edit", {res: obj.res}, { title: "Edit",width:700}, function () {
                    $("#update-edit-field").click(function (res) {
                        var data = sparrow_form.encode( "edit-field-form",0 ); //0 表示所有字段都提交， 2 表示有改变的才提交
                        data.databaseName = window.database;
                        data.tableName = window.table;
                        updateFieldExtend(data, function (obj) {
                            setTimeout(function(){
                                layer.closeAll();
                                $("#setting-field-button").click();
                            }, 3000);
                        });
                        data.table = window.table;
                        data.database = window.database;
                        data.cname  = data.fieldName;
                        data.comment = data.note;
                        updateColumn(data, function (obj) {
                            setTimeout(function(){
                                layer.closeAll();
                                $("#setting-field-button").click();
                            }, 3000);
                        });
                    });
                });
            });
        });

        $("#add-field").click(function (res) {
            getColumnExtendInit(window.database, window.table, function (obj) {
                smarty.open("super/field_add", obj ,{ title: "Edit",width:700}, function () {
                    $("#add-edit-field").click(function (res) {
                        var data = sparrow_form.encode( "edit-field-form",0 ); //0 表示所有字段都提交， 2 表示有改变的才提交
                        data.table = window.table;
                        data.database = window.database;
                        data.cname  = data.fieldName;
                        data.comment = data.note;
                        data.field = data.fieldName;
                        addColumn(data, function (obj) {
                            data.databaseName = window.database;
                            data.tableName = window.table;
                            addFieldExtend(data, function (obj) {
                                setTimeout(function(){
                                    layer.closeAll();
                                    $("#setting-field-button").click();
                                }, 3000);
                            });
                        });
                    });
                });
            });
        });

        $(".remove-field-button").click(function () {
            var name = $(this).data("field");
            var id = $(this).data("id");
            sparrow_win.confirm("确定删除？", function(){
                deleteFiedldExtendById(id, function(){
                    var data = {};
                    data.table = window.table;
                    data.database = window.database;
                    data.cname  = name;
                    removeColumn(data, function () {
                        $("#setting-field-button").click();
                    });
                });
            });
        });
    });
});