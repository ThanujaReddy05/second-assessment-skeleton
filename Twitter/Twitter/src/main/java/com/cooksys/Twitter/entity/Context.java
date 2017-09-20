package com.cooksys.Twitter.entity;

import java.util.List;

import javax.persistence.OneToOne;

public class Context {
	
	private Tweet target;
	private List<Tweet> before;
	private List<Tweet> after;
	
	
		
	public Context() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Context(Tweet target, List<Tweet> before, List<Tweet> after) {
		super();
		this.target = target;
		this.before = before;
		this.after = after;
	}



	/**
	 * @return the target
	 */
	public Tweet getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(Tweet target) {
		this.target = target;
	}
	/**
	 * @return the before
	 */
	public List<Tweet> getBefore() {
		return before;
	}
	/**
	 * @param before the before to set
	 */
	public void setBefore(List<Tweet> before) {
		this.before = before;
	}
	/**
	 * @return the after
	 */
	public List<Tweet> getAfter() {
		return after;
	}
	/**
	 * @param after the after to set
	 */
	public void setAfter(List<Tweet> after) {
		this.after = after;
	}
	
	
	
	

}
