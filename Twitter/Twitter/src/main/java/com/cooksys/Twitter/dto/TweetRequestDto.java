package com.cooksys.Twitter.dto;

import com.cooksys.Twitter.entity.Credential;

public class TweetRequestDto {
	
	private Credential credential;	
	private String content;
	
	
	public Credential getCredential() {
		return credential;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
