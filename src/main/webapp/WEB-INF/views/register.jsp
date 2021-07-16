<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 13.07.2021
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

<sec:authorize access="!isAuthenticated()">
    <div class="col-md-6 col-xl-4">
    <div class="main-card mb-3 card">
    <div class="card-body">
<form:form method="post" modelAttribute="dto">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="position-relative form-group">
    <label for="userName">User name</label>
    <form:input path="userName" class="form-control" type="text" id="userName" name="userName" placeholder="User name" required="required" autofocus="autofocus"/>
        <form:errors cssClass="text-danger" id="userName" path="userName" />
        <c:if test="${not empty userexists}">
            <div class="text-danger">${userexists}</div>
        </c:if>
    </div>

    <div class="position-relative form-group">
    <label for="inputEmail">Email</label>
    <form:input path="email" class="form-control" type="text" id="inputEmail" name="email" placeholder="Email address" required="required"/>
        <form:errors cssClass="text-danger" path="email" />
        <c:if test="${not empty emailexists}">
            <div class="text-danger">${emailexists}</div>
        </c:if>
    </div>

    <div class="position-relative form-group">
    <label for="inputPassword">Password</label>
    <form:input path="password" class="form-control" type="password" id="inputPassword" name="password" placeholder="Password" required="required"/>
        <form:errors cssClass="text-danger" path="password" />
    </div>

    <div class="position-relative form-group">
    <label for="confirmPassword">Confirm password</label>
    <form:input path="confirm_password" class="form-control" type="password" id="confirmPassword" name="confirm_password" placeholder="Confirm password" required="required"/>
        <c:if test="${not empty match}">
            <div class="text-danger">${match}</div>
        </c:if>
    </div>

    <br/>
    <div class="position-relative form-group">
    <input class="btn-shadow mr-3 btn btn-success btn-block" type="submit" value="Sign up"/>
    </div>


</form:form>

    </div>
    </div>
    </div>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <div class="app-page-title">
        <div class="page-title-wrapper">
            <div class="page-title-heading">
                <div><h2> Welcome!</h2>
                    <div class="page-title-subheading">
                        <a class="btn btn-primary" href='<c:url value="/home"/>'>Go to main page</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>

<%@ include file="footer.jsp" %>
