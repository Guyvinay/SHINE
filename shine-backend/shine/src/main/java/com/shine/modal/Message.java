package com.shine.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
