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

smarty.register_function('checkbox_show_value', function (params) {
    var target = params['target'];
    var value = params['value'];
    var field = params['field'];
    var res = '<input type="checkbox" name="' + field + '" value="' + target.value + '"';
    if( value.indexOf(target.value) > -1 ){
      res += " checked ";
    }
    res += 'value="' + value + '"/>' + target.key;
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
                var data = {"field":field, "value":value, "note":note, "type": type, "valueInit":valueInit};
                console.log(data);
                console.log( data.valueInit );
                if( type == "multi-image" ){
                    smarty.parse("/plugin/multi_image", data, function (obj) {
                        res = obj;
                    });
                }else if( type == "single-image" || type == "music" || type == "video"){
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
    return field_format(field, value, fieldArr);
});

function field_format(field, value, fieldArr) {
    var res = '';
    if( !sparrow.empty(value) && fieldArr ){
        for(var i in fieldArr){
            var fieldItem = fieldArr[i];
            if(fieldItem.fieldName == field){
                var type = fieldArr[i].type;
                if( type == "single-image" || type == "multi-image" ){
                    if(!sparrow.empty(value)){
                        var imgs = value.split(",");
                        for(var j in imgs){
                            if( imgs[j] ){
                                res += '<img class="img-item" ' + fieldItem.style + ' src="' + window.imageurl + imgs[j] + '" width="30" height="30">';
                            }
                        }
                        res = "<div class='img-div' data-detail='" + value +"'>" + res + "</div>"
                    }
                }else if( type == "text-file"){
                    res += "<div class='text-file-link' data-src='" + value + "' " + fieldItem.style + ">查看详情</div>";
                }else if( type == "video" ){
                    var videos = value.split(",");
                    for(var j in videos){
                        if( videos[j] ){
                            res += '<video class="video-item" ' + fieldItem.style+ ' controls="controls" src="' + window.videourl + videos[j] + '" width="100" height="100"></video>';
                        }
                    }
                }else if( type == "music" ){
                    var musics = value.split(",");
                    for(var j in musics){
                        if( musics[j] ){
                            res += '<audio class="music-item" ' + fieldItem.style + ' controls="controls" src="' + window.musicurl + musics[j] + '"></audio>';
                        }
                    }
                }else if( type == "time" ){
                    res += "<div " + fieldItem.style + ">" + timestampToDateTime(value) + "</div>";
                }else if( type == "date" ){
                    res += "<div " + fieldItem.style + ">" + timestampToDate(value) + "</div>";
                }else if( type == "checkbox" ){
                    res += "<div " + fieldItem.style + ">";
                    if( !sparrow.empty(fieldItem.valueInit) ){
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
                }else if( type == "radio" ){
                    res += "<div " + fieldItem.style + ">";
                    if( !sparrow.empty(fieldItem.valueInit) ){
                        var arr = JSON.parse(fieldItem.valueInit);
                        for(var n in arr){
                            if( arr[n].value == value ){
                                res += arr[n].key;
                                break;
                            }
                        }
                        res += "</div>";
                    }else{
                        res += value + "</div>";
                    }
                }
            }
        }
    }
    if( !res ){
        res = "<div class='field-ellipsis'>" + value + "</div>";
    }
    return res;
}


function plugin_init(data) {
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