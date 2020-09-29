<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>financial goal/signup</title>
<%@ include file="components/ui.jsp"%>
</head>
<body class="container-fluid">
<%@include file="components/navbar.jsp" %>

<div class="container">
<form action="signup" method="post">
	<div class="form-group">
    	<label for="username">user name</label>
    	<input type="text" class="form-control" id="username" name="username" placeholder="username" required>
  	</div>
  	<div class="form-group">
    	<label for="firstname">first name<br><small>Should start with capital</small></label>
    	<input type="text" class="form-control" id="firstname" name="firstname" placeholder="first name" required>
  	</div>
  	<div class="form-group">
    	<label for="lastname">last name<br><small>Should start with capital</small></label>
    	<input type="text" class="form-control" id="lastname" name="lastname" placeholder="last name" required>
  	</div>
	  <div class="form-group">
	    <label for="mail">Email address</label>
	    <input type="email" class="form-control" id="mail" name="mail" placeholder="name@example.com" required>
	  </div>
 	<div class="form-group">
    	<label for="password">password</label>
    	<input type="password" class="form-control" id="password" name="password" placeholder="password" required>
  	</div>
  	<button type="submit" class="btn btn-outline-primary">sign up</button>
</form>
</div>


</body>
</html>