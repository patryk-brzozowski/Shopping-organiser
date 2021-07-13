<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 13.07.2021
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label for="firstName">User name</label>
    <input type="text" id="firstName" name="userName" placeholder="User name" required="required" autofocus="autofocus">


    <label for="inputEmail">Email</label>
    <input type="text" id="inputEmail" name="email" placeholder="Email address" required="required">


    <label for="inputPassword">Password</label>
    <input type="password" id="inputPassword" name="password" placeholder="Password" required="required">


    <label for="confirmPassword">Confirm password</label>
    <input type="password" id="confirmPassword" name="confirm_password" placeholder="Confirm password" required="required">


    <input type="submit" value="Register"/>


    <form:errors path="name" />
    <form:errors path="username" />
    <form:errors path="password" />
    <form:errors path="confirm_password" />

</form:form>
</body>
</html>
