package com.cooksys.Twitter.service;

import org.springframework.stereotype.Service;

import com.cooksys.Twitter.controller.TagController;
import com.cooksys.Twitter.controller.TweetUserController;
import com.cooksys.Twitter.mapper.TweetUserMapper;

@Service
public class ValidateService {

	private TagController tagsController;
	private TweetUserController userController;
	private TweetUserService userService;
	private TweetUserMapper userMapper;
	
	public ValidateService(TagController tagsController, TweetUserController userController, TweetUserService userService,TweetUserMapper userMapper) {
		this.tagsController = tagsController;
		this.userController = userController;
		this.userService = userService;
		this.userMapper = userMapper;
	}

	public boolean tagExists(String label) {
		return (tagsController.getTaggedTweets(label) != null)? true : false;
	}

	public boolean userExists(String username) {
		return (userMapper.toUser(userService.getUser(username)).isActive() == true)? true : false;
	}

	public boolean isUsernameAvailable(String username) {
		return (userController.getUser(username) != null)? false : true;
	}
	
	
}
