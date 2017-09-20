/**
 * 
 */
package com.cooksys.Twitter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.Twitter.dto.UserDto;
import com.cooksys.Twitter.entity.User;
import com.cooksys.Twitter.mapper.UserMapper;
import com.cooksys.Twitter.service.UserService;

/**
 * @author ftd-11
 *
 */
@RestController
@RequestMapping("users")
public class UserController {

	private UserService userService;
	private UserMapper userMapper;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	
	@GetMapping
	public UserDto getAllUsers(){
		return userMapper.toUserDto(userService.getUsers());
	
	}
	
	@PostMapping
	public UserDto addUser(@RequestBody UserDto userDto){
		return userService.addUser(userDto);
	}
	
	
}
