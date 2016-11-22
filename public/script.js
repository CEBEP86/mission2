function save() {
    var Customer= {
        'text': $('#text').val() ,
     };
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        type: 'POST',
        url: 'http://localhost:8080/save',
        data: JSON.stringify(Customer),
        success: function(data) {
            $('#results').html(data.text);
        },
        error:  function(xhr, str){
            alert('�������� ������: ' + xhr.responseCode);
        }
    });

}
function load() {
    var Customer= {
        'text': $('#text').val() ,
    };
    var msg   = $('#text').serialize();
    $.ajax({
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        type: 'POST',
        url: 'http://localhost:8080/load',
        data: JSON.stringify(Customer),
        success: function(data) {
            $('#text').val(data.text)
            },
        error:  function(xhr, str){
            alert('�������� ������: ' + xhr.responseCode);
        }
    });

}



