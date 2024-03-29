package com.shine.communication;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.shine.exception.ChatNotFoundException;
import com.shine.exception.ProfileNotFoundException;
import com.shine.modal.Chat;
import com.shine.modal.Profile;
import com.shine.repository.ChatRepository;
import com.shine.repository.ProfileRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SocketHandshakeInterceptor implements HandshakeInterceptor {

	private ChatRepository chatRepository;
	
	private ProfileRepository profileRepository;
	
	public static final Map<String, Map<String, WebSocketSession>> active_sessions = WebSocketTextHandler.chat_profile_sessions;
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		String uri = request.getURI().toString();
		String chat_id = extractChatId(uri);
		String profile_id = extractProfileId(uri);
		
		if(!active_sessions.isEmpty()) {
			if( active_sessions.get(chat_id).get(profile_id)!=null ) {
				if(active_sessions.get(chat_id).get(profile_id).isOpen()) {
					log.trace(profile_id +"already connected!!!");
					return false;
				}
			}
		}
		
		
		Profile profile = profileRepository.findById(profile_id).orElseThrow(
        		()->new ProfileNotFoundException("Profile with id: "+ profile_id+", not found!!!"));
		Chat chat = chatRepository.findById(chat_id).orElseThrow(
                ()->new ChatNotFoundException("Chat with id: "+ chat_id+", not found!!!"));
		
		log.trace(profile_id +" connected!!!");
		return chat.getProfiles().stream().anyMatch(a->a.getProfile_id().equals(profile.getProfile_id()));
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}
	
	private String extractChatId(String uri) {

		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String userId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			userId = matcher.group(1);
		}
		return userId;
	}

	private String extractProfileId(String uri) {

		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String problemId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			problemId = matcher.group(2);
		}
		return problemId;
	}
}
