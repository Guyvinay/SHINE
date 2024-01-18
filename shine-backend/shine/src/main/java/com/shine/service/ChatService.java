package com.shine.service;

import com.shine.modal.Chat;
import com.shine.modal.Chat;
import com.shine.repository.ChatRepository;

import java.util.List;

public interface ChatService {

    public Chat createChat(Chat chat);

    public Chat getChatById(String chat_id);

    public List<Chat> getAllChats();

    public Chat updateChatById(String chat_id, Chat chat);

    public String deleteChatById(String chat_id);

}
