package com.cooksys.Twitter.dto;

import java.sql.Timestamp;


import com.cooksys.Twitter.entity.Credential;
import com.cooksys.Twitter.entity.Tweet;
import com.cooksys.Twitter.entity.TweetUser;

public class TweetDto {

private Integer id;
	
	private TweetUser author;
	private Timestamp posted;
	
	private String content;
	
	private Tweet inReplyTo;
	
	private Tweet repostOf;
	
	private Credential credential;

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
	 * @return the author
	 */
	public TweetUser getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(TweetUser author) {
		this.author = author;
	}

	/**
	 * @return the posted
	 */
	public Timestamp getPosted() {
		return posted;
	}

	/**
	 * @param posted the posted to set
	 */
	public void setPosted(Timestamp posted) {
		this.posted = posted;
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

	/**
	 * @return the inReplyTo
	 */
	public Tweet getInReplyTo() {
		return inReplyTo;
	}

	/**
	 * @param inReplyTo the inReplyTo to set
	 */
	public void setInReplyTo(Tweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	/**
	 * @return the repostOf
	 */
	public Tweet getRepostOf() {
		return repostOf;
	}

	/**
	 * @param repostOf the repostOf to set
	 */
	public void setRepostOf(Tweet repostOf) {
		this.repostOf = repostOf;
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
	
	
	
}
