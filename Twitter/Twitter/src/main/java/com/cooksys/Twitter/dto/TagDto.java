package com.cooksys.Twitter.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import com.cooksys.Twitter.entity.Tweet;

public class TagDto {
	
	private Integer id;	
	private String label;	
	private Timestamp firstUsed;
	private Timestamp lastUsed;	
	private List<Tweet> tweets;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the firstUsed
	 */
	public Timestamp getFirstUsed() {
		return firstUsed;
	}
	/**
	 * @param firstUsed the firstUsed to set
	 */
	public void setFirstUsed(Timestamp firstUsed) {
		this.firstUsed = firstUsed;
	}
	/**
	 * @return the lastUsed
	 */
	public Timestamp getLastUsed() {
		return lastUsed;
	}
	/**
	 * @param lastUsed the lastUsed to set
	 */
	public void setLastUsed(Timestamp lastUsed) {
		this.lastUsed = lastUsed;
	}
	/**
	 * @return the tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}
	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	
	
	
	
}
