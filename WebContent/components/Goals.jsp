<%@page import="java.util.Date"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.subham.pojo.UserData"%>
<%
if(session.getAttribute("userdata") == null) return;
%>

<%@page import="java.net.URI"%>
<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.subham.pojo.FinancialGoal"%>
<%@page import="java.util.List"%>
<%@page import="java.net.http.HttpClient"%>
<%@page import="java.net.http.HttpResponse"%>
<%@page import="java.net.http.HttpRequest"%>

<%
String json = "";

String url = "http://localhost:8090/users/" + userdata.getUsername() + "/goals";

double walletBalance = userdata.getWallet();
HttpRequest req = HttpRequest.newBuilder()
		.header("Content-type", "application/json")
		.GET()
		.uri(URI.create(url)).build();

HttpClient client = HttpClient.newBuilder().build();

HttpResponse<String> res;
try {
	res = client.send(req, HttpResponse.BodyHandlers.ofString());
	int code = res.statusCode();
	if (code == 200) {
		json = res.body().toString();
	} else {
		response.sendError(code);
	}
} catch (InterruptedException e) {
	e.printStackTrace();
}

Type listType = new TypeToken<List<FinancialGoal>>() {}.getType();
List<FinancialGoal> list = new Gson().fromJson(json, listType);
%>

<div id="goals-section" class="container">
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">goal name</th>
      <th scope="col">amount</th>
      <th scope="col">created</th>
      <th scope="col">status</th>
      <th scope="col">action</th>
    </tr>
  </thead>
  <tbody>
  <%
  	SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy");
  	int count = 1;
  	for(FinancialGoal eachGoal : list) {
  %>
    <tr>
      <td><%=eachGoal.getName()%></td>
      <td><%=eachGoal.getAmount()+"/--"%></td>
      <td><%=formatter.format(eachGoal.getCreated())%></td>
      <% if(eachGoal.isStatus()) {
    	  	long diffInMillies = Math.abs(eachGoal.getCompleted().getTime() - new Date().getTime());
  			long minutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
    	  %>
    	  	<td class="bg-success"  data-toggle="tooltip" data-placement="top" title="<%=minutes%> minutes ago">done</td>
    	  <%
    	  } else {
    		  %>
    		<td class="bg-warning">pending</td>  
    		  <%
    	}
    	%>
    	<% if(eachGoal.isStatus()) {
    	  %>
    	  	<td>
    	  			<button type="submit" class="btn-sm btn-outline-primary" disabled>completed on <small><%=formatter.format(eachGoal.getCompleted())%></small></button>
    	  	</td>
    	  <%
    	  } else {
    		  %>
    		<td>
    		<%if (walletBalance >= eachGoal.getAmount()){ %>
    		<form action="completeGoal" method="post">
    			<input type="hidden" value=<%=eachGoal.getId()%> name="id">
    			<input type="hidden" value=<%=eachGoal.getUsername()%> name="username">
    			<input type="hidden" value=<%=eachGoal.getName()%> name="name">
    			<input type="hidden" value=<%=eachGoal.getAmount()%> name="amount">
    			<input type="hidden" value=<%=eachGoal.getCreated()%> name="created">
    			<input type="hidden" value=<%=eachGoal.isStatus()%> name="status">
    			<input type="hidden" value=<%=eachGoal.getCompleted()%> name="completed">
    			
    			<button type="submit" class="btn-sm btn-outline-success">complete</button>
    		</form>
    		<%} else { %>
    			<button type="submit" class="btn-sm btn-outline-warning" disabled>not enough money</button>
    		<%} %>
    		</td>  
    		  <%
    	}
    	%>
    </tr>
    <%
  	}
    %>
  </tbody>
</table>
</div>
