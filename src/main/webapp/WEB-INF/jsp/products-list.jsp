<%--
  Created by IntelliJ IDEA.
  User: svidleo
  Date: 2019-03-14
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container-flud">
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
                                    <div class="card-body">
                                        <h5 class="card-title">${product.name}</h5>
                                        <div class="badge badge-info">${product.price}</div>
                                        <a href="/admin/products/edit/${product.id}">Edit</a>
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
