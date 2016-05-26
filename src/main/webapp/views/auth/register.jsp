<%@ page import="com.delay.components.enums.CommandStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="content">
    <div class="form-register">
        <form id="register">
            <h2 class="form-register-heading"><spring:message code="label.sign.up" /></h2>
            <h4 class="form-register-heading"><spring:message code="label.sign.up.header" /></h4>
            <input type="text" name="username" class="form-control" placeholder="Логин" required autofocus>
            <input type="password" name="password" class="form-control input-password" placeholder="<spring:message code="label.password" />" required>
            <input type="password" name="password2" class="form-control input-password2" placeholder="<spring:message code="label.password.repeat" />" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.sign.up" /></button>
        </form>
    </div>
    <div class="toblur">
        <div class="fade in" data-alerts="alerts" data-fade="3000"></div>
    </div>
    <%
        String success = CommandStatus.SUCCESS.name();
        request.setAttribute("SUCCESS", success);
        String error = CommandStatus.ERROR.name();
        request.setAttribute("ERROR", error);
        String usernameExist = CommandStatus.USERNAME_EXIST.name();
        request.setAttribute("USERNAME_EXIST", usernameExist);
        String invalid = CommandStatus.PASSWORD_INVALID.name();
        request.setAttribute("PASSWORD_INVALID", invalid);
    %>
    <c:choose>
        <c:when test="${result == SUCCESS}">
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong><spring:message code="label.success" />!</strong> <spring:message code="register.success" />
            </div>
            <script type="application/javascript">
                window.onload = function() {
                    loginRequest('${username}', '${password}');
                }
            </script>
        </c:when>
        <c:when test="${result == ERROR}">
            <div class="alert alert-danger">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong><spring:message code="label.error" />!</strong> <spring:message code="label.error.unknown" />
            </div>
        </c:when>
        <c:when test="${result == USERNAME_EXIST}">
            <div class="alert alert-danger">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong><spring:message code="label.error" />!</strong> <spring:message code="register.username.exist" />
            </div>
        </c:when>
        <c:when test="${result == PASSWORD_INVALID}">
            <div class="alert alert-danger">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong><spring:message code="label.error" />!</strong> <spring:message code="register.passwords.incorrect" />
            </div>
        </c:when>
    </c:choose>
</div>
