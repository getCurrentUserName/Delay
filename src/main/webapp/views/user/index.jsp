<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Истекает сроки</h3>
<div class="form-group input-group col-md-8">
    <input class="form-control" id="date" type="number" placeholder="Истечет в течении">
</div>
<div class="form-group input-group col-md-8">
    <button type="submit" class="btn btn-default pull-right" onclick="getProductsByDate();">Поиск</button>
</div>
<table class="table table-hover table-striped" id="productList">
    <thead>
    <th>Название</th>
    <th>Количество</th>
    <th>Дата добавления</th>
    <th>Дата окончания</th>
    <th>Страна</th>
    <th>Id</th>
    </thead>
    <tbody id="products">
    </tbody>
</table>

<script type="text/javascript">

    var products = document.getElementById("products");

    function getProducts() {
        $.get(context + "/api/v1/product/expiring", {})
                .done(function(data) {
                    products.innerHTML = '';
                    for (var id in data) {
                        var object = data[id];
                        showProduct(object);
                    }
                    productEdit();
                });
    }

    function getProductsByDate() {
        $.post(context + "/api/v1/product/expiring", {
            date : document.getElementById("date").value
        })
                .done(function(data) {
                    products.innerHTML = '';
                    for (var id in data) {
                        var object = data[id];
                        showProduct(object);
                    }
                    productEdit();
                });
    }


    function showProduct(object) {
        var tr = document.createElement('tr');
        var name = document.createElement('td');
        var count = document.createElement('td');
        var additionDate = document.createElement('td');
        var expireDate = document.createElement('td');
        var country = document.createElement('td');
        var id = document.createElement('td');

        name.innerHTML = object.name;
        count.innerHTML = object.count;
        additionDate.innerHTML = object.additionDate;
        expireDate.innerHTML = object.expireDate;
        country.innerHTML = object.country;
        id.innerHTML = object.id;

        tr.appendChild(name);
        tr.appendChild(count);
        tr.appendChild(additionDate);
        tr.appendChild(expireDate);
        tr.appendChild(country);
        tr.appendChild(id);
        products.appendChild(tr);
    }

    function productEdit() {
        $('#productList').Tabledit({
            url: '/api/v1/product/update',
            eventType: 'dblclick',
            editButton: false,
            deleteButton: true,
            columns: {
                identifier: [5, 'id'],
                editable: [
                    [0, 'name'],
                    [1, 'count'],
                    [3, 'expiry'],
                    [4, 'country']
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
        getProducts();
    }

</script>