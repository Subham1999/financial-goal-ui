package com.subham.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.subham.pojo.CustomeError;
import com.subham.pojo.User;
import com.subham.pojo.UserData;

/**
 * Servlet implementation class AddMoney
 */
@WebServlet("/addmoney")
public class AddMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMoney() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserData userData = (UserData) request.getSession().getAttribute("userdata");
		double amount = Double.parseDouble((String) request.getParameter("amount"));
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("username", userData.getUsername());
		map.put("wallet", amount);
		
		String jsonString = new Gson().toJson(map);
		
		String uri = "http://localhost:8090/users/" + userData.getUsername() + "/addmoney";
		
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.POST(BodyPublishers.ofString(jsonString))
				.header("Content-type", "application/json")
				.uri(URI.create(uri))
				.build();
		
		HttpClient client = HttpClient.newBuilder().build();
		HttpResponse<String> httpResponse = null;
		try {
			httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			CustomeError error = new CustomeError(e.getLocalizedMessage(), 404);
			request.getSession().setAttribute("error", error);
			response.sendRedirect("error.jsp");
		}
		User newUserData = new Gson().fromJson(httpResponse.body().toString(), User.class);
		userData.setWallet(newUserData.getWallet());
		request.getSession().setAttribute("userdata", userData);
		response.sendRedirect("index.jsp");
		
	}

}









