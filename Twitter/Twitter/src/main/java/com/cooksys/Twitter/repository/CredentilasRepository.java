package com.cooksys.Twitter.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.Twitter.entity.Credential;
import com.cooksys.Twitter.entity.TweetUser;


@Transactional
public interface CredentilasRepository extends JpaRepository<Credential, Integer> {
	
	public TweetUser findByUsernameAndPassword(String username, String password);
	


}
