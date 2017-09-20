package com.cooksys.Twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.Twitter.entity.Tweet;


public interface TweetRepository extends JpaRepository<Tweet, Integer> {

	
	
}
