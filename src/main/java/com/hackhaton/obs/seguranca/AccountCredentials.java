package com.hackhaton.obs.seguranca;

public class AccountCredentials {
	
	private String username;
	private String password;
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		System.out.println(username);
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		System.out.println(password);
		this.password = password;
	}
}
