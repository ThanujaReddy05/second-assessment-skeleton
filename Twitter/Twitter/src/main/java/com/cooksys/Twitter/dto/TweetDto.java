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
		TweetDto other = (TweetDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
	
}
