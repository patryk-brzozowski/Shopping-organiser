<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 13.07.2021
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>



<sec:authorize access="!isAuthenticated()">
    <div class="col-md-6 col-xl-4">
    <div class="main-card mb-3 card">
         <div class="card-body">
             <h2 class="card-title text-center"> Welcome!</h2>
    <form method="post">
        <div class="position-relative form-group">
            <label for="username"> User Name : </label> <input class="form-control" type="text" name="username" id="username"/>
        </div>

        <div class="position-relative form-group">
            <label for="password"> Password : </label><input class="form-control" type="password" name="password" id="password"/>
        </div>

        <div class="position-relative form-group">
            <input class="btn-shadow mr-3 btn btn-primary btn-block" type="submit" value="Sign in"/>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
         <br/>
    <a class="btn-shadow mr-3 btn btn-success btn-block" href='<c:url value="/register"/>'>Sign up</a>

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
