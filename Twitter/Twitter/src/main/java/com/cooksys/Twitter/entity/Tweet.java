///**
// * 
// */
//package com.cooksys.Twitter.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
///**
// * @author ftd-11
// *
// */
//@Entity
//public class Tweet {
//
//	@Id
//	@GeneratedValue
//	private Integer id;
//	
//	private User author;
//	private String posted;
//	
//	@Column(nullable = true)
//	private String content;
//	
//	@Column(nullable = true)
//	private Tweet inReplyTo;
//	
//	@Column(nullable = true)
//	private Tweet repostOf;
//
//	/**
//	 * @return the id
//	 */
//	public Integer getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	/**
//	 * @return the author
//	 */
//	public User getAuthor() {
//		return author;
//	}
//
//	/**
//	 * @param author the author to set
//	 */
//	public void setAuthor(User author) {
//		this.author = author;
//	}
//
//	/**
//	 * @return the posted
//	 */
//	public String getPosted() {
//		return posted;
//	}
//
//	/**
//	 * @param posted the posted to set
//	 */
//	public void setPosted(String posted) {
//		this.posted = posted;
//	}
//
//	/**
//	 * @return the content
//	 */
//	public String getContent() {
//		return content;
//	}
//
//	/**
//	 * @param content the content to set
//	 */
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	/**
//	 * @return the inReplyTo
//	 */
//	public Tweet getInReplyTo() {
//		return inReplyTo;
//	}
//
//	/**
//	 * @param inReplyTo the inReplyTo to set
//	 */
//	public void setInReplyTo(Tweet inReplyTo) {
//		this.inReplyTo = inReplyTo;
//	}
//
//	/**
//	 * @return the repostOf
//	 */
//	public Tweet getRepostOf() {
//		return repostOf;
//	}
//
//	/**
//	 * @param repostOf the repostOf to set
//	 */
//	public void setRepostOf(Tweet repostOf) {
//		this.repostOf = repostOf;
//	}
//
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
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Tweet other = (Tweet) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
//	
//	
//}
