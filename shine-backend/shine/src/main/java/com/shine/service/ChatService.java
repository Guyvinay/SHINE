package com.shine.service;

import java.util.List;

import com.shine.modal.Chat;

public interface ChatService {

    public Chat createChat(String profile_id, Chat chat);

    public Chat getChatById(String chat_id);

    public List<Chat> getAllChats();

    public Chat updateChatById(String chat_id, Chat chat);

    public String deleteChatById(String chat_id);

}
