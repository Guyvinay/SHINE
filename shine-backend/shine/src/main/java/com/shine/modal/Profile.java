package com.shine.modal;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String profile_id;

    private String name;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    @ToString.Exclude
    private List<Message> sent_messages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    @ToString.Exclude
    private List<Message> received_messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    @JsonIgnore
    @ToString.Exclude
    private List<Chat> chats = new ArrayList<>();
    
    @OneToMany(mappedBy = "chat_created_by")
    @JsonIgnore
    @ToString.Exclude
    private List<Chat> chats_created = new ArrayList<>();

	public Profile(String name, String username, String email, String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

    
    
}
