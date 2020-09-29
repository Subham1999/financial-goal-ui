package com.subham.pojo;

public class UserData {
	private String username;
	private String password;
	private double wallet;
	
	
	public UserData() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "UserData [username=" + username + ", password=" + password + ", wallet=" + wallet + "]";
	}
	
	
	
}
