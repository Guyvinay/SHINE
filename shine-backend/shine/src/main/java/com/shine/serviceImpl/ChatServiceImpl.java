package com.shine.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import com.shine.exception.ChatNotFoundException;
import com.shine.exception.ProfileNotFoundException;
import com.shine.modal.Profile;
import com.shine.repository.ChatRepository;
import com.shine.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shine.modal.Chat;
import com.shine.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Chat createChat(String profile_id, Chat chat) {
        Profile profile = profileRepository.findById(profile_id).orElseThrow(
                ()->new ProfileNotFoundException("Profile with id: "+ profile_id+", not found!!!"));

        List<String> users_to_be_added = chat.getUsers_to_be_added();
        
        for(String user_id: users_to_be_added) {
        	Profile user = profileRepository.findById(user_id)
        			.orElseThrow(()->new ProfileNotFoundException("Profile with id: "+ profile_id+", not found!!!"));
        	chat.getProfiles().add(profile);
        	user.getChats().add(chat);
        }
        
        profile.getChats_created().add(chat);
        chat.setChat_created_by(profile);
        chat.setChat_created_at(LocalDateTime.now());
        chat.getProfiles().add(profile);
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(String chat_id) {

        return chatRepository.findById(chat_id).orElseThrow(
                ()->new ChatNotFoundException("Chat with id: "+ chat_id+", not found!!!"));

    }

    @Override
    public List<Chat> getAllChats() {

        List<Chat> chats = chatRepository.findAll();
        if(chats.isEmpty())
            throw new ChatNotFoundException("No Chat found!!!");
        return chats;
    }

    @Override
    public Chat updateChatById(String chat_id, Chat chat) {
        return null;
    }

    @Override
    public String deleteChatById(String chat_id) {
        Chat chat = chatRepository.findById(chat_id).orElseThrow(
                ()->new ChatNotFoundException("Chat with id: "+ chat_id+", not found!!!"));
        chatRepository.delete(chat);
        return "Chat with id: "+ chat+", deleted";
    }
}
