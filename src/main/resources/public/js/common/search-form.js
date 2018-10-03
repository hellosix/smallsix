/**
 * Created by lzz on 2018/10/3.
 */

$(document).on("click", ".search-item", function () {
    //换样式 //赋值
    var group = $(this).data("group");
    var value = $(this).data("value");
    $("[data-group='" + group +"']").removeClass("active");
    $(this).addClass("active");
    $(".search-field[data-group='" + group + "']").data("value", value);
    //获取所有
    var paramList = getAllSearchParam();
    //调用模版接口
    loadSearchRes(paramList);
});


$(document).on("click", ".search-item-submit", function () {
    //换样式 //赋值
    var group = $(this).data("group");
    $("[data-group='" + group +"']").removeClass("active");
    var field = $(this).data("field");
    var min = $("[name='field-min-"+ group +"']").val();
    var max = $("[name='field-max-"+ group +"']").val();
    var param = min +" < " + field + " and " + field + " > " + max;
    $(".search-field[data-group='" + group + "']").data("value", param);
    //获取所有
    var paramList = getAllSearchParam();
    //调用模版接口
    loadSearchRes(paramList);
});


$(document).on("click", ".search-query-submit", function () {
    //换样式 //赋值
    var group = $(this).data("group");
    var value = $("[name='field-content-" + group + "']").val();
    if( value ){
        var params = [];
        $(".search-item-query.active").each(function(){
            var field = $(this).data("field");
            var tmp = field +" like '%" + value + "%'";
            params.push( tmp );
        });
        $(".search-field[data-group='" + group + "']").data("value", params.join(" or "));
    }else{
        $(".search-field[data-group='" + group + "']").data("value", "");
    }
    //获取所有
    var paramList = getAllSearchParam();
    //调用模版接口
    loadSearchRes(paramList);
});

$(document).on("click", ".search-item-query", function () {
   if( $(this).hasClass("active") ){
       $(this).removeClass("active");
   } else{
       $(this).addClass("active");
   }
});


function getAllSearchParam() {

    var paramList = [];
    $(".search-field").each(function(){
        var param = $(this).data("value");
        if( param ){
            paramList.push( param );
        }
    });
    return paramList;
}

function loadSearchRes(paramList) {
    var data = {};
    data.table = window.table;
    data.database = window.database;
    data.page = 1;
    data.conditions = paramList;
    window.queryConditions = data.conditions;
    smarty.post("getTableRowList",JSON.stringify(data), "admin/table_content", "table-container",function () {

    });
}

$(document).on("click", ".table-sort-button", function () {
    var data = {};
    data.table = window.table;
    data.database = window.database;
    data.page = 1;
    data.conditions = window.queryConditions;
    data.sortList = [];
    var field = $(this).data("field");
    if($(this).hasClass("icon-sort-by-attributes")){
        $(this).removeClass("icon-sort-by-attributes");
        $(this).addClass("icon-sort-by-attributes-alt");
        data.sortList.push( field + " asc");
    }else{
        $(this).removeClass("icon-sort-by-attributes-alt");
        $(this).addClass("icon-sort-by-attributes");
        data.sortList.push( field + " desc");
    }
    window.sortList = data.sortList;
    smarty.post("getTableRowList",JSON.stringify(data), "admin/table_content", "table-container",function () {
        var arr = data.sortList;
        for(var i in arr){
           var str = arr[i];
            var tmpArr = str.split(" ");
            if( tmpArr.length != 2 ) return;
            var field = tmpArr[0];
            if( tmpArr[1] == "asc" ){
                $(".table-sort-button[data-field='" + field + "']").removeClass("icon-sort-by-attributes");
                $(".table-sort-button[data-field='" + field + "']").addClass("icon-sort-by-attributes-alt");
            }

        }
    });
});

