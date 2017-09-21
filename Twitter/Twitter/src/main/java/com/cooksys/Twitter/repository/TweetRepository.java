package com.cooksys.Twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.Twitter.entity.Tweet;


public interface TweetRepository extends JpaRepository<Tweet, Integer> {

	List<Tweet> findAll();
	
	Tweet findByIdAndActiveTrue(Integer id);
	
	Tweet findById(Integer id);
	 
	
	
}
