package com.cooksys.Twitter.entity;

public class Context {
	
	private Tweet target;
	private Tweet[] before;
	private Tweet[] after;
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
	public Tweet[] getBefore() {
		return before;
	}
	/**
	 * @param before the before to set
	 */
	public void setBefore(Tweet[] before) {
		this.before = before;
	}
	/**
	 * @return the after
	 */
	public Tweet[] getAfter() {
		return after;
	}
	/**
	 * @param after the after to set
	 */
	public void setAfter(Tweet[] after) {
		this.after = after;
	}
	
	
	
	

}
