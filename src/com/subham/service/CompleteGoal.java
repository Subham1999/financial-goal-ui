package com.subham.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.subham.pojo.CustomeError;
import com.subham.pojo.FinancialGoal;
import com.subham.pojo.User;
import com.subham.pojo.UserData;

/**
 * Servlet implementation class CompleteGoal
 */
@WebServlet({ "/CompleteGoal", "/completeGoal" })
public class CompleteGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FinancialGoal newGoal = new FinancialGoal();
		newGoal.setUsername((String) request.getParameter("username"));
		newGoal.setId(Integer.parseInt((String) request.getParameter("id")));
		String jsonString = new Gson().toJson(newGoal);
		
		String uri = "http://localhost:8090/users/" + newGoal.getUsername() + "/goals/" + newGoal.getId();// + "?status=" + newGoal.isStatus() ;
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.header("Content-type", "application/json")
				.PUT(BodyPublishers.ofString(jsonString))
				.uri(URI.create(uri))
				.build();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response2;
		try {
			response2 = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			if (response2.statusCode() == 200) {
				User user = new Gson().fromJson(response2.body().toString(), User.class);
				UserData newUserData = new UserData();
				newUserData.setUsername(user.getUsername());
				newUserData.setPassword(user.getPassword());
				newUserData.setWallet(user.getWallet());
				request.getSession().setAttribute("userdata", newUserData);
				response.sendRedirect("index.jsp");
			} else {
				CustomeError error = new CustomeError("Can't complete task", response2.statusCode());
				request.getSession().setAttribute("error", error);
				response.sendRedirect("error.jsp");
			}
		} catch (InterruptedException e) {
			CustomeError error = new CustomeError(e.getMessage(), 404);
			request.getSession().setAttribute("error", error);
			response.sendRedirect("error.jsp");
		}
	}

}
