/**
 * 
 */
package com.cooksys.Twitter.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author ftd-11
 *
 */
@Entity
public class Tag {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true)
	private String label;
	
	@Column(updatable = false)
	private Timestamp firstUsed;
	private Timestamp lastUsed;
	
	
	@ManyToMany
	private List<Tweet> tweets;
	
	
	
	
	
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	public Tag(String label, Timestamp firstUsed, Timestamp lastUsed, List<Tweet> tweets) {
		super();
		this.label = label;
		this.firstUsed = firstUsed;
		this.lastUsed = lastUsed;
		this.tweets = tweets;
	}







	/**
	 * @return the tweet
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}
	/**
	 * @param tweet the tweet to set
	 */
	public void setTweet(List<Tweet> tweets) {
		this.tweets = tweets;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
