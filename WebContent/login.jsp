<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>financial goal/login</title>
<%@ include file="components/ui.jsp"%>
</head>
<body class="container-fluid">
<%@include file="components/navbar.jsp" %>
<div class="container">
<form action="login" method="post">
	<div class="form-group">
    	<label for="username">user name</label>
    	<input type="text" class="form-control" id="username" name="username" placeholder="username" required>
  	</div>
 	<div class="form-group">
    	<label for="password">password</label>
    	<input type="password" class="form-control" id="password" name="password" placeholder="password" required>
  	</div>
  	<button type="submit" class="btn btn-outline-primary">login</button>
</form>
</div>
</body>
</html>