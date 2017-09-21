package com.cooksys.Twitter.dto;

import java.sql.Timestamp;

import com.cooksys.Twitter.entity.Credential;
import com.cooksys.Twitter.entity.Profile;

public class TweetUserDisplayDto {

	
	private String username;
	private Profile profile;
//	private Timestamp joined;
	private Credential credential;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
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
	 * @return the joined
	 */
//	public Timestamp getJoined() {
//		return joined;
//	}
//	/**
//	 * @param joined the joined to set
//	 */
//	public void setJoined(Timestamp joined) {
//		this.joined = joined;
//	}
//	
	
	
}
