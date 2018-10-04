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
                var typeStr = fieldArr[i].type;
                var typeObject = JSON.parse( typeStr );
                if( typeObject.type == "image" ){
                    res += '<div class="form-group">';
                    res += '<label class="col-sm-2 control-label">' + note + '</label>';
                    res += '<div class="col-sm-10">';
                    res += '<div class="upload" action="multiUpload" data-file="file" data-type="png,jpg,jpeg,gif,mp4,mp3" data-name=' + field + ' data-value="'+ value +'"></div>';
                    res += '</div>';
                    res += '</div>';
                }else{
                    res += '<div class="form-group">';
                    res += '<label class="col-sm-2 control-label">' + note + '</label>';
                    res += '<div class="col-sm-10">';
                    res += '<input type="text" class="form-control disabled" name="' + field + '" value="' + value + '" />';
                    res += '</div>';
                    res += '</div>';
                }
            }
        }
    }
    return res;
}