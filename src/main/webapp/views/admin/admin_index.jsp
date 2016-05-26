<%@ page import="com.delay.components.enums.CommandStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String success = CommandStatus.SUCCESS.toString();
    request.setAttribute("success", success);

    String username_exist = CommandStatus.USERNAME_EXIST.toString();
    request.setAttribute("username_exist", username_exist);

    String access_denied = CommandStatus.ACCESS_DENIED.toString();
    request.setAttribute("access_denied", access_denied);

    String error = CommandStatus.ERROR.toString();
    request.setAttribute("error", error);
%>
<h3>Добавление компании</h3>
<form class="form-group input-group col-md-8" action="/admin/company/add" method="post">
    <input class="form-control" name="name" placeholder="Имя компании">
    <input class="form-control" name="username" placeholder="Username">
    <input class="form-control" name="password" placeholder="Password">
    <button type="submit" class="btn btn-primary">Добавить</button>
</form>
<c:choose>
    <c:when test="${result == success}">
        <h4>Успешно добавлен</h4>
    </c:when>
    <c:when test="${result == username_exist}">
        <h4>Имя пользователя занято</h4>
    </c:when>
    <c:when test="${result == access_denied}">
        <h4>Нет доступа</h4>
    </c:when>
    <c:when test="${result == error}">
        <h4>Ошибка при добавлении</h4>
    </c:when>
</c:choose>

<table class="table table-hover table-striped" id="companyList">
    <thead>
    <th>Имя</th>
    <th>Id</th>
    </thead>
    <tbody id="companies">
    </tbody>
</table>

<script type="text/javascript">

    var companies = document.getElementById("companies");

    function getCompanies() {
        $.get(context + "/api/v1/admin/company/all", {})
                .done(function(data) {
                    companies.innerHTML = '';
                    for (var id in data) {
                        var object = data[id];
                        showCompany(object);
                    }
                    companyEdit();
                });
    }

    function showCompany(object) {
        var tr = document.createElement('tr');
        var name = document.createElement('td');
        var id = document.createElement('td');

        name.innerHTML = object.name;
        id.innerHTML = object.id;

        tr.appendChild(name);
        tr.appendChild(id);
        companies.appendChild(tr);
    }

    function companyEdit() {
        $('#companyList').Tabledit({
            url: '/api/v1/admin/company/update',
            eventType: 'dblclick',
            editButton: false,
            deleteButton: false,
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
        getCompanies();
    }

</script>