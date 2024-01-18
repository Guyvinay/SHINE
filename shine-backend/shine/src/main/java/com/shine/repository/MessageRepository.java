package com.shine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.modal.Message;

public interface MessageRepository  extends JpaRepository<Message, String> {

}
