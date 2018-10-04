/**
 * Created by lzz on 2018/10/3.
 */


function syntaxHighlight(json) {
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&');
    json = json.replace(/</g, '<');
    json = json.replace(/>/g, '>');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}



function timestampToDate(timestamp) {
    if( timestamp < 10000000000 ){
        timestamp = timestamp * 1000;
    }
    var date = new Date(timestamp);
    Y = date.getFullYear() ;
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) ;
    D = date.getDate() ;
    h = date.getHours();
    m = date.getMinutes() ;
    s = date.getSeconds();
    if(D<10){
        D = '0'+ D;
    }
    if(h<10){
        h = '0'+ h;
    }
    if(m<10){
        m = '0'+ m;
    }
    if(s<10){
        s = '0'+ s;
    }
    return Y + '-' + M + '-' + D + ' ' + h + ':' + m + ':' + s;
}


//return smarty;
smarty.register_modifier( 'json_format', function( val ) {
    return syntaxHighlight( val );
} );
