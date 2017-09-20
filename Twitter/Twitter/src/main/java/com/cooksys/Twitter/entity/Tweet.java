/**
 * 
 */
package com.cooksys.Twitter.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

/**
 * @author ftd-11
 *
 */
@Entity
public class Tweet {
	
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private TweetUser author;
	private Timestamp posted;
	
	@Column(nullable = true)
	private String content;
	
	@ManyToOne
	private Tweet inReplyTo;
	
	@ManyToOne	
	private Tweet repostOf;
	
	@Column(nullable = false)
	private boolean active;

		
	
	@ManyToMany(mappedBy = "tweets")
	private Set<Tag> hashtag;
	
	
	@ManyToMany(mappedBy = "likedTweets")
	private Set<TweetUser> likes;
	
	@ManyToMany(mappedBy = "mentions")
	private Set<TweetUser> mentionedUsers;
	
	
	@OneToMany(mappedBy = "inReplyTo")
	private List<Tweet> replies;
	
	
	@OneToMany(mappedBy = "repostOf")
	private List<Tweet> reposts;
	
	
	
	
	
	
	
	
	

	

	/**
	 * @return the hashtag
	 */
	public Set<Tag> getHashtag() {
		return hashtag;
	}

	/**
	 * @param hashtag the hashtag to set
	 */
	public void setHashtag(Set<Tag> hashtag) {
		this.hashtag = hashtag;
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
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the likes
	 */
	public Set<TweetUser> getLikes() {
		return likes;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(Set<TweetUser> likes) {
		this.likes = likes;
	}

	/**
	 * @return the mentionedUsers
	 */
	public Set<TweetUser> getMentionedUsers() {
		return mentionedUsers;
	}

	/**
	 * @param mentionedUsers the mentionedUsers to set
	 */
	public void setMentionedUsers(Set<TweetUser> mentionedUsers) {
		this.mentionedUsers = mentionedUsers;
	}

	/**
	 * @return the replies
	 */
	public List<Tweet> getReplies() {
		return replies;
	}

	/**
	 * @param replies the replies to set
	 */
	public void setReplies(List<Tweet> replies) {
		this.replies = replies;
	}

	/**
	 * @return the reposts
	 */
	public List<Tweet> getReposts() {
		return reposts;
	}

	/**
	 * @param reposts the reposts to set
	 */
	public void setReposts(List<Tweet> reposts) {
		this.reposts = reposts;
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
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
