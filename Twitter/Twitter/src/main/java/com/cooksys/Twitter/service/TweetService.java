package com.cooksys.Twitter.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.Twitter.dto.ContextDto;
import com.cooksys.Twitter.dto.CredentialDto;
import com.cooksys.Twitter.dto.TagDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.dto.TweetRequestDto;
import com.cooksys.Twitter.dto.TweetUserDto;
import com.cooksys.Twitter.entity.Context;
import com.cooksys.Twitter.entity.Tweet;
import com.cooksys.Twitter.entity.TweetRequest;
import com.cooksys.Twitter.entity.TweetUser;
import com.cooksys.Twitter.mapper.ContextMapper;
import com.cooksys.Twitter.mapper.TagMapper;
import com.cooksys.Twitter.mapper.TweetMapper;
import com.cooksys.Twitter.mapper.TweetRequestMapper;
import com.cooksys.Twitter.mapper.TweetUserMapper;
import com.cooksys.Twitter.repository.CredentilasRepository;
import com.cooksys.Twitter.repository.TagRepository;
import com.cooksys.Twitter.repository.TweetRepository;
import com.cooksys.Twitter.repository.TweetUserRepository;


@Service
public class TweetService {
	
	
	
	private TweetRepository tweetRepo;
	private TweetMapper tweetMapper;
	private TweetRequestMapper tweetRequestMapper;
	private TweetUserRepository userRepo;
	private TagRepository tagRepo;
	private CredentilasRepository credentilRepo;
	private TweetUserMapper userMapper;
	private TagMapper tagMapper;
	private ContextMapper contexMapper;
	
	

	public TweetService(TweetRepository tweetRepo, CredentilasRepository credentilRepo, TagRepository tagRepo) {
		this.tweetRepo = tweetRepo;
		this.credentilRepo = credentilRepo; 
		this.tagRepo = tagRepo;
	}

	public List<TweetDto> GetTweets() {
		return tweetMapper.toTweetDtos(tweetRepo.findAll());
	}

	public TweetDto postNewTweet(TweetRequestDto tweetRequestDto) {
		Tweet simpleTweet = null;
//		TweetRequest tweetToAdd = tweetRequestMapper.toTweetRequest(tweetRequestDto);
		TweetUser author = (TweetUser)credentilRepo.findByUsernameAndPasswordAndActiveTrue(tweetRequestDto.getCredential().getUsername(), tweetRequestDto.getCredential().getPassword());
		
		if(author != null){		
			simpleTweet.setContent(tweetRequestDto.getContent());
			simpleTweet.setAuthor(author);
			simpleTweet.setActive(true);
		
			Date date = new Date(0);
//			Long timestamp = new Timestamp(date.getTime());
			simpleTweet.setPosted(date.getTime());
			
			
		//Update any tags/mentions in new tweet
			createTags(tweetRequestDto.getContent(), simpleTweet.getId());
			createMentions(tweetRequestDto.getContent(), simpleTweet.getId());
			
			return tweetMapper.toTweetDto(tweetRepo.saveAndFlush(simpleTweet));
		}
		
		return null;
		
	}

	
	public TweetDto getTweet(Integer id) {
		return tweetMapper.toTweetDto(tweetRepo.findByIdAndActiveTrue(id));
	}
	
	
@Transactional
	public TweetDto deleteTweet(Integer id, CredentialDto credentialDto) {
		Tweet tweetToDelete = tweetRepo.findById(id);
		if(tweetToDelete.getAuthor().getCredential().getUsername().equals(credentialDto.getUsername())){
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

	public TweetDto tweetReply(Integer id,TweetRequestDto tweetRequestDto) {
		Tweet originalTweet = tweetRepo.findByIdAndActiveTrue(id);
		Tweet newTweet = tweetMapper.toTweet(postNewTweet(tweetRequestDto));
		if(originalTweet != null){	
			TweetUser author = newTweet.getAuthor();
			if(author.getCredential().getUsername().equals(tweetRequestDto.getCredential().getUsername())){
				if(author.getCredential().getPassword().equals(tweetRequestDto.getCredential().getPassword())){
					newTweet.setInReplyTo(originalTweet);
					return tweetMapper.toTweetDto(tweetRepo.saveAndFlush(newTweet));
				}
			}
		}
		return null;
	}

	public TweetDto tweetRepost(Integer id,CredentialDto credentialDto) {
		Tweet tweet = tweetRepo.findByIdAndActiveTrue(id);
		if(tweet != null){				
			TweetUser author = tweet.getAuthor();
			if(author.getCredential().getUsername().equals(credentialDto.getUsername())){
				if(author.getCredential().getPassword().equals(credentialDto.getPassword())){
					tweet.getReposts().add(tweet);
					tweet.setRepostOf(tweet);
					return tweetMapper.toTweetDto(tweetRepo.saveAndFlush(tweet));
				}
			}
		}
		
		return null;
	}

	public Set<TweetUserDto> getLikedUsers(Integer id) {
		Tweet tweet = tweetRepo.findByIdAndActiveTrue(id);
		if(tweet != null){			
			return getActiveUsers(tweet.getLikes());
		}
		
		return null;
	}

	public List<TweetDto> getTweetReplies(Integer id) {
		Tweet tweet = tweetRepo.findByIdAndActiveTrue(id);
		if(tweet != null){	
			return getActiveTweets(tweet.getReplies());
		}
		return null;
		
	}

	public List<TweetDto> getTweetRepost(Integer id) {
		Tweet tweet = tweetRepo.findByIdAndActiveTrue(id);
		if(tweet != null){	
			return getActiveTweets(tweet.getReposts());
		}
		
		return null;
	}

	public Set<TweetUserDto> getMentions(Integer id) {
		Tweet tweet = tweetRepo.findByIdAndActiveTrue(id);
		if(tweet != null){	
			return getActiveUsers(tweet.getMentionedUsers());
		}
		return null;
	}

	

	public ContextDto getContext(Integer id) {
		Context context = new Context();
		Tweet tweet = tweetRepo.findByIdAndActiveTrue(id);
		Tweet target = tweet;
		
		if(tweet != null){
			context.setTarget(tweet);
			
			//Get Before tweet chain
			List<Tweet> before = new ArrayList();
			while(tweet.getInReplyTo() != null){
				Tweet tweetRepliedTo = tweet.getInReplyTo();
				if(tweetRepliedTo.isActive()){
					before.add(tweetRepliedTo);
				}
				tweet = tweetRepliedTo;
			}
			Collections.reverse(before);
			context.setBefore(before);
			
			//Get After tweet chain
			List<Tweet> after = new ArrayList();
			List<Tweet> replies = repliesOfReplies(target,after);
			context.setAfter(replies);
			
			return contexMapper.toContextDto(context);
		}
		return null;
	}	
			
		
	public Set<TagDto> getTags(Integer id) {
		Tweet tweet = tweetRepo.findByIdAndActiveTrue(id);
		if(tweet != null){	
			return tagMapper.toTagDto(tweet.getHashtag());
		}
		return null;
		
	}

	//Private method to get all active tweets for a single tweet
	private List<TweetDto> getActiveTweets(List<Tweet> tweetList){
		List<Tweet> activeTweets = new ArrayList();
		tweetList.forEach(tweetI -> {
				if(tweetI.isActive() == true){
					activeTweets.add(tweetI);
				}
			});
		return tweetMapper.toTweetDtos(activeTweets);	

	}
	
	
	/**
	 * 
	 * Private Methods	 
	 * 	 
	 **/
	
	
	//Private method to get all active users for a single tweet
	private Set<TweetUserDto> getActiveUsers(Set<TweetUser> mentionedUsers) {
		Set<TweetUser> activeUsersForTweet = new HashSet();
		activeUsersForTweet.forEach(user -> {
				if(user.isActive() == true){
					activeUsersForTweet.add(user);
				}
			});
		return userMapper.toSetDtos(activeUsersForTweet);	
	}
	
	
	
	private void createMentions(String content, Integer id) {
		
		
	}

	private void createTags(String content, Integer id) {
		// TODO Auto-generated method stub
		
	}

	private List<Tweet> repliesOfReplies(Tweet target, List<Tweet> after) {
		for(Tweet tweet : target.getReplies()){
			if(!tweet.getReplies().isEmpty()){
				after.add(tweet);
				 repliesOfReplies(tweet, after);
			}
			else
			{
				 after.add(tweet);
				
			}
			
		}
	
	Comparator<Tweet> comparator = new Comparator<Tweet>(){
		@Override
		public int compare(Tweet t1, Tweet t2){
			return (int) (t1.getPosted() - t2.getPosted());
		}
	};	
	
	Collections.sort(after, comparator);
	return after;
}

}
