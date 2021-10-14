package com.vsoft.shopping.model;

public class UserResponse {
	private String token;
	private String authority;
	
	public UserResponse(String tokenn, String authority) {
		this.token = tokenn ;
		this.authority = authority;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
