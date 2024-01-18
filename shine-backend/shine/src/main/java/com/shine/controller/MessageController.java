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

import com.shine.modal.Message;
import com.shine.service.MessageService;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

	@Autowired
    private MessageService messageService;

    @PostMapping()
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        return new ResponseEntity<Message>(
                messageService.createMessage(message),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("message_id") String message_id){
        return new ResponseEntity<Message>(
                messageService.getMessageById(message_id),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping()
    public ResponseEntity<List<Message>> getAllMessages(){
        return new ResponseEntity<List<Message>>(
                messageService.getAllMessages(),
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Message> updateMessageById(
            @PathVariable("message_id") String message_id,
            @RequestBody Message message){
        return new ResponseEntity<Message>(
                messageService.updateMessageById(message_id, message),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(value = "/{message_id}")
    public ResponseEntity<String> deleteMessageById(@PathVariable("message_id") String message_id){
        return new ResponseEntity<String>(
                messageService.deleteMessageById(message_id),
                HttpStatus.ACCEPTED
        );
    }
}
