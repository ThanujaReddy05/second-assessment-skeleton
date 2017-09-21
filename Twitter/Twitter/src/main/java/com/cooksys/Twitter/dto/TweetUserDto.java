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

/**
 * @author ftd-11
 *
 */
public class TweetUserDto {

	
	private Credential credential;
//	private Integer id;
	private String username;
	private Profile profile;
//	private Timestamp joined;
	
	
//	private boolean active;
//	
//	
//	private List<Tweet> tweet;
//	
//	
//	private Set<TweetUser> following;
//	
//	
//	private Set<TweetUser> followers;
//	
//	
//	private Set<Tweet> likedTweets;
//	
//	
//	private Set<Tweet> mentions;
	
	/**
	 * @return the id
	 */
//	public Integer getId() {
//		return id;
//	}
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Integer id) {
//		this.id = id;
//	}
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
	 * @return the active
	 */
//	public boolean isActive() {
//		return active;
//	}
//	/**
//	 * @param active the active to set
//	 */
//	public void setActive(boolean active) {
//		this.active = active;
//	}
//	/**
//	 * @return the tweet
//	 */
//	public List<Tweet> getTweet() {
//		return tweet;
//	}
//	/**
//	 * @param tweet the tweet to set
//	 */
//	public void setTweet(List<Tweet> tweet) {
//		this.tweet = tweet;
//	}
//	/**
//	 * @return the following
//	 */
//	public Set<TweetUser> getFollowing() {
//		return following;
//	}
//	/**
//	 * @param following the following to set
//	 */
//	public void setFollowing(Set<TweetUser> following) {
//		this.following = following;
//	}
//	/**
//	 * @return the followers
//	 */
//	public Set<TweetUser> getFollowers() {
//		return followers;
//	}
//	/**
//	 * @param followers the followers to set
//	 */
//	public void setFollowers(Set<TweetUser> followers) {
//		this.followers = followers;
//	}
//	/**
//	 * @return the likedTweets
//	 */
//	public Set<Tweet> getLikedTweets() {
//		return likedTweets;
//	}
//	/**
//	 * @param likedTweets the likedTweets to set
//	 */
//	public void setLikedTweets(Set<Tweet> likedTweets) {
//		this.likedTweets = likedTweets;
//	}
//	/**
//	 * @return the mentions
//	 */
//	public Set<Tweet> getMentions() {
//		return mentions;
//	}
//	/**
//	 * @param mentions the mentions to set
//	 */
//	public void setMentions(Set<Tweet> mentions) {
//		this.mentions = mentions;
//	}
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "TweetUserDto [username=" + username + ", profile=" + profile + ", joined=" + joined + "]";
//	}
//	/**
//	 * @return the joined
//	 */
//	public Timestamp getJoined() {
//		return joined;
//	}
//	/**
//	 * @param joined the joined to set
//	 */
//	public void setJoined(Timestamp joined) {
//		this.joined = joined;
//	}
	
	
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
//	/* (non-Javadoc)
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		TweetUserDto other = (TweetUserDto) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
	
	
	
}
