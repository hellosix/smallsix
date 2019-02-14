/*
{type,res}
type:device | admin
 */
window.addEventListener('message',function(rs){
    console.log(rs.data);
    var msgJson = JSON.parse(rs.data);
    var obj = msgJson.res;
    if( msgJson.type == "device" ){
        $("#iframe-loading").remove();
        if( obj == "mobile" ){
            $(".icon-mobile-2").click();
        }
    }else if( msgJson.type == "edit-field" ){
        var field = obj.field;
        var id = obj.id;
        window.table = obj.table;
        editField(window.database, window.table, id, field);
    }else if( msgJson.type == "add-row" ){
        window.table = obj.table;
        addRow(window.database, window.table);
    }else if( msgJson.type == "delete-row" ){
        window.table = obj.table;
        deleteRow(window.database, window.table, obj.id);
    }else if( msgJson.type == "edit-row" ){
        window.table = obj.table;
        editRow(window.database, window.table, obj.id);
    }
});