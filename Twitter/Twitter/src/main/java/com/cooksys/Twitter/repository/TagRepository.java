package com.cooksys.Twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.Twitter.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

	List<Tag> findAll();
	
	List<Tag> findByLabel(String label);
}
