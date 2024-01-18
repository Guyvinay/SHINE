package com.shine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.modal.Chat;

public interface ChatRepository extends JpaRepository<Chat, String> {

}
