package com.cooksys.Twitter.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.Twitter.entity.TweetUser;

@Transactional
public interface TweetUserRepository extends JpaRepository<TweetUser, Integer>  {
		
	List<TweetUser> findAll();
	
	TweetUser findByUsername(String username);
	
	TweetUser saveAndFlush(TweetUser newUser);
	
	TweetUser findByUsernameAndActiveTrue(String username);

//	TweetUser findByUsernameAndPasswordAndActiveTrue(String username, String password);

}
