<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add user page</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="../lib/css/bootstrap.min.css" />
</head>
<body>
<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">管理界面</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">用户管理</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <h1>新增用户</h1>

    <form:form method="post" commandName="user" role="form">
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input path="name" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <form:input path="email" class="form-control" />
        </div>
        <div class="form-group">
            <label for="age">Age:</label>
            <form:input path="age" class="form-control"/>
        </div>
        <div class="form-group">
            <input class="btn btn-primary" type="submit" value="新增" />
            <a href="${pageContext.request.contextPath}/user/">返回</a>
        </div>
    </form:form>
</div>

<p></p>
</body>
</html>