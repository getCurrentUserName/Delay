<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<c:url value="/assets/style/product.css"/>">
<h3>Добавление товара</h3>

<div class="form-group input-group col-md-2">
    <select id="categories" onchange="getSubcategories();" class="form-control">
    </select>
</div>
<div class="form-group input-group col-md-2">
    <select id="subcategories" onchange="getProducts();" class="form-control">
    </select>
</div>
<div class="modal fade" id="addModal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="form-add">
                <h2 class="form-add-heading">Добавление товара</h2>
                <div class="form-group input-group col-md-8">
                    <input class="form-control" id="name" placeholder="Имя товара">
                    <input class="form-control" type="number" id="count" placeholder="Количество">
                    <input class="form-control" id="country" placeholder="Страна">
                    <input class="form-control" type="number" id="expiry" placeholder="Кол. дней до истечения">
                    <input class="form-control" type="number" id="price" placeholder="Цена">
                </div>
                <div class="form-group input-group col-md-8">
                    <button type="submit" class="btn btn-default pull-right" onclick="addProduct();">Добавить</button>
                </div>
            </div>
        </div>
    </div>
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
<button class="btn btn-default pull-right" data-toggle="modal" data-target="#addModal">Добавить</button>

<script type="text/javascript">

    var subcategories = document.getElementById("subcategories");
    var categories = document.getElementById("categories");
    var products = document.getElementById("products");

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

    function getSubcategories() {
        $.get(context + "/api/v1/category/" + categories.options[categories.selectedIndex].value + "/subcategories", {})
                .done(function(data) {
                    subcategories.innerHTML = '';
                    for (var id in data) {
                        var object = data[id];
                        showSubcategory(object);
                    }
                    getProducts();
                });
    }

    function showSubcategory(object) {
        var option = document.createElement('option');
        option.value = object.id;
        option.innerHTML = object.name;
        subcategories.appendChild(option);
    }

    function addProduct() {
        $.post(context + "/api/v1/product/add", {
            name : document.getElementById("name").value,
            count : document.getElementById("count").value,
            country : document.getElementById("country").value,
            expiry : document.getElementById("expiry").value,
            price : document.getElementById("price").value,
            subcategoryId : subcategories.options[subcategories.selectedIndex].value
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

    function getProducts() {
        $.get(context + "/api/v1/subcategory/" + subcategories.options[subcategories.selectedIndex].value + "/products", {})
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
        getCategories();
    }

</script>