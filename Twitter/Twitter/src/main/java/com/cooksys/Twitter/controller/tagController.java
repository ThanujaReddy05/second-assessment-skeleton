package com.cooksys.Twitter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.Twitter.dto.TagDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.entity.Tag;
import com.cooksys.Twitter.service.TagService;

@RestController
@RequestMapping("tag")
public class tagController {

	private TagService tagService;

	public tagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	
	@GetMapping
	public List<TagDto> getAllTags(){
		return tagService.getHashtags();
	}
	
	
	@GetMapping("tags/{label}")
	public List<TagDto> getTaggedTweets(@PathVariable String label){
		return tagService.getTaggedTweets(label);
	}
}
