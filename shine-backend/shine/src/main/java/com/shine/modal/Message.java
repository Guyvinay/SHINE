package com.shine.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String message_id;

    private String content;

    private LocalDateTime time_stamp;

    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Profile sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Profile receiver;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

}
