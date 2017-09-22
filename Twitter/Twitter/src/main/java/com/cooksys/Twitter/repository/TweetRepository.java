package com.cooksys.Twitter.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.Twitter.entity.Tag;
import com.cooksys.Twitter.entity.Tweet;

@Transactional
public interface TweetRepository extends JpaRepository<Tweet, Integer> {

	List<Tweet> findAll();
	
	Tweet findByIdAndActiveTrue(Integer id);
	
	Tweet findById(Integer id);
	
	Tweet saveAndFlush(Tweet tweet);


	 
	
	
}
