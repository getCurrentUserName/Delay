<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Добавление подкатегории</h3>


<div class="form-group input-group col-md-2">
    <select id="categories" onchange="getSubcategories();" class="form-control">
    </select>
</div>

<div class="form-group input-group col-md-8">
    <input class="form-control" id="name" placeholder="Имя подкатегории">
</div>
<div class="form-group input-group col-md-8">
    <button type="submit" class="btn btn-default pull-right" onclick="addSubcategory();">Добавить</button>
</div>

<table class="table table-hover table-striped" id="subcategoryList">
    <thead>
    <th>Название</th>
    <th>Id</th>
    </thead>
    <tbody id="subcategories">
    </tbody>
</table>

<script type="text/javascript">

    var subcategories = document.getElementById("subcategories");
    var categories = document.getElementById("categories");

    function getCategories() {
        $.get(context + "/api/v1/category/all", {})
                .done(function(data) {
                    categories.innerHTML = '';
                    for (var id in data) {
                        var object = data[id];
                        showCategory(object);
                    }
                    getSubcategories();
                });
    }

    function showCategory(object) {
        var option = document.createElement('option');
        option.value = object.id;
        option.innerHTML = object.name;
        categories.appendChild(option);
    }

    function addSubcategory() {
        $.post(context + "/api/v1/subcategory/add", {
            name : document.getElementById("name").value,
            categoryId : categories.options[categories.selectedIndex].value
        })
                .done(function(data) {
                    switch (data.result) {
                        case "SUCCESS":
                            getSubcategories();
                            break;
                        default:
                            show_message(error_unknown, 'danger');
                    }
                });
    }

    function getSubcategories() {
        $.get(context + "/api/v1/category/" + categories.options[categories.selectedIndex].value + "/subcategories", {})
                .done(function(data) {
                    subcategories.innerHTML = '';
                    for (var id in data) {
                        var object = data[id];
                        showSubcategory(object);
                    }
                    subcategoryEdit();
                });
    }

    function showSubcategory(object) {
        var tr = document.createElement('tr');
        var name = document.createElement('td');
        var id = document.createElement('td');

        name.innerHTML = object.name;
        id.innerHTML = object.id;

        tr.appendChild(name);
        tr.appendChild(id);
        subcategories.appendChild(tr);
    }

    function subcategoryEdit() {
        $('#subcategoryList').Tabledit({
            url: '/api/v1/subcategory/update',
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