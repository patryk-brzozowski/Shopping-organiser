<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 18.07.2021
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="row">
    <div class="col-md-12">
        <div class="main-card mb-3 card">
            <div class="card-body">
                <table class="mb-0 table">
                    <thead>
                    <tr>
                        <th>Product name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Bought in</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list.elements}" var="element" varStatus="status">
                        <form:form method="post" action="/home/editproducts" modelAttribute="product${status.index}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <form:input path="id" type="hidden" value="${element.id}"/>
                            <input name="listOfProducts" type="hidden" value="${list.id}"/>
                        <tr>
                            <td>
                                <form:input class="form-control" path="description" type="text"/>
                                <form:errors cssClass="text-danger"  path="description"/>
                            </td>
                            <td>
                                <form:input class="form-control" path="quantity" type="number" step="0.001"/>
                                <form:errors cssClass="text-danger"  path="quantity"/>
                            </td>
                            <td>
                                <form:input class="form-control" path="price" type="number" step="0.01"/>
                                <form:errors cssClass="text-danger"  path="price"/>
                            </td>
                            <td>
                                <form:input class="form-control" path="shop" type="text"/>
                            </td>
                            <td>
                                <input class="btn-shadow mr-3 btn btn-success btn-block" type="submit" value="Edit row"/>
                        </form:form>
                        <form method="post" action="/home/deleteproduct">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="productId" value="${element.id}">
                            <button class="btn-shadow mr-3 btn btn-danger btn-block" type="submit"> Delete product</button>
                        </form>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${not empty failed}">
                    <div class="text-danger">${failed}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="text-success">${success}</div>
                </c:if>
                <br/>
                <form method="post" action="/home/addproduct">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="listId" value="${list.id}">
                    <div class="position-relative input-group">
                        <input class="form-control" type="text" name="description" placeholder="Add new product" required="required">
                        <div class="input-group-append">
                            <input class="btn-shadow mr-3 btn btn-primary" type="submit" value="Add">
                        </div>
                    </div>
                </form>
                <br/>
                <c:if test="${list.active == 'yes'}">
                <div class="position-relative form-group">
                    <a class="btn-shadow mr-3 btn btn-primary" href="/home/details?id=${list.id}"> Go back </a>
                </div>
                </c:if>
                <c:if test="${list.active == 'no'}">
                    <div class="position-relative form-group">
                        <a class="btn-shadow mr-3 btn btn-primary" href="/home/historydetails?id=${list.id}"> Go back </a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
