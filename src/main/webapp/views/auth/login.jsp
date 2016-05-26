<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">

    <div class="form-login">
        <h2 class="form-login-heading"><spring:message code="label.authorization" /></h2>
        <label for="loginUsername" class="sr-only"></label>
        <input type="text" id="loginUsername" class="form-control" placeholder="<spring:message code="label.username" />" required autofocus>
        <label for="loginPassword" class="sr-only"></label>
        <input type="password" id="loginPassword" class="form-control" placeholder="<spring:message code="label.password" />" required>
        <div class="checkbox">
            <label>
                <spring:message code="label.remember" var="labelSubmit"/>
                <input type="checkbox" id="remember" value="remember-me"> ${labelSubmit}
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" onclick="login();" type="submit"><spring:message code="label.sign.in" /></button>
        <label for="loginPassword" class="sr-only"><spring:message code="label.sign.up" /></label>
    </div>

    <div class="toblur">
        <div class="fade in" data-alerts="alerts" data-fade="3000"></div>
    </div>

</div>

