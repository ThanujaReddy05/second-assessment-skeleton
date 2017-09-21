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


@Entity
public class Tweet {	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private TweetUser author;
	private Long posted;
	
	@Column(nullable = true)
	private String content;
	
	@Column(nullable = false)
	private boolean active;
	
	@ManyToOne
	private Tweet inReplyTo;
	
	@ManyToOne	
	private Tweet repostOf;		
	
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Tweet getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(Tweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public Tweet getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(Tweet repostOf) {
		this.repostOf = repostOf;
	}

	public Set<Tag> getHashtag() {
		return hashtag;
	}

	public void setHashtag(Set<Tag> hashtag) {
		this.hashtag = hashtag;
	}

	public Set<TweetUser> getLikes() {
		return likes;
	}

	public void setLikes(Set<TweetUser> likes) {
		this.likes = likes;
	}

	public Set<TweetUser> getMentionedUsers() {
		return mentionedUsers;
	}

	public void setMentionedUsers(Set<TweetUser> mentionedUsers) {
		this.mentionedUsers = mentionedUsers;
	}

	public List<Tweet> getReplies() {
		return replies;
	}

	public void setReplies(List<Tweet> replies) {
		this.replies = replies;
	}

	public List<Tweet> getReposts() {
		return reposts;
	}

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
