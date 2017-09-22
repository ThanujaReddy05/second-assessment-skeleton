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
	private Integer id;
	private String username;
	private Profile profile;
//	private Timestamp joined;
	
	
	
	public Credential getCredential() {
		return credential;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TweetUserDto other = (TweetUserDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
}
