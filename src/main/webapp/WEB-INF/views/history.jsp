<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 20.07.2021
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<c:forEach items="${userLists}" var="list" varStatus="status">
    <c:if test="${status.index % 2 == 0}">
        <div class="row">
    </c:if>
    <div class="col-md-6">
        <div class="main-card mb-3 card">
            <div class="card-body">
                <h2 class="card-title text-center"> ${list.title} </h2>
                <h3 class="card-title text-center"> ${list.date} </h3>
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

<%@ include file="footer.jsp" %>
