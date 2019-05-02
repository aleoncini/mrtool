function getReport() {
    ///rs/trips/9991/2019/3
    var theUrl = '/rs/trips';
    $.ajax({
        url: theUrl,
        type: 'GET',
        data: {},
        dataType: 'json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            console.log("====> " + data);
            $("#the_response").text(data.result);
        }
    });
};