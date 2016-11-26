{
    var notes=2;
    function save() {
        ////найти обьекты-поместить в массив их значения-передать
        var div;
        var customers = [];

        for (var i = 1; i < notes + 1; i++) {
            div = document.getElementById('note' + i);
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
            error: function (xhr, str) {
                alert('�������� ������: ' + xhr.responseCode);
            }
        });
    }


    function load() {
        var Customer = {
            'text': $('#text').val(),
        };
        var msg = $('#text').serialize();
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'POST',
            url: 'http://localhost:8080/load',
            data: JSON.stringify(Customer),
            success: function (data) {

                $('#note1').val(data.text)

            },
            error: function (xhr, str) {
                alert('�������� ������: ' + xhr.responseCode);
            }
        });

    }

    function add() {
        var l = document.createElement('li')
        l.id = "l"
        var o = document.createElement('textarea')
        notes++;
        o.id = 'note' +notes;
        o.tagName ="lili";
            list.appendChild(l);
        l.appendChild(o);
    }

    function del() {
        var div = document.getElementById('list')
        var elems = div.getElementsByTagName('*')
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        var elems = div.getElementsByTagName('*')
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);

    }
}




