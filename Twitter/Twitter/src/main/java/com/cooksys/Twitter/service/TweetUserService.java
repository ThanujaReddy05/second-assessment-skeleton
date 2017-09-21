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
import com.cooksys.Twitter.dto.DeletedUserDto;
import com.cooksys.Twitter.dto.TweetDto;
import com.cooksys.Twitter.dto.TweetUserDisplayDto;
import com.cooksys.Twitter.dto.TweetUserDto;
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
	private DeletedUserDto deletedUserDto;
	private CredentilasRepository credentialRepo;
	private TweetUserDisplayDto userDisplayDto;
	private TweetMapper tweetMapper;

//	String timestamp = new SimpleDateFormat("MM.dd.yyyy  HH.mm.ss").format(new Date());



	public TweetUserService(TweetUserRepository userRepo, CredentilasRepository credentialRepo) {
		this.userRepo = userRepo;
		this.credentialRepo = credentialRepo;
	}





	public List<TweetUserDto> getUsers() {
	return userMapper.toDtos(userRepo.findAll());
		
//		  userDtoToDisplyDtos(tempDto);
	}

	
	
	
@Transactional
	public TweetUserDto addUser(TweetUserDto userDto) {
//	TweetUserDto TweetuserDto = null;
//	TweetUserDto userDto = useroDisplyDtoToUserDto(userDisplyDto,TweetuserDto);
		TweetUser newUser = userMapper.toUser(userDto);
		//Check for the Username in database
		if(credentialRepo.findByUsernameAndPasswordAndActiveFalse(userDto.getCredential().getUsername(), userDto.getCredential().getPassword()) != null)
		{
			System.out.println(userDto.getCredential().getUsername() + "Already exists in DB");
			userMapper.toUser(userDto).setActive(true);
			return userDto;
//			return userDtoToDisplyDto(userDto,null);
		}
		//Add as new user to the table
		else
		{
//			String timestamp = new SimpleDateFormat("MM.dd.yyyy  HH.mm.ss").format(new Date());
			 Date date = new Date();
//			Timestamp timestamp = new Timestamp(date.getTime());
			
			newUser.setJoined(date.getTime());
			newUser.setActive(true);
			newUser.getCredential().setActive(true);
			return userMapper.toUserDto(userRepo.saveAndFlush(userMapper.toUser(userDto)));
//			return userDtoToDisplyDto(),null);
		}
	}




	public TweetUserDto getUser(String username) {
		return userMapper.toUserDto(userRepo.findByUsername(username));
	}




	@Transactional
	public TweetUserDto editUser(TweetUserDto userDto) {
		
		TweetUser userToUpdate = findUsernameInRepo(userDto.getCredential().getUsername());
		
		userToUpdate.setProfile(userDto.getProfile());
		userToUpdate.setCredential(userDto.getCredential());
		userToUpdate.setUsername(userDto.getUsername());
		
		return userMapper.toUserDto(userRepo.saveAndFlush(userToUpdate));
			
		
	}



	public TweetUserDto delete(String username, CredentialDto credentialDto) {
		TweetUser user =  findUsernameInRepo(username);
		
		if(user.getCredential().getUsername().equals(credentialDto.getUsername())){ 
			if(user.getCredential().getPassword().equals(credentialDto.getPassword())){
			user.setActive(false);
			userRepo.saveAndFlush(user);
			}
		}
		
		return userMapper.toUserDto(user);
	}





	




	public void createFollowing(String username,CredentialDto credentialDto) {
		TweetUser userToFollow = findUsernameInRepo(credentialDto.getUsername());
		TweetUser user = findUsernameInRepo(username);
		
		user.getFollowers().add(userToFollow);
		userToFollow.getFollowing().add(user);
		
		userRepo.saveAndFlush(user);
		userRepo.saveAndFlush(userToFollow);
			
	}





	public void deleteFollowing(String username,CredentialDto credentialDto) {
		TweetUser userToUnFollow = findUsernameInRepo(credentialDto.getUsername());
		TweetUser user = findUsernameInRepo(username);
		
		user.getFollowers().remove(userToUnFollow);
		userToUnFollow.getFollowing().remove(user);
		
		userRepo.saveAndFlush(user);
		userRepo.saveAndFlush(userToUnFollow);
		
	}





	public List<TweetDto> getFeed(String username) {
		
		TweetUser user = findUsernameInRepo(username);
		List<Tweet> feed = new ArrayList();
		
		List<Tweet> userTweets = user.getTweet();
		for(Tweet tweet : userTweets){
			feed.add(tweet);
		}
		
		
		Set<TweetUser> followings = user.getFollowing();
		for(TweetUser tempUser : followings){
			List<Tweet> followersTweets = tempUser.getTweet();
			for(Tweet tweet : followersTweets){
				feed.add(tweet);
			}
		}
		
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
		return userMapper.toSetDtos(findUsernameInRepo(username).getFollowers());
		
	}





	public Set<TweetUserDto> getfollowing(String username) {
		return userMapper.toSetDtos(findUsernameInRepo(username).getFollowing());
		
	}





	public List<TweetDto> getTweets(String username) {
		List<Tweet> tweets = findUsernameInRepo(username).getTweet();
		Collections.reverse(tweets);
		return tweetMapper.toTweetDtos(tweets);
		
	}





	public Set<TweetDto> getMentions(String username) {
		return tweetMapper.toSetDto(findUsernameInRepo(username).getMentions());
		
	}
	
	
	public TweetUser findUsernameInRepo(String username){
		return userRepo.findByUsername(username);
	}

	private List<TweetUserDisplayDto> userDtoToDisplyDtos(List<TweetUserDto> userDto){
		List<TweetUserDisplayDto> userDisplayDtoList = null;
		for(TweetUserDto user: userDto){
			for(TweetUserDisplayDto displayUser : userDisplayDtoList ){
				userDisplayDtoList.add(userDtoToDisplyDto(user,displayUser));
			}			
		}
		return userDisplayDtoList;
	}
	
	
	
	private List<TweetUserDto> useroDisplyDtoToUserDtos(List<TweetUserDisplayDto> displyrDto){
		List<TweetUserDto> userDtoList = null;
		for(TweetUserDisplayDto displayUser : displyrDto ){
		for(TweetUserDto user: userDtoList){
			userDtoList.add(useroDisplyDtoToUserDto( displayUser, user));
			}			
		}
		return userDtoList;
	}

	
	
	private TweetUserDto useroDisplyDtoToUserDto(TweetUserDisplayDto displyrDto,TweetUserDto userDto){
			userDto.setUsername(displyrDto.getUsername());
			userDto.setProfile(displyrDto.getProfile());
//			userDto.setJoined(displyrDto.getJoined());		
			return userDto;
	}
	
	
	private TweetUserDisplayDto userDtoToDisplyDto(TweetUserDto userDto,TweetUserDisplayDto displayUser){
		displayUser.setUsername(userDto.getUsername());
		displayUser.setProfile(userDto.getProfile());
//		userDisplayDto.setJoined(userDto.getJoined());
		return displayUser;
	}
	
}
