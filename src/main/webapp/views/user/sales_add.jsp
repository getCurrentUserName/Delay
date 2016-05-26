<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Продажа</h3>

<div class="form-group input-group col-md-2">
    <select id="categories" onchange="getSubcategories();" class="form-control">
    </select>
</div>
<div class="form-group input-group col-md-2">
    <select id="subcategories" onchange="getProducts();" class="form-control">
    </select>
</div>


<table class="table table-hover table-striped" id="productList">
    <thead>
    <th>Название</th>
    <th>Количество</th>
    <th>Дата окончания</th>
    <th>Цена</th>
    <th>Количество</th>
    <th>Действие</th>
    </thead>
    <tbody id="products">
    </tbody>
</table>


<table class="table table-hover table-striped" id="basketList">
    <thead>
    <th>Название</th>
    <th>Количество</th>
    <th>Цена</th>
    <th>Действие</th>
    </thead>
    <tbody id="addedProducts">
    </tbody>
</table>

<script type="text/javascript">

    var subcategories = document.getElementById("subcategories");
    var categories = document.getElementById("categories");
    var products = document.getElementById("products");
    var addedProducts = document.getElementById("addedProducts");
    var basket;

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

    function getProducts() {
        $.get(context + "/api/v1/subcategory/" + subcategories.options[subcategories.selectedIndex].value + "/products", {})
                .done(function(data) {
                    products.innerHTML = '';
                    for (var id in data) {
                        var object = data[id];
                        showProduct(object);
                    }
                });
    }

    function showProduct(object) {
        var tr = document.createElement('tr');
        var name = document.createElement('td');
        var count = document.createElement('td');
        var expireDate = document.createElement('td');
        var price = document.createElement('td');
        var inputTd = document.createElement('td');
        var addTd = document.createElement('td');
        var inputCount = document.createElement('div');
        var addButton = document.createElement('div');

        name.innerHTML = object.name;
        count.innerHTML = object.count;
        expireDate.innerHTML = object.expireDate;
        price.innerHTML = object.price;
        inputCount.innerHTML = '<input class="form-control" id="'+ object.id + '" type="number" placeholder="Кол. шт.">';
        addButton.innerHTML = '<button type="submit" class="btn btn-default" onclick="clickAdd(\'' + object.id + '\',\'' + object.name + '\',\'' + object.price + '\');">Добавить</button>';

        inputTd.appendChild(inputCount);
        addTd.appendChild(addButton);
        tr.appendChild(name);
        tr.appendChild(count);
        tr.appendChild(expireDate);
        tr.appendChild(price);
        tr.appendChild(inputTd);
        tr.appendChild(addTd);
        products.appendChild(tr);
    }

    function clickAdd(id, name, price) {
        //var item = {productId:id, count:document.getElementById(id).value};
        var item = {};
        item.productId = id;
        item.count = document.getElementById(id).value;
        item.name = name;
        item.price = price;
        basket = basket + item;
        showBasketItem(item);
    }

    function showBasketItem(object) {
        var tr = document.createElement('tr');
        var name = document.createElement('td');
        var count = document.createElement('td');
        var price = document.createElement('td');
        var tdDelete = document.createElement('td');
        name.innerHTML = object.name;
        count.innerHTML = object.count;
        price.innerHTML = object.price;
        tr.appendChild(name);
        tr.appendChild(count);
        tr.appendChild(price);
        tr.appendChild(tdDelete);
        addedProducts.appendChild(tr);
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