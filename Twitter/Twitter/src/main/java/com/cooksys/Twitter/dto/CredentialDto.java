package com.cooksys.Twitter.dto;

import javax.persistence.Column;

public class CredentialDto {
	
	private Integer id;	
	private String username;
	private String password;	
	private boolean active;
		
	/*
	 * Getters and Setters
	 */
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
