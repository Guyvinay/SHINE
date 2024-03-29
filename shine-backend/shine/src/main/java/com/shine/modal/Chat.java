package com.shine.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String chat_id;

	private String chat_name;
	
	private LocalDateTime chat_created_at;

	private LocalDateTime last_message_at;
	
	@ManyToOne
	@JoinColumn(name = "chat_created_by")
	@JsonIgnore
	@ToString.Exclude
	private Profile chat_created_by;

	@OneToMany(mappedBy = "chat")
	@JsonIgnore
	@ToString.Exclude
	private List<Message> messages = new ArrayList<>();

	@ManyToMany(mappedBy = "chats", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	@ToString.Exclude
	private List<Profile> profiles = new ArrayList<>();

	@Transient
	private List<String> users_to_be_added = new ArrayList<>();
	

}
