package com.cooksys.Twitter.mapper;

import com.cooksys.Twitter.dto.UserDto;
import com.cooksys.Twitter.entity.User;

public interface UserMapper {

	public UserDto toUserDto(User user);
	public User toUser(UserDto userDto);

}
