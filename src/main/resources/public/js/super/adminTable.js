/**
 * Created by lzz on 2018/10/28.
 */


window.database = getQueryString("database");
smarty.get("getTableExtendByDatabase?database="+ window.database, "super/tableAdmin/table_extend_list", "main-container",function () {
    $("#add-table").click(function () {
        //( tpl, data, window_option, callback )
        smarty.open("super/tableAdmin/add_table", {}, { title: "Add table",width:700}, function () {
            $("#add-table-submit").click(function () {
                var data = sparrow_form.encode( "add-table-form",0 );
                if( data.note ){
                    data.databaseName = window.database;
                    addTableAndExtend(data, function (data) {
                        setTimeout(function(){
                            layer.closeAll();
                        }, 3000);
                    })
                }
            });
        });
    });

    $(".edit-table-extend").click(function () {
        window.table = $(this).data("table");
        var url = "getTableExtendDetail?database=" + window.database + "&table=" + window.table;
        smarty.fopen(url,"super/tableAdmin/table_edit",true,{ title: "Alias",width:700}, function () {
            $("#edit-table-name-submit").click(function () {
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

        });
    });

    $(".remove-table-extend").click(function () {
        window.table = $(this).data("table");
        sparrow_win.confirm("确定删除？", function(){
            ajax.get( "deleteTableAndExtend?database=" + window.database + "&table=" + window.table, function (obj) {
                layer.msg("删除成功");
            });
        });
    });
}, true);


