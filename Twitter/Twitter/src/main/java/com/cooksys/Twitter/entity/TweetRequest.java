package com.cooksys.Twitter.entity;



public class TweetRequest {

	
	private Credential credential;
	
	private String content;

	public TweetRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TweetRequest(Credential credential, String content) {
		super();
		this.credential = credential;
		this.content = content;
	}

	/**
	 * @return the credential
	 */
	public Credential getCredential() {
		return credential;
	}

	/**
	 * @param credential the credential to set
	 */
	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
	
	
}
