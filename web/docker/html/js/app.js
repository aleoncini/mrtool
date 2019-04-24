function getReport() {
    var theUrl = '/mrtool/rs/pdf';
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