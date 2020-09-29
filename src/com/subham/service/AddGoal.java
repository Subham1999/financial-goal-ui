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
import com.subham.pojo.UserData;


@WebServlet({ "/AddGoal", "/addgoal" })
public class AddGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", request.getParameter("name"));
		map.put("amount", request.getParameter("amount"));
		
		String jsonMap = new Gson().toJson(map);
		
		
		System.out.println(jsonMap);
		
		UserData userdata = (UserData) request.getSession().getAttribute("userdata");
		System.out.println(userdata);
		String url = new String("http://localhost:8090/users/"+userdata.getUsername()+"/goals");
		
		HttpRequest httpRequest = HttpRequest.newBuilder()
								.header("Content-type", "application/json")
								.POST(BodyPublishers.ofString(jsonMap))
								.uri(URI.create(url))
								.build();
		
		HttpClient httpClient = HttpClient.newHttpClient();
		
		HttpResponse<String> httpResponse;
		try {
			httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			if (httpResponse.statusCode() == 200) {
				response.sendRedirect("index.jsp");
			} else {
				CustomeError error = new CustomeError("Something is not right", httpResponse.statusCode());
				request.getSession().setAttribute("error", error);
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e) {
			CustomeError error = new CustomeError("Something is not right", 404);
			request.getSession().setAttribute("error", error);
			response.sendRedirect("error.jsp");
		}
	}

}
