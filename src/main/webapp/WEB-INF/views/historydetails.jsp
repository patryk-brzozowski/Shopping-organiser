<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 20.07.2021
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="row">
  <div class="col-md-9">
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h2 class="card-title text-center"> ${list.title} </h2>
        <h3 class="card-title text-center"> ${list.date} </h3>
        <table class="mb-0 table">
          <thead>
          <tr>
            <th>Product name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Bought in</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${list.elements}" var="element" varStatus="status">
              <tr>
              <td>
                ${element.description}
              </td>
              <td>
                  ${element.quantity}
              </td>
              <td>
                  ${element.price}
              </td>
              <td>
                  ${element.shop}
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h2 class="card-title text-center"> Change list details </h2>

        <form method="post" action="/home/changetitle">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <input type="hidden" name="listId" value="${list.id}">
          <div class="position-relative input-group">
            <input class="form-control" type="text" name="title" placeholder="New list title" required="required">
            <div class="input-group-append">
              <input class="btn-shadow mr-3 btn btn-primary" type="submit" value="Change">
            </div>
          </div>
        </form>
        <br/>
        <form method="post" action="/home/changedate">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <input type="hidden" name="listId" value="${list.id}">
          <div class="position-relative input-group">
            <input class="form-control" type="date" name="date" required="required">
            <div class="input-group-append">
              <input class="btn-shadow mr-3 btn btn-primary"  type="submit" value="Change">
            </div>
          </div>
        </form>

      </div>
    </div>
  </div>
</div>

<div class="row">
  <div class="col-md-3">
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h2 class="card-title text-center"> Options for products </h2>
        <div class="center-svg  form-group">
          <a class="btn-shadow mr-3 btn btn-primary btn-block" href="/home/editproducts?id=${list.id}">Edit products details</a>
        </div>
        <br/>
        <form method="post" action="deleteallproducts">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <input type="hidden" name="listId" value="${list.id}">
          <input class="btn-shadow mr-3 btn btn-danger btn-block" type="submit" value="Delete all products">
        </form>
      </div>
    </div>
  </div>

  <div class="col-md-3">
    <div class="main-card mb-3 card">
      <div class="card-body">
        <h2 class="card-title text-center"> Delete the list </h2>
        <form method="post" action="/home/deletelist">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <input type="hidden" name="listId" value="${list.id}">
          <input class="btn-shadow mr-3 btn btn-danger btn-block" type="submit" value="Delete">
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="footer.jsp" %>
