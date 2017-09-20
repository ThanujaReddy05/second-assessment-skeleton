package com.cooksys.Twitter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.Twitter.dto.TweetUserDto;
import com.cooksys.Twitter.entity.TweetUser;

@Mapper(componentModel="spring")
public interface TweetUserMapper {

	public TweetUserDto toUserDto(TweetUser user);
	public TweetUser toUser(TweetUserDto userDto);
	public List<TweetUserDto> toDtos(List<TweetUser> userList);

}
