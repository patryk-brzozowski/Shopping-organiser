<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 15.07.2021
  Time: 09:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="row">
  <div class="col-md-6">
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h2 class="card-title text-center"> User details</h2>
<form:form method="post" modelAttribute="user">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

  <div class="position-relative form-group">
  <label for="userName">User name</label>
  <form:input path="userName" class="form-control" type="text" id="userName" name="userName"  required="required" autofocus="autofocus"/>
  </div>

  <div class="position-relative form-group">
  <label for="inputEmail">Email</label>
  <form:input path="email" class="form-control" type="text" id="inputEmail" name="email"  required="required"/>
  </div>
  <form:input path="password" type="hidden"  value="irrelevant"/>

  <br/>
  <div class="position-relative form-group">
  <input class="btn-shadow mr-3 btn btn-success btn-block" type="submit" value="Change details"/>
  </div>

  <form:errors path="userName" />
  <form:errors path="email" />
  <form:errors path="password" />
</form:form>

      </div>
    </div>
  </div>
</div>


<%@ include file="footer.jsp" %>