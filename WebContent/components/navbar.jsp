<%@page import="com.subham.pojo.UserData"%>
<%
boolean valid = true;
UserData userdata = (UserData) session.getAttribute("userdata");
if (userdata == null) valid = false;
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Financial goals</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <%if(valid){ %>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <strong><%=userdata.getUsername() %></strong>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	        <a class="dropdown-item" href="addGoal.jsp" data-toggle="modal" data-target="#addGoal">add new goal</a>
	        <button class="dropdown-item" data-toggle="modal" data-target=".bd-example-modal-sm">add money to wallet</button>
	        <a class="dropdown-item" href="#">transactions history</a>
        </div>
      </li>
      <li class="nav-item">
      	<div class="nav-link">wallet<span>(Rs.<%=" " + userdata.getWallet()%>)</span></div>
      </li>
      <%} %>
      
    </ul>
    
    <%if(!valid){%>
    <form action="login.jsp" class="form-inline my-2 my-lg-0">
      <button class="btn btn-outline-success m-2 my-2 my-sm-0" type="submit">log in</button>
      <!-- <button class="btn btn-outline-success m-2 my-2 my-sm-0" type="submit">sign up</button> -->
    </form>
    <form action="signup.jsp" class="form-inline my-2 my-lg-0">
      <!-- <button class="btn btn-outline-success m-2 my-2 my-sm-0" type="submit">log in</button> -->
      <button class="btn btn-outline-success m-2 my-2 my-sm-0" type="submit">sign up</button>
    </form>
    <%} else { %>
    <form action="logout" class="form-inline my-2 my-lg-0">
      <button class="btn btn-outline-success m-2 my-2 my-sm-0" type="submit">log out</button>
    </form>
     <%} %>
  </div>
</nav>