package com.shine.exception;

public class ChatNotFoundException extends RuntimeException {

	public ChatNotFoundException() {
		super("Chat Not FOund!!!");
	}

	public ChatNotFoundException(String message) {
		super(message);
	}
}
