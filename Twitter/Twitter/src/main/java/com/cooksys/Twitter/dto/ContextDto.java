package com.cooksys.Twitter.dto;

import java.util.List;

import com.cooksys.Twitter.entity.Tweet;

public class ContextDto {
	private Tweet target;
	private List<Tweet> before;
	private List<Tweet> after;
	
			
	public ContextDto() {
		
	}	
	
	public ContextDto(Tweet target, List<Tweet> before, List<Tweet> after) {
		this.target = target;
		this.before = before;
		this.after = after;
	}
	
	
	/*
	 * Getters and Setters
	 */
	
	public Tweet getTarget() {
		return target;
	}

	public void setTarget(Tweet target) {
		this.target = target;
	}

	public List<Tweet> getBefore() {
		return before;
	}

	public void setBefore(List<Tweet> before) {
		this.before = before;
	}

	public List<Tweet> getAfter() {
		return after;
	}

	public void setAfter(List<Tweet> after) {
		this.after = after;
	}	
}
