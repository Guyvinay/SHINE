package com.shine.exception;

public class ProfileNotFoundException extends RuntimeException {

	public ProfileNotFoundException(String msg) {
		super(msg);
	}
	
	public ProfileNotFoundException() {
		super("Profile Not Found!!!");
	}
}
