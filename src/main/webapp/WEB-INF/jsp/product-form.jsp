<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<h1>Product form</h1>
<div class="container">
    <div class="row">
        <form:form method="POST" modelAttribute="productModel">
            <div class="form-group">
                <label for="name">Product name</label>
                <form:input path="name" cssClass="form-control" />
                <form:errors path="name" cssStyle="color: red" />
            </div>
            <div class="form-group">
                <label form="description">Product description</label>
                <form:input path="description" cssClass="form-control" />
            </div>
            <form:button class="btn btn-primary">Create</form:button>
        </form:form>
    </div>
</div>
</body>
</html>
