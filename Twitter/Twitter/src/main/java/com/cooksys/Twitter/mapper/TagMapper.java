package com.cooksys.Twitter.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.cooksys.Twitter.dto.TagDto;
import com.cooksys.Twitter.entity.Tag;

@Mapper(componentModel="spring")
public interface TagMapper {

	public Tag toTag(Tag tag);
	
	public Set<TagDto> toTagDto(Set<Tag> tag);
	
	public List<Tag> toTag(List<TagDto> tagDto);
	
	
}
