package com.dcs.exception;

import org.springframework.http.HttpStatus;

public class DeveloperCommunitySystemException extends Exception{

	private static final long serialVersionUID = 1L;

	public DeveloperCommunitySystemException(String str) {
		super(str);
	}

	public DeveloperCommunitySystemException(HttpStatus internalServerError) {
		super();
	}

	
}
