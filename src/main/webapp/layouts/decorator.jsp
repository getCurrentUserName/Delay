<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru" xmlns:sitemesh="sitemesh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="UTF-8">
    <title>Delay</title>
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/fonts/glyphicons-halflings-regular.ttf"/>">
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap-theme.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/style/dashboard.css"/>">
</head>
<body id="body">
<script type="application/javascript">
    var context = '${pageContext.request.contextPath}';
    var error_unknown = '<spring:message code="label.error.unknown" />';
</script>
<nav class="navbar navbar-default navbar-fixed-top" id="menu">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">Delay</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/category"/>">Категории</a></li>
                <li><a href="<c:url value="/subcategory"/>">Подкатегории</a></li>
                <li><a href="<c:url value="/product"/>">Товары</a></li>
                <li><a href="<c:url value="/sales"/>">Продажи</a></li>
                <li><a href="<c:url value="/sales/add"/>">Оформление продажи</a></li>
                <li><a href="#">Контакты</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" onclick="logout();"><spring:message code="label.logout" /></a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="<c:url value="/category"/>">Категории</a></li>
                    <li><a href="<c:url value="/subcategory"/>">Подкатегории</a></li>
                    <li><a href="<c:url value="/product"/>">Товары</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="<c:url value="/sales"/>">Продажи</a></li>
                    <li><a href="<c:url value="/sales/add"/>">Оформление продажи</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="#">Контакты</a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <sitemesh:write property='body'/>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/assets/jquery/jquery-2.2.3.min.js"/>"></script>
<script async src="<c:url value="/assets/bootstrap/js/bootstrap.js"/>"></script>
<script async src="<c:url value="/assets/jquery/jquery.bsAlerts.min.js"/>"></script>
<script async src="<c:url value="/assets/jquery/jquery.tabledit.js"/>"></script>
<script>
    function logout() {
        $.get(context + "/logout", {})
                .done(function(data) {
                    location=context + "/";
                });
    }

</script>
</body>
</html>
