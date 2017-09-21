package com.cooksys.Twitter.mapper;

import org.mapstruct.Mapper;

import com.cooksys.Twitter.dto.ContextDto;
import com.cooksys.Twitter.entity.Context;

@Mapper(componentModel="spring")
public interface ContextMapper {
	
	public ContextDto toContextDto(Context context);
	
	public Context toContext(ContextDto contextDto);

	
	

}
