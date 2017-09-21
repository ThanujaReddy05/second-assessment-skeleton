package com.cooksys.Twitter.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.entity.Tweet;

@Mapper(componentModel="spring")
public interface TweetMapper {
	
	public TweetDto toTweetDto(Tweet tweet);
	
	public Tweet toTweet(TweetDto tweetDto);
	
	public 	List<TweetDto> toTweetDtos(List<Tweet> tweets);
	
	public Set<TweetDto> toSetDto(Set<Tweet> tweets);

}
