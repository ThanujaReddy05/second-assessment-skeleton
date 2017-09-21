/**
 * 
 */
package com.cooksys.Twitter.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cooksys.Twitter.entity.Credential;
import com.cooksys.Twitter.entity.Profile;
import com.cooksys.Twitter.entity.Tweet;
import com.cooksys.Twitter.entity.TweetUser;


public class TweetUserDto {

	
	private Credential credential;
//	private Integer id;
	private String username;
	private Profile profile;
//	private Timestamp joined;
	
	
	public Credential getCredential() {
		return credential;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}	
}
