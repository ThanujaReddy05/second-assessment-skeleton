package com.cooksys.Twitter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.Twitter.dto.TagDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.entity.Tag;
import com.cooksys.Twitter.mapper.TagMapper;
import com.cooksys.Twitter.repository.TagRepository;

@Service
public class TagService {

	private TagRepository tagRepo;
	private TagMapper tagMapper;

	public TagService(TagRepository tagRepo) {
		this.tagRepo = tagRepo;
	}
	public List<TagDto> getHashtags() {
		return tagMapper.toTagDto(tagRepo.findAll());
	}

	public List<TagDto> getTaggedTweets(String label) {
		return tagMapper.toTagDto(tagRepo.findByLabel(label));
	}

}
