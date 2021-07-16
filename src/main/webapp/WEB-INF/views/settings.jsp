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
    <form:errors cssClass="text-danger" path="userName" />
    <c:if test="${not empty userexists}">
      <div class="text-danger">${userexists}</div>
    </c:if>
  </div>

  <div class="position-relative form-group">
  <label for="inputEmail">Email</label>
  <form:input path="email" class="form-control" type="text" id="inputEmail" name="email"  required="required"/>
    <form:errors cssClass="text-danger" path="email" />
    <c:if test="${not empty emailexists}">
      <div class="text-danger">${emailexists}</div>
    </c:if>
  </div>


  <form:input path="password" type="hidden"  value="irrelevant"/>

  <c:if test="${not empty update}">
    <div class="text-success">${update}</div>
  </c:if>

  <br/>
  <div class="position-relative form-group">
  <input class="btn-shadow mr-3 btn btn-success btn-block" type="submit" value="Change details"/>
  </div>

</form:form>

      </div>
    </div>
  </div>
</div>

<div class="row">
  <div class="col-md-6 col-xl-3">
    <div class="main-card mb-3 card">
      <div class="card-body">

        <h2 class="card-title text-center"> Change password </h2>

        <form:form method="post" modelAttribute="dto" action="/home/changepassword">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <form:input path="userName" class="form-control" value="user" type="hidden"  name="userName"/>
          <form:input path="email" class="form-control" value="user@email.com" type="hidden"  name="userName"/>

          <div class="position-relative form-group">
            <label for="currentPassword">Current password</label>
            <input  class="form-control" type="password" id="currentPassword" name="currentPassword" placeholder="Old password" required="required"/>
            <c:if test="${not empty failed}">
              <div class="text-danger">${failed}</div>
            </c:if>
          </div>

          <div class="position-relative form-group">
            <label for="password">New password</label>
            <form:input path="password" class="form-control" type="password" id="password" name="password" placeholder="New password" required="required"/>
            <form:errors cssClass="text-danger" path="password" />
          </div>

          <div class="position-relative form-group">
            <label for="confirmPassword">Confirm password</label>
            <form:input path="confirm_password" class="form-control" type="password" id="confirmPassword" name="confirm_password" placeholder="Confirm password" required="required"/>
            <c:if test="${not empty match}">
              <div class="text-danger">${match}</div>
            </c:if>
          </div>

          <c:if test="${not empty password}">
            <div class="text-success">${password}</div>
          </c:if>

          <br/>
          <div class="position-relative form-group">
            <input class="btn-shadow mr-3 btn btn-success btn-block" type="submit" value="Change password"/>
          </div>

        </form:form>

      </div>
    </div>
  </div>

  <div class="col-md-6 col-xl-3">
    <div class="main-card mb-3 card">
      <div class="card-body">

        <h2 class="card-title text-center"> Delete account </h2>

        <a class="btn-shadow mr-3 btn btn-danger btn-block" href='<c:url value="/home/delete"/>'>Delete</a>

      </div>
    </div>
  </div>
</div>

<%@ include file="footer.jsp" %>