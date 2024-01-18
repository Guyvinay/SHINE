package com.shine.communication;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.shine.repository.ChatRepository;
import com.shine.repository.ProfileRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class WebSocketTextHandler extends TextWebSocketHandler {

	private ChatRepository chatRepository;
	
	private ProfileRepository profileRepository;
	
	public static final Map<String, Map<String, WebSocketSession>> chat_profile_sessions = new HashMap<>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String profile_id = extractProfileId(session);
		String chat_id = extractChatId(session);
		
		chat_profile_sessions.computeIfAbsent(chat_id, k-> new HashMap<>());
		chat_profile_sessions.get(chat_id).put(profile_id, session);

	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		
		
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String profile_id = extractProfileId(session);
		String chat_id = extractChatId(session);
		chat_profile_sessions.get(chat_id).remove(profile_id);
	}
	
	private String extractChatId(WebSocketSession session) {
		String uri = session.getUri().toString();
		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String userId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			userId = matcher.group(1);
		}
		return userId;
	}

	private String extractProfileId(WebSocketSession session) {
		String uri = session.getUri().toString();
		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String problemId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			problemId = matcher.group(2);
		}
		return problemId;
	}
	
}
