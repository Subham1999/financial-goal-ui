package com.subham.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
//import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.subham.pojo.CustomeError;
import com.subham.pojo.User;
import com.subham.pojo.UserData;

@WebServlet({ "/Signup", "/signup" })
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public Signup() { }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		
		user.setUsername((String) request.getParameter("username"));
		user.setFirstname((String) request.getParameter("firstname"));
		user.setLastname((String) request.getParameter("lastname"));
		user.setPassword((String) request.getParameter("password"));
		user.setMail((String) request.getParameter("mail"));
		user.setWallet(0);
		
		String jsonUserString = new Gson().toJson(user);
		
		response.getOutputStream().println(jsonUserString);
		
		
		var url = "http://localhost:8090/users";
		
		
		var req = HttpRequest.newBuilder()
				.header("Content-type", "application/json")
				.POST(BodyPublishers.ofString(jsonUserString))
				.uri(URI.create(url)).build();
		var client = HttpClient.newBuilder().build();
		
		// Fetch the response from API
		HttpResponse<String> res = null;
		try {
			res = client.send(req, HttpResponse.BodyHandlers.ofString());
			user = new Gson().fromJson(res.body().toString(), User.class);
			int code = res.statusCode();
			if (code == 200 && user != null) {
				
				UserData userData = new UserData();
				userData.setUsername(user.getUsername());
				userData.setPassword(user.getPassword());
				userData.setWallet(user.getWallet());
				
				request.getSession().setAttribute("userdata", userData);
				response.sendRedirect("index.jsp");
			} else {
				CustomeError error = new CustomeError("username already exists in our database", 0);
				request.getSession().setAttribute("error", error);
				response.sendRedirect("error.jsp");
			}
		} catch (InterruptedException e) {
			CustomeError error = new CustomeError(e.getLocalizedMessage(), res.statusCode());
			request.getSession().setAttribute("error", error);
			response.sendRedirect("error.jsp");
		}
	}

}











