/**
 * 
 */
package com.cooksys.Twitter.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.Twitter.dto.CredentialDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.dto.TweetUserDisplayDto;
import com.cooksys.Twitter.dto.TweetUserDto;
import com.cooksys.Twitter.mapper.TweetUserMapper;
import com.cooksys.Twitter.service.TweetUserService;

/**
 * @author ftd-11
 *
 */
@RestController
@RequestMapping("users")
public class TweetUserController {

	private TweetUserService userService;

	public TweetUserController(TweetUserService userService) {
		this.userService = userService;
	}

	
	@GetMapping
	public List<TweetUserDto> getAllUsers(){
		return userService.getUsers();
	
	}
	
	@PostMapping
	public TweetUserDto addUser(@RequestBody TweetUserDto userDto, HttpServletResponse response ){
		return userService.addUser(userDto);
	}
	
	@GetMapping("users/{username}")
	public TweetUserDto getUser(@PathVariable String username)
	{
		return userService.getUser(username);
	}
	
	
	@PatchMapping("users/{username}")
	public TweetUserDto updateUser(@PathVariable String username,@RequestBody TweetUserDto userDto){
		return userService.editUser(userDto);
	}
	
	@DeleteMapping("users/{username}")
	public TweetUserDto deleteUser(@PathVariable String username,@RequestBody CredentialDto credentialDto){
		return userService.delete(username,credentialDto );
	}
	
	@PostMapping("users/{username}/follow")
	public void createFollowing(@PathVariable String username, @RequestBody CredentialDto credentialDto){
		 userService.createFollowing(username,credentialDto);
	}
	
	@PostMapping("users/{username}/unfollow")
	public void unFollow(@PathVariable String username, @RequestBody CredentialDto credentialDto){
		 userService.deleteFollowing(username,credentialDto);
	}
	
	@GetMapping("users/{username}/feed")
	public List<TweetDto> getFeed(@PathVariable String username){
		return userService.getFeed(username);
	 
	}
	
	@GetMapping("user/{username}/tweets")
	public List<TweetDto> getTweets(@PathVariable String username){
		return userService.getTweets(username);
		
	}
	
	
	@GetMapping("user/{username}/mentions")
	public Set<TweetDto> getmentions(@PathVariable String username){
		userService.getMentions(username);
		return null;
	}
	
	
	@GetMapping("user/{username}/followers")
	public  Set<TweetUserDto> getFollowers(@PathVariable String username){
		userService.getfollowers(username);
		return null;
	}
	
	
	
	@GetMapping("user/{username}/following")
	public  Set<TweetUserDto> getFollowing(@PathVariable String username){
		userService.getfollowing(username);
		return null;
	}
	
	
	
}
