package com.cooksys.Twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.Twitter.entity.TweetUser;

public interface TweetUserRepository extends JpaRepository<TweetUser, Integer>  {
		
	List<TweetUser> findAll();
	
	TweetUser findByUsername(String username);
	
	TweetUser save(TweetUser newUser);
	
	TweetUser findByUsernameAndActiveTrue(String username);

}
