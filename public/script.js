{
    var executors = [];
    var executorHours = [];
    var executorsIterator = 0;
    var cost = 0;
    var personal = {};
    var notes = 0;
    var div = {};

    function CreateTask() {
        var taskName = '';
        var description = '';
        var starttime;
        var finishTime;
        var responcebleID = 0;
        var cost = 0;
        taskName = document.getElementById('name');
        description = document.getElementById('description');
        starttime = document.getElementById('start');
        finishTime = document.getElementById('stop');
        responcebleID = document.getElementById('responceble');
        cost = document.getElementById('cost');
        var UTCstring = (new Date()).toUTCString();
        var customer = {
            'id': '3',
            'creatorID': '3',
            'taskName': taskName.value,
            'description': description.value,
            'startTime': UTCstring,
            'finishTime': UTCstring,
            'responcebleID': responcebleID.value,
            'cost': cost.value
        };

        $.ajax({

            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'POST',
            url: 'http://localhost:8080/create-task',
            data: JSON.stringify(customer),
            dataType: 'text',
            success: function (data) {
                $('#results1').text(data);
            },
            error: function (data) {
                alert('FILED RESPONSE CreateTask!!!  TEXT RESPONSE: ' + data);
            }
        });

        ////send executors list
        var executorsStr = [];
        for (var i = 0; i < executorHours.length; i++) {
            executorsStr[i] = {
                'workerId': executors[i],
                'hour': executorHours[i]
            };
        }
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'POST',
            url: 'http://localhost:8080/create-task-add-executors',
            data: JSON.stringify(executorsStr),
            dataType: 'text',
            success: function (data) {
                $('#results2').text(data);
                goTaskList();
            },
            error: function (data) {
                alert('FILED!!!  TEXT RESPONSE: ' + data);
            }
        });
    }

    function loadPersonalInformation() {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'GET',
            url: 'http://localhost:8080/load-personal-information',
            success: function (data) {
                personal = data;
                div = document.getElementById('executors');
                for (var i = 0; i < personal.length; i++)
                    div.options[div.options.length] = new Option(personal[i].firstName, personal[i].id);
                div = document.getElementById('responceble');
                for (var i = 0; i < personal.length; i++)
                    div.options[div.options.length] = new Option(personal[i].firstName, personal[i].id);
            },
            error: function (xhr) {
                alert('Responceble: ' + xhr.responseCode);
            }
        });
    }

    function findAllTasks() {

        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'GET',
            url: 'http://localhost:8080/find-all-tasks',
            success: function (data) {
                for (var i = 0; data.length != notes; i++) {
                    if (data.length > notes) findAllTaskAdd();
                    if (data.length < notes) findAllTaskDel();
                }
                for (var i = 0; i < data.length; i++) {
                    div = document.getElementById('note' + (i + 1));
                    var customer = {
                        'id': i,
                        'taskName': div.value
                    };
                    customer = data[i];
                    div.textContent = '  ' + customer['taskName'] + '  ';
                    var div2 = document.getElementById('delete');
                    div.id = customer['id'];
                    div2.id = div.id;
                }
            },
            error: function (xhr) {
                alert('Responceble: ' + xhr.responseCode);
            }
        });

    }

    function readTask() {
        var id = window.location.href.split("?")[1].split("=")[1];
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: 'POST',
            url: 'http://localhost:8080/read-task',
            data: JSON.stringify(id),
            dataType: 'text',
            success: function (data) {
                var customer = JSON.parse(data)
                document.getElementById('name').value = customer["taskName"];
                document.getElementById('description').value = customer['description'];
                var date = new Date(customer['startTime']);
                document.getElementById('start').value = date;
                var date = new Date(customer['finishTime']);
                document.getElementById('stop').value = date;
                document.getElementById('responceble').value = customer['responcebleID'];
                document.getElementById('cost').value = "price = " + customer['cost'];

                $.ajax({
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    type: 'GET',
                    url: 'http://localhost:8080/load-personal-information',
                    success: function (data) {
                        personal = data;
                        div = document.getElementById('responceble');
                        for (var i = 0; i < personal.length; i++)
                            if (personal[i].id == customer['responcebleID'])
                                div.options[div.options.length] = new Option(personal[i].firstName, personal[i].id);

                        div = document.getElementById('executors');

                        $.ajax({
                            contentType: "application/json; charset=utf-8",
                            data: JSON.stringify(id),
                            dataType: 'text',
                            type: 'POST',
                            url: 'http://localhost:8080/read-task-executors',
                            success: function (data) {
                                customer = JSON.parse(data)
                                for (var i2 = 0; i2 < customer.length; i2++) {
                                    var l = document.createElement('p');
                                    l.id = "p1";
                                    var name = "";
                                    for (var i3 = 0; i3 < personal.length; i3++)
                                        if (customer[i2].workerId == personal[i3].id) {
                                            name = personal[i3].firstName;
                                            break;
                                        }
                                    l.textContent = name + '   ' + customer[i2].hour + '  hours ';
                                    var list = document.getElementById('executorsList');
                                    document.getElementById('executorsList1').parentNode.removeChild(document.getElementById('executorsList1'));
                                    document.createElement('executorsList1');
                                    list.appendChild(l);
                                    l = document.createElement('p');
                                    l.id = "executorsList1";
                                    list.appendChild(l);

                                }

                            },
                            error: function (xhr) {
                                alert('Responceble read-task-executors: ' + xhr.responseCode);
                            }
                        });
                    },
                    error: function (xhr) {
                        alert('Responceble load-personal-information: ' + xhr.responseCode);
                    }
                });


            },
            error: function (xhr) {
                alert(' Responceble read-task :' + xhr.responseCode);
            }
        });


    }


    function goCreateTask() {
        window.location.href = "../create.html"
    }

    function goTaskList() {
        window.location.href = "http://localhost:8080/"
    }

    function goReadTask(obj) {
        window.location.href = "../read.html?id=" + obj.id;
    }

    function del(obj) {
        var id = obj.id;
        $.ajax({
            contentType: "application/json; charset=utf-8", dataType: "json",
            type: 'POST',
            url: 'http://localhost:8080/remove-task',
            data: JSON.stringify(id),
            dataType: 'text',
            success: function (data) {
            },
            error: function (xhr) {
                alert('Responceble: ' + xhr.responseCode);
            }
        });
        var elems = obj.parentNode.getElementsByTagName('*');
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        elems[elems.length - 1].parentNode.removeChild(elems[elems.length - 1]);
        notes--;
     }

    function findAllTaskAdd() {
        var l = document.createElement('p');
        l.id = "p1";
        notes++;
        var l2 = document.createElement('a');
        l2.id = 'note' + notes;
        l2.setAttribute('onclick', 'goReadTask(this)');
        l2.textContent = ' ' + l2.id.toString() + ' ';
        var l3 = document.createElement('input');
        l3.type = "button";
        l3.id = "delete";
        l3.value = "x";
        l3.setAttribute('onclick', 'del(this)');
        l3.class = "btn btn-primary";
        var list = document.getElementById('listik');
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


    function addExecutors() {
        var l = document.createElement('p');
        l.id = "p1";
        var name = "";
        for (var i = 0; i < personal.length; i++)
            if (document.getElementById('executors').value == personal[i].id) {
                name = personal[i].firstName;
                cost = parseFloat(document.getElementById('hours').value) * parseFloat(personal[i].tax);
                document.getElementById('cost').value = cost;
                executors[executorsIterator] = personal[i].id;
                executorHours[executorsIterator] = parseFloat(document.getElementById('hours').value);

                executorsIterator++;
                break;
            }

        l.textContent = name + '   ' + document.getElementById('hours').value;
        l.align="center";
        var list = document.getElementById('executorsList');
        document.getElementById('executorsList1').parentNode.removeChild(document.getElementById('executorsList1'));
        document.createElement('executorsList1');
        list.appendChild(l);
        l = document.createElement('p');
        l.id = "executorsList1";
        list.appendChild(l);

    }


}




