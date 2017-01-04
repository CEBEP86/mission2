{
    var notes=0;
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
            dataType: 'text',
            success: function (data) {
               $('#results').text(data);
                },
            error: function (data) {
                alert('FILED!!!  TEXT RESPONSE: ' + data);
            }
        });
    }

    function findAllTasks() {

        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'GET',
            url: 'http://localhost:8080/findAllTasks',
            success: function (data) {

                ///уровнять колличество окон
                for (var i = 0;data.length != notes;  i++) {
                    if (data.length > notes) findAllTaskAdd();
                    if (data.length < notes) findAllTaskDel();
                }

                ///вставить результат
                for (var i = 0; i < data.length;  i++) {
                    div = document.getElementById('note' + (i+1));
                    var customer = {
                        'id': i,
                        'taskName': div.value
                    };
                    customer = data[i];
                    div.textContent = '  '+customer['taskName']+'  ';
                }
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
                    if (data.length > notes) findAllTaskAdd();
                    if (data.length < notes) findAllTaskDel();
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


    function goCreateTask() {
        window.location.href="../create.html"
    }

    function goTaskList() {
        window.location.href="http://localhost:8080/"
    }


    function findAllTaskAdd() {
        var l = document.createElement('p');
        l.id="p1";
        var l1 = document.createElement('input');
        l1.type = "checkbox";
        notes++;
       // l1.id =  'note' +notes;
        l1.tagName ="lili";
        var l2 = document.createElement('STRONG');
     //   l2.id=l1.id;
        l2.id =  'note' +notes;
        l2.textContent=' '+l2.id.toString()+' ';
        var l3 = document.createElement('input');
        l3.type="button";
      //  l3.id =l1.id;
        l3.value="delete";
        l3.onclick="del();";
        l3.class="btn";
        l3.class="btn-default";
        var list = document.getElementById('listik');
        l.appendChild(l1);
        l.appendChild(l2);
        l.appendChild(l3);
        list.appendChild(l);

    }

    function findAllTaskDel() {
        div = document.getElementById('listik');
        var elems = div.getElementsByTagName('*');
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        notes--;

    }
}




