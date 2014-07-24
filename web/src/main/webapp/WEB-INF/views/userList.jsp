<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>List of Users</title>
</head>
<body>
<h1>User Manager</h1>
<p>
<a href="${pageContext.request.contextPath}/user/add">Add New User</a>
</p>
<small>${message}</small>

<h2>List of Users</h1>

<p>Here you can see the list of the users, edit them, remove or update.</p>


<table class="table table-striped" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="10%">
    <input type="checkbox" name="checkbox" />
    <a href="${pageContext.request.contextPath}/user/deleteAll">Batch Delete</a>
</th>
<th width="10%">id</th>
<th width="15%">name</th>
<th width="10%">email</th>
<th width="10%">age</th>
<th width="10%" colspan="2">actions</th>
</tr>
</thead>
<tbody>
<c:forEach var="user" items="${users}">
<tr>
 <td>
    <input type="checkbox" name="userCheckbox" value ='${user.id}' />
    <form:checkbox path="myList[${status.index}].checkControl"/>
 </td>
 <td>${user.id}</td>
 <td>${user.name}</td>
 <td>${user.email}</td>
 <td>${user.age}</td>
 <td>
    <a href="${pageContext.request.contextPath}/user/${user.id}/edit">Edit</a>
 </td>
 <td>
    <a href="${pageContext.request.contextPath}/user/${user.id}/delete">Delete</a>
 </td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>