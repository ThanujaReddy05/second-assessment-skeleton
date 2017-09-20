package com.cooksys.Twitter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.entity.Tweet;

@Mapper(componentModel="spring")
public interface TweetMapper {
	
	TweetDto toTweetDto(Tweet tweet);
	
	Tweet toTweet(TweetDto tweetDto);
	
	List<TweetDto> toTweetDtos(List<Tweet> tweets);

}
