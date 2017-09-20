package com.cooksys.Twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.Twitter.entity.TweetUser;

public interface TweetUserRepository extends JpaRepository<TweetUser, Integer>  {
	
	TweetUser findByUsername(String username);
	
	List<TweetUser> findAll();
	
	TweetUser save(TweetUser newUser);

}
