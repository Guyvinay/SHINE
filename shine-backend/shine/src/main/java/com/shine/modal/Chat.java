package com.shine.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String chat_id;

	private LocalDateTime chat_created_at;

	private LocalDateTime last_message_at;

	@OneToMany(mappedBy = "chat")
	private List<Message> messages = new ArrayList<>();

	@ManyToMany(mappedBy = "chats")
	private List<Profile> profiles = new ArrayList<>();


}
