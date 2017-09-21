package com.cooksys.Twitter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.Twitter.dto.ContextDto;
import com.cooksys.Twitter.dto.CredentialDto;
import com.cooksys.Twitter.dto.TagDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.dto.TweetUserDto;
import com.cooksys.Twitter.entity.Tweet;
import com.cooksys.Twitter.entity.TweetUser;
import com.cooksys.Twitter.mapper.TweetMapper;
import com.cooksys.Twitter.mapper.TweetUserMapper;
import com.cooksys.Twitter.repository.CredentilasRepository;
import com.cooksys.Twitter.repository.TagRepository;
import com.cooksys.Twitter.repository.TweetRepository;
import com.cooksys.Twitter.repository.TweetUserRepository;


@Service
public class TweetService {
	
	
	
	private TweetRepository tweetRepo;
	private TweetMapper tweetMapper;
	private TweetUserRepository userRepo;
	private TagRepository tagRepo;
	private CredentilasRepository credentilRepo;
	

	public TweetService(TweetRepository tweetRepo, CredentilasRepository credentilRepo, TagRepository tagRepo) {
		this.tweetRepo = tweetRepo;
		this.credentilRepo = credentilRepo; 
		this.tagRepo = tagRepo;
	}

	public List<TweetDto> GetTweets() {
		return tweetMapper.toTweetDtos(tweetRepo.findAll());
	}

	public TweetDto postNewTweet(TweetDto tweetDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto getTweet(Integer id) {
		return tweetMapper.toTweetDto(tweetRepo.findByIdAndActiveTrue(id));
	}
	
	
@Transactional
	public TweetDto deleteTweet(Integer id, CredentialDto credentialDto) {
		Tweet tweetToDelete = tweetRepo.findById(id);
		if(tweetToDelete.getAuthor().getCredential().getUsername().equals(credentialDto.getUsername()))
		{
			if(tweetToDelete.getAuthor().getCredential().getPassword().equals(credentialDto.getPassword())){
				tweetToDelete.setActive(false);
				return tweetMapper.toTweetDto(tweetRepo.saveAndFlush(tweetToDelete));
			}
		}
		
		return null;
		
	}

	public void tweetLike(Integer id,CredentialDto credentialDto) {
		Tweet tweetToLike = tweetRepo.findById(id);
		TweetUser user = credentilRepo.findByUsernameAndPasswordAndActiveTrue(credentialDto.getUsername(),credentialDto.getPassword());
		if(user != null){
			tweetToLike.getLikes().add(user);
			user.getLikedTweets().add(tweetToLike);
			
			tweetRepo.saveAndFlush(tweetToLike);
			userRepo.saveAndFlush(user);
			
		}
		
	}

	public TweetDto tweetReply(TweetDto tweetDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto tweetReply(CredentialDto credentialDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetUserDto[] getLikedUsers(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto[] getTweetReplies(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto[] getTweetRepost(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetUserDto[] getMentions(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ContextDto getContext(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TagDto> getTags(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
