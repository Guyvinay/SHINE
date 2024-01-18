package com.shine.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.shine.repository.ChatRepository;
import com.shine.repository.ProfileRepository;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(
				new WebSocketTextHandler(
						chatRepository, profileRepository
						), "/chat/{chat_id}/{user_id}")
		        .addInterceptors(new SocketHandshakeInterceptor(
		        		chatRepository, profileRepository
		        		))
		        .setAllowedOrigins("*");
		        
	}

	
	
}
