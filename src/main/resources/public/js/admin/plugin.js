/**
 * Created by lzz on 2018/10/4.
 */

// 预加载模版
smarty.parse("/plugin/multi_image", {}, function (obj) {
    layer.closeAll();
});

smarty.parse("/plugin/single_image", {}, function (obj) {
    layer.closeAll();
});

smarty.parse("/plugin/editor_small", {}, function (obj) {
    layer.closeAll();
});
smarty.parse("/plugin/editor_big", {}, function (obj) {
    layer.closeAll();
});

smarty.parse("/plugin/textarea", {}, function (obj) {
    layer.closeAll();
});

smarty.parse("/plugin/date", {}, function (obj) {
    layer.closeAll();
});

smarty.parse("/plugin/time", {}, function (obj) {
    layer.closeAll();
});

smarty.parse("/plugin/radio", {}, function (obj) {
    layer.closeAll();
});

smarty.parse("/plugin/checkbox", {}, function (obj) {
    layer.closeAll();
});

smarty.parse("/plugin/select", {}, function (obj) {
    layer.closeAll();
});
smarty.parse("/plugin/input", {}, function (obj) {
    layer.closeAll();
});
smarty.parse("/plugin/number", {}, function (obj) {
    layer.closeAll();
});
smarty.parse("/plugin/range", {}, function (obj) {
    layer.closeAll();
});
smarty.register_modifier("format_editor_value", function (val) {
    if(!sparrow.empty(val)){
        val = val.replace(/[\n\r]/g,"<br>");
    }
    return val;
});
smarty.register_function('checkbox_show_value', function (params) {
    var valueInit = params['valueInit'];
    var value = params['value'];
    var field = params['field'];
    var valitate = params['valitate'];
    var res = "";
    if( sparrow.empty(valueInit) ){
        return res;
    }
    for(var i = 0; i < valueInit.length; i++){
        var target = valueInit[i];
        res += '<label class="checkbox-inline">';
        res += '<input type="checkbox" name="' + field + '" value="' + target.value + '"';
        if( i == 0 ){
            res += " data-min-check='1' data-select-default='" + value + "' " + valitate + " ";
        }
        res += 'value="' + value + '"/>' + target.key;
        res += '</label>';
    }
    return res;
});

smarty.register_function( 'field_plugin', function( params ){
    var field = params['field'];
    var value = params['value'];
    var fieldArr = params['field_extend'];
    return show_field_plugin(field, value, fieldArr);
});

function show_field_plugin(field, value, fieldArr) {
    var tag = "";
    if( field == "id" ){
        tag = "hidden";
    }
    var res = "";
    res += '<div class="form-group '+ tag +'">';
    res += '<label class="col-sm-2 control-label">' + field + '</label>';
    res += '<div class="col-sm-10">';
    res += '<input type="text" class="form-control disabled" name="' + field + '" value="' + value + '" />';
    res += '</div>';
    res += '</div>';
    if( field == "id" ){
        return res;
    }
    var r = field_plugin(field, value, fieldArr);
    if( r ){
        res = r;
    }
    return res;
}

function field_plugin(field, value, fieldArr) {
    var res = '';
    if( fieldArr ){
        for(var i in fieldArr){
            if(fieldArr[i].fieldName == field){
                var note = field;
                if( fieldArr[i].note ){
                    note = fieldArr[i].note;
                }
                var type = fieldArr[i].type;

                var valueInit = [];
                if(fieldArr[i].valueInit && fieldArr[i].valueInit != ""){
                    valueInit = JSON.parse(fieldArr[i].valueInit);
                }
                var data = {"field":field, "value":value, "note":note, "type": type,"valitate":fieldArr[i].valitate, "valueInit":valueInit};
                console.log(data);
                console.log( data.valueInit );
                if( type == "multi-image" ){
                    smarty.parse("/plugin/multi_image", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "single-image" || type == "text-file" || type == "music" || type == "video"){
                    smarty.parse("/plugin/single_image", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "time" ){
                    smarty.parse("/plugin/time", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "textarea" ){
                    smarty.parse("/plugin/textarea", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "editor-small" ){
                    smarty.parse("/plugin/editor_small", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "editor-big" ){
                    smarty.parse("/plugin/editor_big", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "date" ){
                    smarty.parse("/plugin/date", data, function (obj) {
                        res = obj;
                    });
                } else if( type == "radio" ){
                    smarty.parse("/plugin/radio", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "checkbox" ){
                    smarty.parse("/plugin/checkbox", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "select" ){
                    smarty.parse("/plugin/select", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "number" ){
                    smarty.parse("/plugin/number", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "range" ){
                    smarty.parse("/plugin/range", data, function (obj) {
                        res = obj;
                    });
                }else{
                    smarty.parse("/plugin/input", data, function (obj) {
                        res = obj;
                    });
                }
            }
        }
    }
    return res;
}


smarty.register_function( 'field_format', function( params ){
    var field = params['field'];
    var value = params['value'];
    var fieldArr = params['field_extend'];
    var id = params['id'];
    var res = field_format(field, value, fieldArr, id);
    if( sparrow.empty(res) ){
        return "";
    }
    return "<td class=''><div class='cell'>" + res + "</div></td>";
});

function field_format(field, value, fieldArr, id) {
    var res = '';
    if( fieldArr ){
        for(var i in fieldArr){
            var fieldItem = fieldArr[i];
            if(fieldItem.fieldName == field){
                var type = fieldArr[i].type;
                var active = fieldArr[i].active;
                if( active == 0 ){
                    return res;
                }
                if( type == "single-image" || type == "multi-image" ){
                    if(!sparrow.empty(value)){
                        var imgs = value.split(",");
                        for(var j = 0; j < imgs.length; j++){
                            if( imgs[j] ){
                                var hidestr = "";
                                if( j > 2 ){
                                    hidestr = "hidden";
                                }
                                res += '<img class="img-item ' + hidestr + '" ' + fieldItem.style + ' src="' + window.imageurl + imgs[j] + '" width="30" height="30">';
                            }
                        }
                        res = "<div class='img-div' data-detail='" + value +"'>" + res + "</div>";
                        res += "<div class='add-image-icon'  data-target='" + field + "' data-id='" + id + "'><span class='glyphicon glyphicon-pencil'></span></div>";
                    }
                }else if( type == "text-file"){
                    res += "<div class='text-file-link' data-src='" + value + "' " + fieldItem.style + ">查看</div>";
                    res += "<div class='text-file-edit-icon'  data-target='" + field + "' data-id='" + id + "'>编辑</div>";
                    res = "<div class='width-60'>" + res + "</div>"
                }else if( type == "video" ){
                    if(!sparrow.empty(value)){
                        var videos = value.split(",");
                        for(var j in videos){
                            if( videos[j] ){
                                res += '<video class="video-item" ' + fieldItem.style+ ' controls="controls" src="' + window.videourl + videos[j] + '" width="100" height="100"></video>';
                            }
                        }
                    }
                }else if( type == "music" ){
                    if(!sparrow.empty(value)){
                        var musics = value.split(",");
                        for(var j in musics){
                            if( musics[j] ){
                                res += '<audio class="music-item" ' + fieldItem.style + ' controls="controls" src="' + window.musicurl + musics[j] + '"></audio>';
                            }
                        }
                    }
                }else if( type == "time" ){
                    res += "<div  " +fieldItem.style+ " class='date-edit-icon'  data-target='" + field + "' data-id='" + id + "'>"+ timestampToDateTime(value) + "</div>";
                }else if( type == "date" ){
                    res += "<div " +fieldItem.style+ " class='date-edit-icon'  data-target='" + field + "' data-id='" + id + "'>"+ timestampToDate(value) +"</div>";
                }else if( type == "checkbox" ){
                    res += "<div " + fieldItem.style + " class='checkbox-edit-icon'  data-target='" + field + "' data-id='" + id + "'>";
                    if( !sparrow.empty(value) && !sparrow.empty(fieldItem.valueInit) ){
                        var tmpArr = [];
                        var arr = JSON.parse(fieldItem.valueInit);
                        var items = value.split(",");
                        for(var j in items){
                            var itemValue = items[j];
                            for(var n in arr){
                                if( arr[n].value == itemValue ){
                                    tmpArr.push(arr[n].key);
                                    break;
                                }
                            }

                        }
                        res += tmpArr.join(",") + "</div>";
                    }else{
                        res += value + "</div>";
                    }

                }else if( type == "number"){
                    res += "<input type='number' " + fieldItem.style + " value='" + value + "' class='no-border-input edit-input'   data-field='" + field + "' data-id='" + id + "' />";
                }else if( type == "range"){
                    res += "<div  " +fieldItem.style+ " class='range-edit-icon'  data-target='" + field + "' data-id='" + id + "'>"+ value + "</div>";
                }else if( type == "radio" ){
                    if( !sparrow.empty(fieldItem.valueInit) ){
                        var arr = JSON.parse(fieldItem.valueInit);
                        var is_checked = false;
                        var on = arr[0]["key"];
                        var on_value = arr[0]["value"];
                        if( on_value == value ){
                            is_checked = true
                        }
                        var off = arr[1]["key"];
                        var off_value = arr[1]["value"];
                        res += "<div class='switch'>";
                        res += '<input type="checkbox" data-id="' + id + '" data-checked="' + is_checked+ '" data-on-value="' + on_value + '" data-off-value="' + off_value + '" data-field="' + field + '" data-off-text="' + off + '" data-on-text="' + on + '" >';
                        res += '</div>';
                    }

                }else if( type == "textarea" || type == "editor-small"){
                    res += "<div " + fieldItem.style +" class='field-ellipsis pull-left'>" + value + "</div>";
                    res += "<div class='textarea-edit-icon'  data-target='" + field + "' data-id='" + id + "'><span class='glyphicon glyphicon-pencil'></span></div>";
                }else if(type == "editor-big"){
                    res += "<div class='textarea-big-preview' data-target='" + field + "' data-id='" + id + "' " + fieldItem.style + ">查看</div>";
                    res += "<div class='textarea-big-edit-icon'  data-target='" + field + "' data-id='" + id + "'>编辑</div>";
                    res = "<div class='width-60'>" + res + "</div>"
                }else{
                    if( field != "id" ){
                        res += "<input type='text' " + fieldItem.style + " value='" + value + "'  class='no-border-input edit-input'   data-field='" + field + "' data-id='" + id + "' />";
                    }else{
                        res = "<div class='id-field'>" + value + "</div>";
                    }
                }
            }
        }
    }
    if( !res ){
        if( field == "id" ){
            res = "<div class='id-field'>" + value + "</div>";
        }else{
            res = "<div class='field-ellipsis'>" + value + "</div>";
        }
    }
    return res;
}


function plugin_init(data) {
    setTimeout(function () {
        var fieldObj = data.extends;
        for(var i = 0; i < fieldObj.length; i++){
            var type = fieldObj[i].type;
            var name = fieldObj[i].fieldName;
            if( type == 'single-image' || type == 'multi-image' || type == 'text-file' || type == 'music' || type == 'video' ){
                $("[data-name='" + name + "']").upload(
                    function(_this,data){
                        alert(data)
                    }
                );
            }
        }
        time_init();
        date_init();

        window.editor = {};
        editor_init("editor-small");
        editor_init("editor-big");
    }, 100 );

}

function time_init() {
    $("[data-plugin='time']").each(function (i) {
        var value = $(this).val();
        var format_value = timestampToDate( parseInt(value) );
        $(this).val(format_value);
    });

    laydate.render({
        elem: "[data-plugin='time']",
        type: 'datetime'
    });
}

function date_init() {
    $("[data-plugin='date']").each(function (i) {
        var value = $(this).val();
        var format_value = timestampToDateTime( parseInt(value) );
        $(this).val(format_value);
    });
    laydate.render({
        elem: "[data-plugin='date']",
        type: 'date'
    });
}
function editor_init(type) {
    $( '[data-plugin="' + type + '"]').each(function (i) {
        var E = window.wangEditor;
        var field = $('[data-plugin="' +  type + '"]').attr("name");
        var editor = null;
        if( type == "editor-small" ){
            editor = new E('.button-editor-toolbar', '[data-plugin="' + type + '"]');
            // 自定义菜单配置
            editor.customConfig.menus = [
                'emoticon',
                'link',
                'foreColor'
            ];
        }else{
            editor = new E('.button-editor-toolbar', '[data-plugin="' + type + '"]');
        }
        editor.customConfig.uploadImgShowBase64 = true;
        editor.create();
        window.editor[ field ] = editor;
    });
}

function plugin_show_list_init() {
    var init = true;
    $('.switch > input').each(function () {
        $(this).not("[data-switch-no-init]").bootstrapSwitch({
            size: "mini",
            onSwitchChange: function(event, state) {
                if( init == false ){
                    var target = $(event.target);
                    var field = target.data("field");
                    var id = target.data("id");
                    var off = target.data("off-value");
                    var on = target.data("on-value");
                    var value = off;
                    if( state == true ){
                        value = on;
                    }
                    var req = {};
                    req.database = window.database;
                    req.table = window.table;
                    req.fieldMap = {};
                    req.fieldMap[field] = value;
                    req.fieldMap.id = id;
                    ajax.get( "getTableRowDetail?database=" + window.database + "&table=" + window.table + "&id=" + id, function (obj) {
                        req.columns = obj.res.columns;
                        updateFieldForm(req, function(response){
                            console.log(response)
                        });
                    });

                }
                return;
            }
        });
        var is_checked = $(this).data("checked");
        $(this).not("[data-switch-no-init]").bootstrapSwitch("state", is_checked);
    });
    init = false;

}

$(document).on("click",".textarea-big-preview", function () {
    var id = $(this).data("id");
    var field = $(this).data("target");
    getInitFieldForm(window.database, window.table, function (data) {
        var tableExtend = data.res.tableExtend;
        getTableRowDetail(window.database, window.table, id, function (obj) {
            var options = { "title": "修改","width":700, "height":600};
            if( tableExtend.options && tableExtend.options != ""){
                options = JSON.parse(tableExtend.options);
                options.title = "预览";
            }
            smarty.open("/plugin/show_big_textarea", {"content":obj.res.detail[field]}, options, function(){

            })
        })
    });
});

$(document).on("click", ".text-file-link", function () {
    var src = $(this).data("src");
    var content = getFileData( window.musicurl + src );
    var options = { "title": "查看详情","width":500,"height":500};
    smarty.open("/plugin/show_text_file", {"content":content}, options, function(){

    })
});

$(document).on("click", ".img-div", function () {
    var data = {};
    var detail = $(this).data("detail");
    var param = {
        "title": "", //相册标题
        "id": 123, //相册id
        "start": 0, //初始显示的图片序号，默认0
        "data": [   //相册包含的图片，数组格式

        ]
    };
    var arr = detail.split(",");
    for(var i in arr){
        var item = {
            "alt": arr[i],
            "pid": arr[i],
            "src": window.imageurl + arr[i],
            "thumb": window.imageurl + arr[i]
        }
        param.data.push(item);
    }

    //调用示例
    layer.photos({
        photos: param
        ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
    });
});


$(document).on("change",".edit-input",function () {
    var field = $(this).data("field");
    var id = $(this).data("id");
    var req = {};
    req.database = window.database;
    req.table = window.table;
    req.fieldMap = {};
    req.fieldMap[field] = $(this).val();
    req.fieldMap.id = id;
    ajax.get( "getTableRowDetail?database=" + window.database + "&table=" + window.table + "&id=" + id, function (obj) {
        req.columns = obj.res.columns;
        updateFieldForm(req, function(response){
            console.log(response)
        });
    });
});


$(document).on("click", ".checkbox-edit-icon,.add-image-icon,.textarea-edit-icon,.textarea-big-edit-icon,.date-edit-icon,.text-file-edit-icon", function () {
    var id = $(this).data("id");
    var field = $(this).data("target");
    editField(window.database, window.table, id, field);

});

function  editField(database, table, id, field) {
    ajax.get( "getTableRowDetail?database=" + database + "&table=" + table + "&id=" + id, function (obj) {
        var data = {};
        data.columns = obj.res.columns;
        data.extends = obj.res.fieldExtends;
        data.fields = {};
        var tableExtend = obj.res.tableExtend;

        for(var key in obj.res.detail){
            if( key == field ){
                data.fields[key] = obj.res.detail[key];
                break;
            }

        }
        var options = { "title": "修改" ,"width":700};
        if( tableExtend.options && tableExtend.options != ""){
            options = JSON.parse(tableExtend.options);
            options.title = "修改" + options.title;
        }
        update_model(id, data, options);
    });
}