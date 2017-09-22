/**
 * 
 */
package com.cooksys.Twitter.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ftd-11
 *
 */
@Entity
public class TweetUser {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true, nullable = false )
	private String username;
	
	@Embedded
	private Profile profile;
	
	@Column(updatable = false)
	private Long joined;
	
	@Column(nullable = false)
	private boolean active;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Credential credential; 	
	
	@OneToMany(mappedBy = "author")
	private List<Tweet> tweet;
	
	@ManyToMany
	private Set<TweetUser> following;
	
	@ManyToMany
	private Set<TweetUser> followers;
	
	@ManyToMany
	private Set<Tweet> likedTweets;
	
	@ManyToMany
	private Set<Tweet> mentions;
	
	
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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Long getJoined() {
		return joined;
	}

	public void setJoined(Long joined) {
		this.joined = joined;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public List<Tweet> getTweet() {
		return tweet;
	}

	public void setTweet(List<Tweet> tweet) {
		this.tweet = tweet;
	}

	@JsonIgnore
	public Set<TweetUser> getFollowing() {
		return following;
	}

	public void setFollowing(Set<TweetUser> following) {
		this.following = following;
	}

	@JsonIgnore
	public Set<TweetUser> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<TweetUser> followers) {
		this.followers = followers;
	}

	@JsonIgnore
	public Set<Tweet> getLikedTweets() {
		return likedTweets;
	}

	public void setLikedTweets(Set<Tweet> likedTweets) {
		this.likedTweets = likedTweets;
	}

	@JsonIgnore
	public Set<Tweet> getMentions() {
		return mentions;
	}

	public void setMentions(Set<Tweet> mentions) {
		this.mentions = mentions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TweetUser other = (TweetUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
