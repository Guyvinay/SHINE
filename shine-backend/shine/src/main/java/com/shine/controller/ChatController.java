package com.shine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shine.modal.Chat;
import com.shine.service.ChatService;

@RestController
@RequestMapping(value = "/chats")
public class ChatController {

	@Autowired
    private ChatService chatService;

    @PostMapping(value = "/{profile_id}")
    public ResponseEntity<Chat> createChat(
            @PathVariable("profile_id")String profile_id,
            @RequestBody Chat chat){
        return new ResponseEntity<Chat>(
                chatService.createChat(profile_id, chat),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/{chat_id}")
    public ResponseEntity<Chat> getChatById(@PathVariable("chat_id") String chat_id){
        return new ResponseEntity<Chat>(
                chatService.getChatById(chat_id),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping()
    public ResponseEntity<List<Chat>> getAllChats(){
        return new ResponseEntity<List<Chat>>(
                chatService.getAllChats(),
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Chat> updateChatById(
            @PathVariable("chat_id") String chat_id,
            @RequestBody Chat chat){
        return new ResponseEntity<Chat>(
                chatService.updateChatById(chat_id, chat),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(value = "/{chat_id}")
    public ResponseEntity<String> deleteChatById(@PathVariable("chat_id") String chat_id){
        return new ResponseEntity<String>(
                chatService.deleteChatById(chat_id),
                HttpStatus.ACCEPTED
        );
    }
	
}
