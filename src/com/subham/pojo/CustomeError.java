package com.subham.pojo;

public class CustomeError {
	
	private String errorMessage;
	private int statusCode;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public CustomeError(String errorMessage, int statusCode) {
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}
	public CustomeError() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
