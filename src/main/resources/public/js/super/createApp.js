
$(document).on("click", "#create-app-button", function () {
    var data = sparrow_form.encode( "create-app-form",0 );
    createApp(data, function(obj){

    });
});