package com.shine.serviceImpl;

import com.shine.modal.Message;
import org.springframework.stereotype.Service;

import com.shine.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public Message createMessage(Message message) {
        return null;
    }

    @Override
    public Message getMessageById(String message_id) {
        return null;
    }

    @Override
    public List<Message> getAllMessages() {
        return null;
    }

    @Override
    public Message updateMessageById(String message_id, Message message) {
        return null;
    }

    @Override
    public String deleteMessageById(String message_id) {
        return null;
    }
}
