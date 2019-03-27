<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <form:form method="POST" modelAttribute="userModel">
            <div class="form-group">
                <label for="name">User name</label>
                <form:input path="name" cssClass="form-control" />
                <form:errors path="name" cssStyle="color: red" />
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <form:input path="username" cssClass="form-control"/>
                <form:errors path="username" cssStyle="color: red" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:password path="password" cssClass="form-control" />
                <form:errors path="password" cssStyle="color: red" />
            </div>
            <form:button class="btn btn-primary">Submit</form:button>
        </form:form>
    </div>
</div>
</body>
</html>
