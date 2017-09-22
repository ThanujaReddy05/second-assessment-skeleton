/**
 * 
 */
package com.cooksys.Twitter.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.Twitter.dto.CredentialDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.dto.TweetUserDto;
import com.cooksys.Twitter.entity.Credential;
import com.cooksys.Twitter.entity.Tweet;
import com.cooksys.Twitter.entity.TweetUser;
import com.cooksys.Twitter.mapper.TweetMapper;
import com.cooksys.Twitter.mapper.TweetUserMapper;
import com.cooksys.Twitter.repository.CredentilasRepository;
import com.cooksys.Twitter.repository.TweetUserRepository;

/**
 * @author ftd-11
 *
 */
@Service
public class TweetUserService {


	private TweetUserRepository userRepo;
	private TweetUserMapper userMapper;	
	private CredentilasRepository credentialRepo;	
	private TweetMapper tweetMapper;


	public TweetUserService(TweetUserRepository userRepo, TweetMapper tweetMapper,TweetUserMapper userMapper,CredentilasRepository credentialRepo) {
		this.userRepo = userRepo;
		this.credentialRepo = credentialRepo;
		this.tweetMapper = tweetMapper;
		this.userMapper = userMapper;

	}


	//Get all active users
	public List<TweetUserDto> getUsers() {

		return userMapper.toDtos(userRepo.findAll());

	}


	@Transactional
	public TweetUserDto addUser(TweetUserDto userDto) {
		Credential credentials = userDto.getCredential();
		TweetUser newUser = userMapper.toUser(userDto);

		//Check for the Username in database
		if(userRepo.findByCredentialUsernameAndCredentialPasswordAndActiveFalse(credentials.getUsername(), credentials.getPassword()) != null)
		{
			TweetUser user = userRepo.findByCredentialUsernameAndCredentialPassword(credentials.getUsername(), credentials.getPassword());
			user.setActive(true);
			userRepo.save(user);
			return userMapper.toUserDto(user);
		}

		//If user doesn't exists in database add as new user to the table 
		else
		{
			String usename = userDto.getCredential().getUsername();
			Date date = new Date();
			newUser.setUsername(usename);
			newUser.setJoined(date.getTime());
			newUser.setActive(true);
			return userMapper.toUserDto(userRepo.saveAndFlush(userMapper.toUser(userDto)));
		}
	}



	public TweetUserDto getUser(String username) {
		return userMapper.toUserDto(userRepo.findByUsername(username));
	}



	@Transactional
	public TweetUserDto editUser(TweetUserDto userDto) {

		TweetUser userToUpdate = userRepo.findByUsername(userDto.getCredential().getUsername());

		userToUpdate.setProfile(userDto.getProfile());
		userToUpdate.setCredential(userDto.getCredential());
		userToUpdate.setUsername(userDto.getUsername());

		return userMapper.toUserDto(userRepo.saveAndFlush(userToUpdate));


	}

		// Delete user by setting active status to false
	@Transactional
	public TweetUserDto delete(String username, CredentialDto credentialDto) {
		TweetUser user =  userRepo.findByUsername(username);

		if(user.getCredential().getUsername().equals(credentialDto.getUsername())){ 
			if(user.getCredential().getPassword().equals(credentialDto.getPassword())){
				user.setActive(false);
				userRepo.saveAndFlush(user);
			}
		}
		return userMapper.toUserDto(user);
	}


	@Transactional
	public void createFollowing(String username,CredentialDto credentialDto) {
		TweetUser userToFollow =  userRepo.findByUsername(credentialDto.getUsername());
		TweetUser user =  userRepo.findByUsername(username);

		user.getFollowers().add(userToFollow);
		userToFollow.getFollowing().add(user);

		userRepo.saveAndFlush(user);
		userRepo.saveAndFlush(userToFollow);

	}


	@Transactional
	public void deleteFollowing(String username,CredentialDto credentialDto) {
		TweetUser userToUnFollow = userRepo.findByUsername(credentialDto.getUsername());
		TweetUser user = userRepo.findByUsername(username);

		user.getFollowers().remove(userToUnFollow);
		userToUnFollow.getFollowing().remove(user);

		userRepo.saveAndFlush(user);
		userRepo.saveAndFlush(userToUnFollow);

	}


	public List<TweetDto> getFeed(String username) {

		TweetUser user = userRepo.findByUsername(username);
		List<Tweet> feed = new ArrayList();

		//Get tweets of user and to feed
		List<Tweet> userTweets = user.getTweet();
		for(Tweet tweet : userTweets){
			feed.add(tweet);
		}		
		//Get the followers tweet
		Set<TweetUser> followings = user.getFollowing();
		for(TweetUser tempUser : followings){
			List<Tweet> followersTweets = tempUser.getTweet();
			for(Tweet tweet : followersTweets){
				feed.add(tweet);
			}
		}
		//sort the tweets
		Comparator<Tweet> comparator = new Comparator<Tweet>(){
			@Override
			public int compare(Tweet t1, Tweet t2){
				return (int) (t2.getPosted() - t1.getPosted());
			}
		};

		Collections.sort(feed, comparator);

		return tweetMapper.toTweetDtos(feed);

	}

	
	public Set<TweetUserDto> getfollowers(String username) {
		return userMapper.toSetDtos(userRepo.findByUsername(username).getFollowers());
	}


	public Set<TweetUserDto> getfollowing(String username) {
		return userMapper.toSetDtos(userRepo.findByUsername(username).getFollowing());
	}


	public List<TweetDto> getTweets(String username) {
		List<Tweet> tweets = userRepo.findByUsername(username).getTweet();
		Collections.reverse(tweets);
		return tweetMapper.toTweetDtos(tweets);
	}


	public Set<TweetDto> getMentions(String username) {
		return tweetMapper.toSetDto(userRepo.findByUsername(username).getMentions());

	}



}
