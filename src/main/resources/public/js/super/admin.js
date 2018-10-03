/**
 * Created by lzz on 2018/10/1.
 */

smarty.get("getMenu", "super/super_menu", "sidebar-menu",function () {
    initMenu();

}, true);

$(document).on("click", ".table-detail", function(res){
    var table = $(this).data("table");
    window.table = table;
    smarty.get("getApiList?database=a&table=b", "super/table_content", "main-container",function () {
        $("#table-title").text( window.table );
        $("#setting-field-button").trigger("click");
    }, true);
});

$(document).on("click", "#api-list-button", function (res) {
    $(document).on('input propertychange focus', '#sql-textarea', function() {
        //$(this).highlightTextarea('setWords', ['select','table']);
        sql_init();
    });
    smarty.html("super/api_list", {}, "table-content", function(){
        sql_init();
    });
});

$(document).on("click", "#js-sdk-button", function (res) {
    smarty.html("super/js_sdk", {}, "table-content", function(){

    });
});

function sql_init() {
    $("#sql-textarea").highlightTextarea({
        words: [{
            color: '#ADF0FF',
            words: 'select'
        }, {
            color: '#ADF0FF',
            words: 'where'
        }, {
            color: '#FFFF00',
            words: '{'
        }, {
            color: '#FFFF00',
            words: '$'
        }, {
            color: '#FFFF00',
            words: '}'
        }, {
            words: 'limit'
        }]
    });

    var content = $("#sql-textarea").val();
    var count = content.length;
    $("#sql_input_num").text(count);
}

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
    smarty.fopen(url,"super/table_edit",true,{ title: "Alias",width:310}, function () {

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
    smarty.open("super/field_edit",detail, { title: "Edit",width:700}, function () {

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