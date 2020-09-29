<%@page import="com.subham.pojo.CustomeError"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>financial goal/error</title>
<%@ include file="components/ui.jsp"%>
<%
	CustomeError error = (CustomeError) session.getAttribute("error");
	session.removeAttribute("error");
	if(error == null){
		response.sendRedirect("index.jsp");
		return;
	}
%>
</head>
<body class="container-fluid">
<%@include file="components/navbar.jsp" %>

<div class="container">
	<div class="jumbotron">
		<h3> <strong><%=error.getErrorMessage() %> <i><%=error.getStatusCode() %></i></strong></h3>
	</div>

</div>

</body>
</html>