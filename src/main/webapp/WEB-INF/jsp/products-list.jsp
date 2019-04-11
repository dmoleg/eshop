<%--
  Created by IntelliJ IDEA.
  User: svidleo
  Date: 2019-03-14
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <title>Title</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-flud">
    <form:form method="GET" modelAttribute="filterModel">
        <div class="row pt-2 pl-3">
            <div class="col-6 form-group">
                <form:input cssClass="form-control" path="name"/>
            </div>
            <div class="col-2 form-group">
                <form:select cssClass="form-control" path="sortBy">
                    <form:option value="name" label="Name"></form:option>
                    <form:option value="price" label="Price"></form:option>
                </form:select>
            </div>
            <div class=" col-2 form-group">
                <form:select cssClass="form-control" path="sortDirection">
                    <form:option label="ASC" value="ASC"/>
                    <form:option label="DESC" value="DESC"/>
                </form:select>
            </div>
            <div class="col-2">
                <form:button class="btn btn-primary">Search</form:button>
            </div>
        </div>
    </form:form>
    <div class="row">
        <div class="col-2">
            <ul class="list-group">
                <li class="list-group-item">
                    <c:choose>
                        <c:when test="${not empty slug}">
                            <a href="/products">All products</a>
                        </c:when>
                        <c:otherwise>
                                <span class="badge badge-primary">
                                    All products
                                </span>
                        </c:otherwise>
                    </c:choose>
                </li>
                <c:forEach items="${categories}" var="category">
                    <li class="list-group-item">
                        <c:choose>
                            <c:when test="${!category.slug.equals(slug)}">
                                <a href="/products/${category.slug}">
                                        ${category.name}
                                </a>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-primary">${category.name}</span>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-10">
            <div class="container-fluid">
                <div class="row">
                    <c:forEach items="${products}" var="product">
                        <div class="col-2">
                            <div class="card">
                                <c:if test="${not empty product.imageName}">
                                    <img src="/images/${product.imageName}" class="card-img-top" alt="${product.name}">
                                </c:if>
                                <c:if test="${empty product.imageName}">
                                    <svg class="bd-placeholder-img card-img-top" width="100%" height="100"
                                         xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice"
                                         focusable="false" role="img" aria-label="Placeholder: Image cap"><title>
                                        Placeholder</title>
                                        <rect width="100%" height="100%" fill="#868e96"></rect>
                                        <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
                                    </svg>
                                </c:if>
                                <div class="card-body">
                                    <h5 class="card-title">${product.name}</h5>
                                    <div class="badge badge-info">${product.price}</div>
                                    <sec:authorize access="hasRole('ADMIN')">
                                        <a href="/admin/products/edit/${product.id}">Edit</a>
                                    </sec:authorize>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
