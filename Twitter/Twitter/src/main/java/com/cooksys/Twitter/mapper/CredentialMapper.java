package com.cooksys.Twitter.mapper;

import org.mapstruct.Mapper;

import com.cooksys.Twitter.dto.CredentialDto;
import com.cooksys.Twitter.entity.Credential;

@Mapper(componentModel="spring")
public interface CredentialMapper {


	public CredentialDto toCredentialDto(Credential credentials);
	
	public Credential toCredential(CredentialDto credentialDto);
	
}
