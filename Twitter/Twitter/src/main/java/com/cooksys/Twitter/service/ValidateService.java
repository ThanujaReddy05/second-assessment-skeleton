package com.cooksys.Twitter.service;

import org.springframework.stereotype.Service;

import com.cooksys.Twitter.controller.TagController;
import com.cooksys.Twitter.controller.TweetUserController;
import com.cooksys.Twitter.mapper.TweetUserMapper;
import com.cooksys.Twitter.repository.TweetUserRepository;

@Service
public class ValidateService {

	private TagController tagsController;
	private TweetUserController userController;
	private TweetUserService userService;
	private TweetUserMapper userMapper;
	private TweetUserRepository userRepo;
	
	public ValidateService(TagController tagsController, TweetUserController userController, TweetUserService userService,TweetUserMapper userMapper,TweetUserRepository userRepo) {
		this.tagsController = tagsController;
		this.userController = userController;
		this.userService = userService;
		this.userMapper = userMapper;
		this.userRepo = userRepo;
	}

	public boolean tagExists(String label) {
		return (tagsController.getTaggedTweets(label) != null)? true : false;
	}

	public boolean userExists(String username) {
		return (userRepo.findByUsernameAndActiveTrue(username) != null)? false : true;
	}

	public boolean isUsernameAvailable(String username) {
		return (userRepo.findByUsernameAndActiveTrue(username) != null)? true :false ;
	}
	
	
}
