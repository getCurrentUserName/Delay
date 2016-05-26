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
    <link rel="stylesheet" href="<c:url value="/assets/style/auth.css"/>">
</head>
<body id="body">
<script type="application/javascript">
    var context = '${pageContext.request.contextPath}';

    var error_unknown = '<spring:message code="label.error.unknown" />';
    var username_not_found = '<spring:message code="error.username.not.found" />';
</script>
<div class="container">
    <div class="container-fluid">
        <sitemesh:write property='body'/>
    </div>
</div>
<script src="<c:url value="/assets/jquery/jquery-2.2.3.min.js"/>"></script>
<script async src="<c:url value="/assets/bootstrap/js/bootstrap.js"/>"></script>
<script async src="<c:url value="/assets/jquery/jquery.bsAlerts.min.js"/>"></script>
<script async src="<c:url value="/assets/js/auth.js"/>"></script>
</body>
</html>
