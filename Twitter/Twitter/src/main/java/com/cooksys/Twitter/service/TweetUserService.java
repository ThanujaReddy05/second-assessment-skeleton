/**
 * 
 */
package com.cooksys.Twitter.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.Twitter.dto.DeletedUserDto;
import com.cooksys.Twitter.dto.TweetUserDto;
import com.cooksys.Twitter.entity.TweetUser;
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

//	String timestamp = new SimpleDateFormat("MM.dd.yyyy  HH.mm.ss").format(new Date());



	public TweetUserService(TweetUserRepository userRepo, TweetUserMapper userMapper) {
		this.userRepo = userRepo;
		this.userMapper = userMapper;
	}





	public List<TweetUserDto> getUsers() {
		return userMapper.toDtos(userRepo.findAll());
	}

	
	
	
@Transactional
	public TweetUserDto addUser(TweetUserDto userDto) {
		TweetUser newUser = userMapper.toUser(userDto);
		//Check for the Username in database
		if(credentialRepo.findByUsernameAndPasswordAndActiveFalse(userDto.getCredential().getUsername(), userDto.getCredential().getPassword()) != null)
		{
			System.out.println(userDto.getCredential().getUsername() + "Already exists in DB");
			userMapper.toUser(userDto).setActive(true);
			return userDto;
		}
		//Add as new user to the table
		else
		{
//			String timestamp = new SimpleDateFormat("MM.dd.yyyy  HH.mm.ss").format(new Date());
			 Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			
			newUser.setJoined(timestamp);
			newUser.setActive(true);
			newUser.getCredential().setActive(true);
			return userMapper.toUserDto(userRepo.save(userMapper.toUser(userDto)));
		}
	}




	public TweetUserDto getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}





	public TweetUserDto editUser(TweetUserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}





	public TweetUserDto delete(String userDto) {
		// TODO Auto-generated method stub
		return null;
	}





	




	public void createFollowing(String username) {
		// TODO Auto-generated method stub
		
	}





	public void deleteFollowing(String username) {
		// TODO Auto-generated method stub
		
	}





	public void getFeed(String username) {
		// TODO Auto-generated method stub
		
	}





	public void getfollowers(String username) {
		// TODO Auto-generated method stub
		
	}





	public void getfollowing(String username) {
		// TODO Auto-generated method stub
		
	}





	public void getTweets(String username) {
		// TODO Auto-generated method stub
		
	}





	public void getMentions(String username) {
		// TODO Auto-generated method stub
		
	}

	

}
