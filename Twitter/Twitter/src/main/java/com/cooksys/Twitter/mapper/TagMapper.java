package com.cooksys.Twitter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.Twitter.dto.TagDto;
import com.cooksys.Twitter.entity.Tag;

@Mapper(componentModel="spring")
public interface TagMapper {

	public Tag toTag(Tag tag);
	
	public List<TagDto> toTagDto(List<Tag> tag);
	
	public List<Tag> toTag(List<TagDto> tagDto);
	
	
}
