package com.cooksys.Twitter.mapper;

import org.mapstruct.Mapper;

import com.cooksys.Twitter.dto.TweetRequestDto;
import com.cooksys.Twitter.entity.TweetRequest;

@Mapper(componentModel="spring")
public interface TweetRequestMapper {

	
	public TweetRequestDto toDto(TweetRequest tweetRequest);
	
	public TweetRequest toTweetRequest(TweetRequestDto tweetRequestDto);
	
}
