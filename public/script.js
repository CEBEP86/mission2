{
    var notes=2;
    var div={};
    function save() {
        ////найти обьекты-поместить в массив их значения-передать
        var customers = [];

        for (var i = 1; i < notes + 1; i++) {

            div = document.getElementById('note' + i);
            if (div.value != null)
                if(div!= null)
            var customer={
                'id':i,
                'text':div.value
            };

            customers.push(customer);

        }


        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'POST',
            url: 'http://localhost:8080/save',
            data: JSON.stringify(customers),
            success: function (data) {
                $('#results').html(data.text);
            },
            error: function (xhr) {
                alert('�������� ������: ' + xhr.responseCode);
            }
        });
    }

    function load() {

        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'GET',
            url: 'http://localhost:8080/load',
            success: function (data) {

                ///уровнять колличество окон
                for (var i = 0;data.length != notes;  i++) {
                    if (data.length > notes) add();
                    if (data.length < notes) del();
                }

                ///вставить результат
                for (var i = 0; i < data.length;  i++) {
                    div = document.getElementById('note' + (i+1));
                    var customer = {
                        'id': i,
                        'text': div.value
                    };
                    customer = data[i];
                    div.value = customer['text'];
                }
            },
            error: function (xhr) {
                alert('�������� ������: ' + xhr.responseCode);
            }
        });

    }

    function add() {
        var l = document.createElement('li');
        l.id = "l";
        var o = document.createElement('textarea');
        notes++;
        o.id = 'note' +notes;
        o.tagName ="lili";
            list.appendChild(l);
        l.appendChild(o);
    }

    function del() {
        div = document.getElementById('list');
        var elems = div.getElementsByTagName('*');
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        var elems = div.getElementsByTagName('*');
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        notes--;
    }
}




