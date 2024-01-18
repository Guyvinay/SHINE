package com.shine.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
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
    private List<Message> sent_messages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> received_messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private List<Chat> chats = new ArrayList<>();

}
