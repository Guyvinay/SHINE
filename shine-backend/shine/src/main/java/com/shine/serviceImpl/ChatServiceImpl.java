package com.shine.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shine.modal.Chat;
import com.shine.service.ChatService;

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
