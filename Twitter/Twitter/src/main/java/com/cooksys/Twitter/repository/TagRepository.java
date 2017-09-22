package com.cooksys.Twitter.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.Twitter.entity.Tag;
import com.cooksys.Twitter.entity.TweetUser;

@Transactional
public interface TagRepository extends JpaRepository<Tag, Integer> {

	List<Tag> findAll();
	
	Tag findByLabel(String label);

	
}
