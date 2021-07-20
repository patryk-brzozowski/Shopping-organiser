<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 13.07.2021
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<c:if test="${not empty closed}">
    <h3>${closed}</h3>
</c:if>

<c:forEach items="${userLists}" var="list" varStatus="status">
    <c:if test="${status.index % 2 == 0}">
    <div class="row">
    </c:if>
    <div class="col-md-6">
        <div class="main-card mb-3 card">
            <div class="card-body">
                <h2 class="card-title text-center"> ${list.title} </h2>
                <ul class="list-group">
                <c:forEach items="${list.elements}" var="element">
                <li class="list-group-item-action list-group-item">${element.description}
                    <form class="float-right" method="post" action="/home/deleteproduct">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="productId" value="${element.id}">
                        <button class="btn center-elem" type="submit"> <i class="pe-7s-close"></i> </button>
                    </form>
                </li>
                </c:forEach>
                </ul>
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

                <div class="center-svg  form-group">
                    <a class="btn-shadow mr-3 btn btn-success btn-block" href="/home/details?id=${list.id}">Details</a>
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

<div class="center-elem center-svg">
    <form action="/home/add" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn-shadow mr-3 btn btn-primary" ><i class="pe-7s-plus"></i> Add new list</button>
    </form>
</div>

<br/>

<%@ include file="footer.jsp" %>
