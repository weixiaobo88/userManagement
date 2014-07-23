<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit user page</title>
</head>
<body>
<h1>Edit user page</h1>
<p>Here you can edit the existing user.</p>

<form:form method="post" commandName="user">
<table>
<tbody>
 <tr>
  <td>Name:</td>
  <td><form:input path="name" /></td>
 </tr>
 <tr>
  <td>Email:</td>
  <td><form:input path="email" /></td>
 </tr>
 <tr>
  <td>Age:</td>
  <td><form:input path="age" /></td>
 </tr>
 <tr>
  <td><input type="submit" value="submit" /></td>
  <td></td>
 </tr>
</tbody>
</table>
</form:form>

<p><a href="${pageContext.request.contextPath}/user/">Home page</a></p>
</body>
</html>