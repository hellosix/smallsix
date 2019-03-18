/**
 * Created by lzz on 2018/10/1.
 */

$(document).on("click", ".table-content", function (res) {
    window.table = $(this).data("table");
    /*
    getTableExtend(window.database, window.table, function (obj) {

    });
    */
    show_table_content();
});

function show_table_content() {
    var data = {};
    data.table = window.table;
    data.database = window.database;
    data.page = 1;
    data.conditions = [];
    data.sortList = [];
    window.queryConditions = [];
    window.sortList = [];
    smarty.post("getTableRowList",JSON.stringify(data), "admin/table_content_div", "main-container",function (res) {
        plugin_show_list_init();

        /*固定表格左边和右边列*/
        $('[role="c-table"]').jqTable({
            fixedLeft: true,
            fixedRight: true
        });

    });
}
$(document).on("click", "#add-field-button", function () {
    addRow(window.database, window.table);
});

function addRow(database, table) {
    getInitFieldForm(window.database, window.table, function (data) {
        add_mode(data.res);
    });
}

$(document).on("click", ".edit-field-button", function () {
    var id = $(this).data("id");
    editRow(window.database, window.table, id);
});

function editRow(database,table, id) {
    getTableRowDetail(database,table, id, function (obj) {
        var data = {};
        data.columns = obj.res.columns;
        data.extends = obj.res.fieldExtends;
        data.fields = obj.res.detail;
        var tableExtend = obj.res.tableExtend;
        var options = { "title": "修改","width":700, "height":600};
        if( tableExtend.options && tableExtend.options != ""){
            options = JSON.parse(tableExtend.options);
            options.title = "修改" + options.title;
        }
        update_model(id, data, options);
    });
}

function update_model(id, data, options) {
    smarty.open("/admin/field_form", data, options, function(){
        plugin_init(data);
        init_form_validate();

        $("#submit-field-form").click(function () {
            var formdata = sparrow_form.encode( "field-form", 1 );
            if ( sparrow.empty( formdata ) ) {
                return false;
            }else{
                var req = {};
                req.database = window.database;
                req.table = window.table;
                req.fieldMap = formdata;
                req.fieldMap.id = id;
                req.columns = data.columns;

                updateFieldForm(req, function(response){
                    closeAll();
                    layer.msg( "修改成功!", function () {
                        getTableRowDetail(window.database,window.table, id, function (obj) {
                            var data = obj;
                            data.row = data.res.detail;
                            console.log(data);
                            smarty.html("admin/table_content_row", data,"row-" + id, function(){
                                var param = {};
                                param.fields = data.row;
                                param.columns = obj.res.columns;
                                param.extends = obj.res.fieldExtends;
                                //plugin_init(param);
                                plugin_show_list_init();
                            });
                        });
                    });
                })
            }
        });
    });
}

function add_mode(data) {
    var options = { "title": "添加","width":700, "height":600};
    var tableExtend = data.tableExtend;
    if( tableExtend.options && tableExtend.options != ""){
        options = JSON.parse(tableExtend.options);
        options.title = "添加" + options.title;
    }
    smarty.open("/admin/field_form", data, options, function(){
        plugin_init(data);
        init_form_validate();

        $("#submit-field-form").click(function () {
            var formdata = sparrow_form.encode( "field-form", 1 );
            if ( sparrow.empty( formdata ) ) {
                return false;
            }else{
                var req = {};
                req.database = window.database;
                req.table = window.table;
                req.fieldMap = formdata;
                req.columns = data.columns;

                addFieldForm(req, function(response){
                    closeAll();
                    layer.msg( "添加成功!", function () {
                        show_table_content();
                    });
                })
            }
        });
    });
}

$(document).on("click", ".delete-row-button", function () {
    var id = $(this).data("id");
    deleteRow(window.database, window.table, id);
});

function deleteRow(database, table, id) {
    sparrow_win.confirm("确定删除？", function(){
        deleteRow(database, table, id, function (obj) {
            layer.msg("删除成功", function () {
                $("#row-" + id).remove();
            });
        });
    });
}

smarty.register_function('header_column', function (params) {
    var res = params['res'];
    var columns = res.columns;
    var arr = res['fieldExtends'];
    var colstr = "";
    for(var key in  columns){
        var field =columns[key].cname;
        if( !sparrow.empty(arr) ){
            var exist = false;
            for(var i in arr){
                if(arr[i].fieldName == field){
                    exist = true;
                    if(arr[i].active != 0){
                        colstr += '<col name="">';
                    }
                }
            }
            if( !exist ){
                colstr += '<col name="">';
            }
        }
    }
    return colstr;
});

smarty.register_function( 'header_note', function( params ){
    var res = params['res'];
    var columns = res.columns;
    var arr = res['fieldExtends'];
    var res = "";
    for(var key in  columns){
        var field =columns[key].cname;
        if( !sparrow.empty(arr) ){
            for(var i in arr){
                if( field == "id" ){
                    res += "<th><div class='cell'>ID<span data-field='" + field + "' class='table-sort-button icon-sort-by-attributes'></span></div></th>";
                    break;
                }else if(arr[i].fieldName == field){
                    if(arr[i].active == 1){
                        if( !sparrow.empty(arr[i].note) ){
                            if( arr[i].type == "checkbox" || arr[i].type == "select" || arr[i].type == "radio"){
                                var tmp = '<select class="form-control head-select search-select" data-field="' + field + '">'+
                                    '<option value="all">' + arr[i].note + '</option>';
                                if( !sparrow.empty(arr[i].valueInit) ){
                                    var values = JSON.parse(arr[i].valueInit);
                                    for(var n in values){
                                        var key = values[n]["key"];
                                        var value = values[n]["value"];
                                        tmp += "<option value='" + value + "'>" + key + "</option>";
                                    }
                                }
                                tmp += '</select> ';

                                res += "<th><div class='cell'>" + tmp + "</div></th>";
                            }else{
                                res += "<th><div class='cell'>" + arr[i].note + "<span data-field='" + field + "' class='table-sort-button icon-sort-by-attributes'></span></div></th>";
                            }
                        }else{
                            res += "<th><div class='cell'>" + field + "<span data-field='" + field + "' class='table-sort-button icon-sort-by-attributes'></span></div></th>";
                        }
                    }
                }
            }
        }
    }

    return  res;
});
