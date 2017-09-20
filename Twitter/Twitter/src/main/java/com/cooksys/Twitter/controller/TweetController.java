package com.cooksys.Twitter.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.Twitter.dto.ContextDto;
import com.cooksys.Twitter.dto.CredentialDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.dto.TweetUserDto;
import com.cooksys.Twitter.dto.tagDto;
import com.cooksys.Twitter.mapper.TweetMapper;
import com.cooksys.Twitter.service.TweetService;
import com.cooksys.Twitter.service.TagService;

@RestController
@RequestMapping("tweets")
public class TweetController {

	private TweetService tweetService;
//	private TweetMapper tweetMapper;
//	private TagService tagService;
//	private TweetController contextService;

	public TweetController(TweetService tweetService) {
	this.tweetService = tweetService;
//	this.tweetMapper = tweetMapper;
//	this.tagService = tagService;
//	this.contextService = contextService;
	}
	
	@GetMapping
	public TweetDto[] GetAllTweets(){
		return tweetService.GetTweets();
	}
	
	@PostMapping
	public TweetDto postTweet(@RequestBody TweetDto tweetDto){
		return tweetService.postNewTweet(tweetDto);
	}
	
	@GetMapping("tweets/{id}")
	public TweetDto getTwet(@PathVariable Integer id){
		return tweetService.getTweet(id);
	}
	
	@DeleteMapping("tweets/{id}")
	public TweetDto deleteTweet(@PathVariable Integer id){
		return tweetService.deleteTweet(id);
	}
	
	@PostMapping("tweets/{id}/like")
	public void tweetLikes(@PathVariable Integer id, @RequestBody CredentialDto credentialDto){
		tweetService.tweetLike(credentialDto);
	}
	
	@PostMapping("tweets/{id}/reply")
	public TweetDto tweetReply(@PathVariable Integer id, @RequestBody TweetDto tweetDto){
		return tweetService.tweetReply(tweetDto);
	}
	
	@PostMapping("tweets/{id}/repost")
	public TweetDto tweetRepost(@PathVariable Integer id, @RequestBody CredentialDto credentialDto){
		return tweetService.tweetReply(credentialDto);
	}
	
	@GetMapping("tweets/{id}/tags")
	public tagDto[] getHashtags(@PathVariable Integer id){
		return TagService.getTags(id);
	}
	
	@GetMapping("tweets/{id}/like")
	public TweetUserDto[] getLikedUsers(@PathVariable Integer id){
		return tweetService.getLikedUsers(id);
	}
	
	@GetMapping("tweets/{id}/context")
	public ContextDto getContext(@PathVariable Integer id){
		return tweetService.getContext(id);
	}
	
	@GetMapping("tweets/{id}/replies")
	public TweetDto[] getReplies(@PathVariable Integer id){
		return tweetService.getTweetReplies(id);
	}
	
	
	@GetMapping("tweets/{id}/reposts")
	public TweetDto[] getReposts(@PathVariable Integer id){
		return tweetService.getTweetRepost(id);
	}
	
	
	@GetMapping("tweets/{id}/mentions")
	public TweetUserDto[] getUserMentions(@PathVariable Integer id){
		return tweetService.getMentions(id);
	}
	
	
	
	
	
	
	
	
}
