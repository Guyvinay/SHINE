package com.shine.service;

import java.util.List;

import com.shine.modal.Message;

public interface MessageService {

    public Message createMessage(Message message);

    public Message getMessageById(String message_id);

    public List<Message> getAllMessages();

    public Message updateMessageById(String message_id, Message message);

    public String deleteMessageById(String message_id);
    
}
