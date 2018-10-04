/**
 * Created by lzz on 2018/10/4.
 */

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
                if( type == "image" || type == "music" || type == "video"){
                    res += '<div class="form-group">';
                    res += '<label class="col-sm-2 control-label">' + note + '</label>';
                    res += '<div class="col-sm-10">';
                    res += '<div class="upload" action="multiUpload" data-plugin="'+ type +'" data-file="file" data-type="png,jpg,jpeg,gif,mp4,mp3" data-name=' + field + ' data-value="'+ value +'"></div>';
                    res += '</div>';
                    res += '</div>';
                }else if( type == "timestamp" ){
                    res += '<div class="form-group">';
                    res += '<label class="col-sm-2 control-label">' + note + '</label>';
                    res += '<div class="col-sm-10">';
                    res += '<input type="text" class="form-control" name="' + field + '" value="' + timestampToDate(value) + '" />';
                    res += '</div>';
                    res += '</div>';
                }else if( type == "textarea" ){
                    res += '<div class="form-group">';
                    res += '<label class="col-sm-2 control-label">' + note + '</label>';
                    res += '<div class="col-sm-10">';
                    res += '<textarea type="text" class="form-control" name="' + field + '" >' + value + '</textarea>';
                    res += '</div>';
                    res += '</div>';
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
    if( fieldArr ){
        for(var i in fieldArr){
            if(fieldArr[i].fieldName == field){
                var type = fieldArr[i].type;
                if( type == "image" ){
                    var imgs = value.split(",");
                    for(var j in imgs){
                        if( imgs[j] ){
                            res += '<img class="img-item" ' + fieldArr[i].style + ' src="' + window.videourl + imgs[j] + '" width="30" height="30">';
                        }
                    }
                }else if( type == "video" ){
                    var videos = value.split(",");
                    for(var j in videos){
                        if( videos[j] ){
                            res += '<video class="video-item" ' + fieldArr[i].style+ ' controls="controls" src="' + window.imageurl + videos[j] + '" width="100" height="100"></video>';
                        }
                    }
                }else if( type == "music" ){
                    var musics = value.split(",");
                    for(var j in musics){
                        if( musics[j] ){
                            res += '<audio class="music-item" ' + fieldArr[i].style + ' controls="controls" src="' + window.musicurl + musics[j] + '"></audio>';
                        }
                    }
                }else if( type == "timestamp" ){
                    res += "<div " + fieldArr[i].style + ">" + timestampToDate(value) + "</div>";
                }
            }
        }
    }
    if( !res ){
        res = value;
    }
    return res;
}