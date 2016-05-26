<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Добавление категории</h3>
<div class="form-group input-group col-md-8">
    <input class="form-control" id="name" placeholder="Имя категории">
</div>

<div class="form-group input-group col-md-8">
    <button type="submit" class="btn btn-default pull-right" onclick="addCategory();">Добавить</button>
</div>

<table class="table table-hover table-striped" id="categoryList">
    <thead>
    <th>Название</th>
    <th>Id</th>
    </thead>
    <tbody id="categories">
    </tbody>
</table>

<script type="text/javascript">

    var categories = document.getElementById("categories");

    function addCategory() {
        $.post(context + "/api/v1/category/add", {
            name : document.getElementById("name").value
        })
                .done(function(data) {
                    switch (data.result) {
                        case "SUCCESS":
                            getCategories();
                            break;
                        default:
                            show_message(error_unknown, 'danger');
                    }
                });
    }

    function getCategories() {
        $.get(context + "/api/v1/category/all", {})
                .done(function(data) {
                    categories.innerHTML = '';
                    for (var id in data) {
                        var object = data[id];
                        showCategory(object);
                    }
                    categoryEdit();
                });
    }

    function showCategory(object) {
        var tr = document.createElement('tr');
        var name = document.createElement('td');
        var id = document.createElement('td');

        name.innerHTML = object.name;
        id.innerHTML = object.id;

        tr.appendChild(name);
        tr.appendChild(id);
        categories.appendChild(tr);
    }

    function categoryEdit() {
        $('#categoryList').Tabledit({
            url: '/api/v1/category/update',
            eventType: 'dblclick',
            editButton: false,
            deleteButton: true,
            columns: {
                identifier: [1, 'id'],
                editable: [
                    [0, 'name']
                ]
            },
            onSuccess: function(data, textStatus, jqXHR) {
                console.log('onSuccess(data, textStatus, jqXHR)');
                console.log(data);
                console.log(textStatus);
                console.log(jqXHR);
            }
        });
    }

    function show_message(text, priority) {
        $(document).trigger("add-alerts", [
            {
                'message': text,
                'priority': priority
            }
        ]);
    }

    window.onload = function(){
        getCategories();
    }

</script>