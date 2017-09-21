package com.cooksys.Twitter.entity;


public class TweetRequest {
	
	private Credential credential;
	
	private String content;

	public TweetRequest() {
		
	}

	public TweetRequest(Credential credential, String content) {
		super();
		this.credential = credential;
		this.content = content;
	}
	

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
