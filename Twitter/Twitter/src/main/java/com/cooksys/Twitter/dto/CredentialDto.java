package com.cooksys.Twitter.dto;

import javax.persistence.Column;

public class CredentialDto {
	
	private String username;
	private String password;	
		
	/*
	 * Getters and Setters
	 */
	
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

}
