package com.cooksys.Twitter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.Twitter.service.ValidateService;

@RestController
@RequestMapping("validate")
public class ValidateController {
	
	private ValidateService validateService;

	public ValidateController(ValidateService validateService) {
		this.validateService = validateService;
		
	}
	
	
	@GetMapping("tag/exists/{label}")
	public boolean tagExists(@PathVariable String label){
		return validateService.tagExists(label);
		
	}
	
	
	@GetMapping("username/exists/{username}")
	public boolean userExists(@PathVariable String username){
		return validateService.userExists(username);
		
	}
	
	@GetMapping("validate/username/available/{username}")
	public boolean usernameAvailable(@PathVariable String username){
		return validateService.isUsernameAvailable(username);
		
	}
	
}
