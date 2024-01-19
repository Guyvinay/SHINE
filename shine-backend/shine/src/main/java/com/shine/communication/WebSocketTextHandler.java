package com.shine.communication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shine.exception.ChatNotFoundException;
import com.shine.exception.ProfileNotFoundException;
import com.shine.modal.Chat;
import com.shine.modal.Message;
import com.shine.modal.Profile;
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
		
		String profile_id = extractProfileId(session);
		String chat_id = extractChatId(session);
		Message chat_message = new Message();
		
		Chat chat = chatRepository.findById(chat_id).orElseThrow(()->
		new ChatNotFoundException("Chat with id: "+ chat_id+", not found!!!"));
		
		Profile profile = profileRepository.findById(profile_id).orElseThrow(
        		()->new ProfileNotFoundException("Profile with id: "+ profile_id+", not found!!!"));
		
		
		
		String payload_message = message.getPayload();

		ObjectMapper object_mapper = new ObjectMapper();
		
		JsonNode json_node = object_mapper.readTree(payload_message);
		
		ObjectNode object_node = (ObjectNode) json_node;
		
		object_node.put("send_by", profile.getName());
		
		
		String final_message = object_mapper.writeValueAsString(object_node);
		
//		System.out.println(final_message);
		
		broadcastMessageToReciever(chat_id, profile_id ,final_message, session);
	}
	
	private void broadcastMessageToReciever(String chat_id, String profile_id, String final_message,
			WebSocketSession session) {

		Map<String, WebSocketSession> profile_sessions = chat_profile_sessions.get(chat_id);
//		System.out.println(profile_sessions);
		
		if(profile_sessions!=null) {
			for(Map.Entry<String, WebSocketSession> profile_session: profile_sessions.entrySet() ) {
				if( 
						profile_session.getValue().isOpen() && 
						!session.getId().equals(profile_session.getValue().getId()) ) {
					
					try {
						profile_session.getValue().sendMessage(new TextMessage(final_message));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
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
