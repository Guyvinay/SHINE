package com.shine.serviceImpl;

import com.shine.modal.Chat;
import org.springframework.stereotype.Service;

import com.shine.service.ChatService;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Override
    public Chat createChat(Chat chat) {
        return null;
    }

    @Override
    public Chat getChatById(String chat_id) {
        return null;
    }

    @Override
    public List<Chat> getAllChats() {
        return null;
    }

    @Override
    public Chat updateChatById(String chat_id, Chat chat) {
        return null;
    }

    @Override
    public String deleteChatById(String chat_id) {
        return null;
    }
}
