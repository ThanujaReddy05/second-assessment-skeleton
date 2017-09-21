package com.cooksys.Twitter.dto;

import java.sql.Timestamp;


import com.cooksys.Twitter.entity.Credential;
import com.cooksys.Twitter.entity.Tweet;
import com.cooksys.Twitter.entity.TweetUser;

public class TweetDto {

	private Integer id;	
	private TweetUser author;
	private Long posted;	
	private String content;	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TweetUser getAuthor() {
		return author;
	}
	public void setAuthor(TweetUser author) {
		this.author = author;
	}
	public Long getPosted() {
		return posted;
	}
	public void setPosted(Long posted) {
		this.posted = posted;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
}
