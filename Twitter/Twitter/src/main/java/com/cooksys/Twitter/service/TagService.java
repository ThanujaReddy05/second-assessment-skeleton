package com.cooksys.Twitter.service;

import java.util.List;

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

	public TagService(TagRepository tagRepo,TweetRepository tweetRepo) {
		this.tagRepo = tagRepo;
		this.tweetRepo = tweetRepo;
	}
	public List<TagDto> getHashtags() {
		return tagMapper.toTagDto(tagRepo.findAll());
	}

	public List<TweetDto> getTaggedTweets(String label) {
		return tagMapper.toTagDto(tweetRepo.findByLabel(label));
	}

}
