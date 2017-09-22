package com.cooksys.Twitter.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.Twitter.dto.TagDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.entity.Tag;
import com.cooksys.Twitter.mapper.TagMapper;
import com.cooksys.Twitter.repository.TagRepository;
import com.cooksys.Twitter.repository.TweetRepository;

@Service
public class TagService {

	private TagRepository tagRepo;
	private TagMapper tagMapper;
	private TweetRepository tweetRepo;

	public TagService(TagRepository tagRepo,TweetRepository tweetRepo,TagMapper tagMapper) {
		this.tagRepo = tagRepo;
		this.tweetRepo = tweetRepo;
		this.tagMapper = tagMapper;
	}
	public List<TagDto> getHashtags() {
		return tagMapper.toTagDto(tagRepo.findAll());
	}

	public TagDto getTaggedTweets(String label) {
		return tagMapper.toTagDto(tagRepo.findByLabel(label));
	}

}
