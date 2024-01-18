package com.shine.serviceImpl;

import java.util.List;

import com.shine.exception.ProfileNotFoundException;
import com.shine.modal.Profile;
import com.shine.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shine.modal.Message;
import com.shine.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createMessage(Message message) {

        return messageRepository.save(message);
    }

    @Override
    public Message getMessageById(String message_id) {
        return messageRepository.findById(message_id).orElseThrow(
                ()->new ProfileNotFoundException("Message with id: "+ message_id+", not found!!!"));
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        if(messages.isEmpty())
            throw new ProfileNotFoundException("No Messages found!!!");
        return messages;
    }

    @Override
    public Message updateMessageById(String message_id, Message message) {
        return null;
    }

    @Override
    public String deleteMessageById(String message_id) {
        Message message = messageRepository.findById(message_id).orElseThrow(
                ()->new ProfileNotFoundException("Message with id: "+ message_id+", not found!!!"));
        messageRepository.delete(message);
        return "Profile with id: "+ message_id+", deleted";
    }
}
