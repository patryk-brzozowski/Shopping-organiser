<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 18.07.2021
  Time: 10:46
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
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="main-card mb-3 card">
            <div class="card-body">
                <h2 class="card-title text-center"> Change the title of the list </h2>

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
                    <a class="btn-shadow mr-3 btn btn-primary btn-block" href="/home/editproducts?id=${list.id}">Show/edit products details</a>
                </div>
                <br/>
                <form method="post" action="deleteallproducts">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<%--                    <input type="hidden" name="listId" value="${list.id}">--%>
                    <input class="btn-shadow mr-3 btn btn-danger btn-block" type="submit" value="Delete all products">
                </form>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="main-card mb-3 card">
            <div class="card-body">
                <h2 class="card-title text-center"> Options for closing the list </h2>
                <form method="post" action="NOT READY">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="listId" value="${list.id}">
                    <input class="btn-shadow mr-3 btn btn-primary btn-block" type="submit" value="Close the list and move to history">
                </form>
                <br/>
                <form method="post" action="NOT READY">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="listId" value="${list.id}">
                    <input class="btn-shadow mr-3 btn btn-primary btn-block" type="submit" value="Close the list, move to history and add products to supplies">
                </form>
                <br/>
                <form method="post" action="/home/deletelist">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="listId" value="${list.id}">
                    <input class="btn-shadow mr-3 btn btn-danger btn-block" type="submit" value="Delete the list">
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
