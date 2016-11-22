function save() {
    ////найти обьекты-поместить в массив их значения-передать
    var  div= document.getElementById('list')
    var elems = div.getElementsByTagName('*')
    for(var i=0; i<elems.length; i++){ var elem_1=elems[i].getElementsByTagName('*'); elems[i]=elem_1[0];}
   // for(var i=0; i<elems.length; i++) elems[i]=elems[i].val();
    elems[0].id="papa";
    $('#papa').valueOf("zasada7q");

    var Customer= {
        'text': elems,
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

function add() {
    var l = document.createElement('li')
    l.id="baba"
    var o = document.createElement('textarea')
    o.id = 'text222'
    list.appendChild(l);
    l.appendChild(o);
}

function del() {
    var  div= document.getElementById('list')
    var elems = div.getElementsByTagName('*')
    elems[elems.length-1].parentNode.removeChild(elems[elems.length-1]);
    var elems = div.getElementsByTagName('*')
    elems[elems.length-1].parentNode.removeChild(elems[elems.length-1]);

}





