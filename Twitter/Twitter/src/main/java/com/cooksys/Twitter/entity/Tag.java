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
	private Long firstUsed;
	
	private Long lastUsed;	
	
	@ManyToMany
	private List<Tweet> tweets;
	
	
	public Tag() {
		
	}
			
	public Tag(String label, Long firstUsed, Long lastUsed, List<Tweet> tweets) {
		super();
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
