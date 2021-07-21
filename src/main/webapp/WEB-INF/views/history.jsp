<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 20.07.2021
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="row">
    <div class="col-md-12">
        <div class="mb-3 card">
            <div class="card-body">
                <h2 class="card-title text-center"> Filters </h2>
                <form method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-row flex-row">
                    <div class="col-md-4 mb-3">
                        <label for="year">Year</label>
                        <select class="form-control" id="year" name="year">
                            <option value="all">All</option>
                            <c:forEach items="${years}" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="month">Month</label>
                        <select class="form-control" id="month" name="month">
                            <option value="all">All</option>
                            <c:forEach items="${months}" var="month">
                                <option value="${month}">${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="shops">Shop</label>
                        <select class="form-control" id="shops" name="shop">
                            <option value="all">All</option>
                            <c:forEach items="${shops}" var="shop">
                                <option value="${shop}">${shop}</option>
                            </c:forEach>
                        </select>
                    </div>
                    </div>
                    <br/>
                    <div class="position-relative ">
                        <input class="btn-shadow mr-3 btn btn-success btn-block btn-sm" type="submit" value="Use filters"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<c:forEach items="${userLists}" var="list" varStatus="status">
    <c:if test="${status.index % 2 == 0}">
        <div class="row">
    </c:if>
    <div class="col-md-6">
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
                    <c:forEach items="${list.elements}" var="element">
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
                <br/>
                <div class="center-svg  form-group">
                    <a class="btn-shadow mr-3 btn btn-success btn-block" href="/home/historydetails?id=${list.id}">Details</a>
                </div>

            </div>

        </div>
    </div>
    <c:if test="${status.index % 2 != 0}">
        </div>
    </c:if>

    <c:if test="${status.last}">
        <c:if test="${status.index % 2 == 0}">
            </div>
        </c:if>
    </c:if>
</c:forEach>


<br/>
<div class="row">
<div class="col-md-6 col-xl-4">
    <div class="card mb-3 widget-content bg-arielle-smile">
        <div class="widget-content-wrapper text-white">
            <div class="widget-content-left">
                <div class="widget-heading">Sum of money spent:</div>
            </div>
            <div class="widget-content-right">
                <div class="widget-numbers text-white">
                    <span>${totalPrice}</span>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@ include file="footer.jsp" %>
