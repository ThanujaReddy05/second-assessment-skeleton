package com.cooksys.Twitter.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import com.cooksys.Twitter.entity.Tweet;

public class TagDto {
	
	private Integer id;	
	private String label;	
	private Long firstUsed;
	private Long lastUsed;	
	private List<Tweet> tweets;
	
	
	public TagDto() {
		
	}
			
	public TagDto(String label, Long firstUsed, Long lastUsed, List<Tweet> tweets) {
		this.label = label;
		this.firstUsed = firstUsed;
		this.lastUsed = lastUsed;
		this.tweets = tweets;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getFirstUsed() {
		return firstUsed;
	}

	public void setFirstUsed(Long firstUsed) {
		this.firstUsed = firstUsed;
	}

	public Long getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Long lastUsed) {
		this.lastUsed = lastUsed;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

}
