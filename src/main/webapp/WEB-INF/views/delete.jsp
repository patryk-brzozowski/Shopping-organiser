<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 15.07.2021
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="col-md-6 col-xl-4">
  <div class="main-card mb-3 card">
    <div class="card-body">
      <form method="post" action="/home/delete">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="position-relative form-group">
          <label for="password"> Please enter password : </label> <input class="form-control" type="password" name="password" id="password"/>
          <c:if test="${not empty failed}">
            <div class="text-danger">${failed}</div>
          </c:if>
        </div>
        <div class="text-center"> Are you sure? You will lose all content on your account. </div>
        <br/>
        <div class="position-relative form-group">
          <input class="btn-shadow mr-3 btn btn-danger btn-block" type="submit" value="Confirm"/>
        </div>
      </form>
      <div class="position-relative form-group">
        <a class="btn-shadow mr-3 btn btn-primary btn-block" href="/home/settings"> Go back </a>
      </div>
    </div>
  </div>
</div>



<%@ include file="footer.jsp" %>
