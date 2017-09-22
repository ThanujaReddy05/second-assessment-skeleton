package com.cooksys.Twitter.service;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
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
import com.cooksys.Twitter.entity.Credential;
import com.cooksys.Twitter.entity.Tag;
import com.cooksys.Twitter.entity.Tweet;
import com.cooksys.Twitter.entity.TweetRequest;
import com.cooksys.Twitter.entity.TweetUser;
import com.cooksys.Twitter.mapper.ContextMapper;
import com.cooksys.Twitter.mapper.CredentialMapper;
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
	private CredentialMapper credentialMapper;



	public TweetService(TweetRepository tweetRepo, CredentilasRepository credentilRepo, TagRepository tagRepo,TweetMapper tweetMapper,TweetRequestMapper tweetRequestMapper,TweetUserRepository userRepo,TagMapper tagMapper,TweetUserMapper userMapper,ContextMapper contexMapper,CredentialMapper credentialMapper) {
		this.tweetRepo = tweetRepo;
		this.credentilRepo = credentilRepo; 
		this.tagRepo = tagRepo;
		this.tweetMapper = tweetMapper;
		this.tweetRequestMapper = tweetRequestMapper;
		this.userRepo = userRepo;
		this.tagMapper = tagMapper;
		this.userMapper = userMapper;
		this.contexMapper = contexMapper;
		this.credentialMapper = credentialMapper;

	}

	public List<TweetDto> GetTweets() {
		return tweetMapper.toTweetDtos(tweetRepo.findAll());
	}

	public TweetDto postNewTweet(TweetRequestDto tweetRequestDto) {
		Tweet simpleTweet = new Tweet();
		Credential credential = tweetRequestDto.getCredential();
		TweetUser author = userRepo.findByUsername(credential.getUsername());

		if(author != null){	
			simpleTweet.setContent(tweetRequestDto.getContent());
			simpleTweet.setAuthor(author);
			simpleTweet.setActive(true);

			Date date = new Date();
			simpleTweet.setPosted(date.getTime());
			tweetRepo.saveAndFlush(simpleTweet);

			//Update any tags/mentions in new tweet
			createTags(tweetRequestDto.getContent(), simpleTweet.getId());
			createMentions(tweetRequestDto.getContent(), simpleTweet.getId());

			return tweetMapper.toTweetDto(simpleTweet);
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
				tweetRepo.saveAndFlush(tweetToDelete);
				return tweetMapper.toTweetDto(tweetToDelete);
			}
		}

		return null;
	}

	
	public void tweetLike(Integer id,CredentialDto credentialDto) {
		Tweet tweetToLike = tweetRepo.findById(id);
		TweetUser user = userRepo.findByUsername(credentialDto.getUsername());
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
					String content = tweet.getContent();
					TweetRequest tweetRequest = new TweetRequest(credentialMapper.toCredential(credentialDto), content);
					TweetDto repost = postNewTweet(tweetRequestMapper.toDto(tweetRequest));
					tweetMapper.toTweet(repost).setRepostOf(tweet);					
					Tweet newp = tweetMapper.toTweet(repost);
					tweetRepo.saveAndFlush(newp);
					return repost;
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
	
	
	
	/**
	 * 
	 * Private Methods	 
	 * 	 
	 **/


	//Private method to get all active tweets for a single tweet
	private List<TweetDto> getActiveTweets(List<Tweet> tweetList){
		List<Tweet> activeTweets = new ArrayList();
		tweetList.forEach(tweetI -> {
			if(tweetI.isActive()){
				activeTweets.add(tweetI);
			}
		});
		return tweetMapper.toTweetDtos(activeTweets);	

	}



	//Private method to get all active users for a single tweet
	private Set<TweetUserDto> getActiveUsers(Set<TweetUser> mentionedUsers) {
		Set<TweetUser> activeUsersForTweet = new HashSet();
		activeUsersForTweet.forEach(user -> {
			if(user.isActive()){
				activeUsersForTweet.add(user);
			}
		});
		return userMapper.toSetDtos(activeUsersForTweet);	
	}


	private void createMentions(String content, Integer id) {
		Pattern tagPattern = Pattern.compile("@(\\w+)");
		Matcher matcher = tagPattern.matcher(content);
		List<String> mentions = new ArrayList();

		while(matcher.find()){
			mentions.add(matcher.group(1));
		}

		for(String mention : mentions){
			Tweet tweet = tweetRepo.findById(id);
			TweetUser user = userRepo.findByUsername(mention);
			user.getMentions().add(tweet);
			userRepo.saveAndFlush(user);
		}
	}

	private void createTags(String content, Integer id) {
		Pattern tagPattern = Pattern.compile("#(\\w+)");
		Matcher matcher = tagPattern.matcher(content);
		List<String> Hashtags = new ArrayList();

		while(matcher.find()){
			Hashtags.add(matcher.group(1));
		}

		for(String tag : Hashtags){
			Date date = new Date(0);
			Tag t = tagRepo.findByLabel(tag);
			Tweet tweet = tweetRepo.findByIdAndActiveTrue(id);
			if(t == null){
				Set<Tweet> tweetList = new HashSet();
				Tag newTag = new Tag(tag.toLowerCase(), date.getTime(),date.getTime(),tweetList);
				newTag.getTweets().add(tweet);
				tagRepo.saveAndFlush(newTag);

			}else{
				t.setLastUsed(date.getTime());
				t.getTweets().add(tweet);
				tagRepo.saveAndFlush(t);
			}
		}
	}

	
	private List<Tweet> repliesOfReplies(Tweet target, List<Tweet> after) {
		for(Tweet tweet : target.getReplies()){
			if(!tweet.getReplies().isEmpty()){
				after.add(tweet);
				repliesOfReplies(tweet, after);
			}
			else{
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
