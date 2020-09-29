package com.subham.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
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

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String username = (String) request.getParameter("username");
		
		if (username == null) response.sendRedirect("index.jsp");
		
		var url = "http://localhost:8090/users/" + username;
		
		
		var req = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		
		var client = HttpClient.newBuilder().build();
		
		// Fetch the response from API
		HttpResponse<String> res;
		try {
			res = client.send(req, HttpResponse.BodyHandlers.ofString());
			String responseObject = res.body();
			User user = new Gson().fromJson(responseObject, User.class);
			int code = res.statusCode();
			if (code == 200 && user != null) {
				UserData userData = new UserData();
				userData.setUsername(user.getUsername());
				userData.setPassword(user.getPassword());
				userData.setWallet(user.getWallet());
				request.getSession().setAttribute("userdata", userData);
				response.sendRedirect("index.jsp");
			} else {
				CustomeError error = new CustomeError("profile not found", code);
				request.getSession().setAttribute("error", error);
				response.sendRedirect("error.jsp");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		
//		UserData userData = new UserData();
//		userData.setUsername("sub_sa");
//		userData.setPassword("nothing");
//		userData.setWallet(1500);
//		request.getSession().setAttribute("userdata", userData);
//		response.sendRedirect("index.jsp");

	}

}
