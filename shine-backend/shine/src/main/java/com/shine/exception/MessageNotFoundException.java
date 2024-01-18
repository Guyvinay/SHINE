package com.shine.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(String msg){
        super(msg);
    }
}
