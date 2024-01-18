package com.shine.repository;

import com.shine.modal.Chat;
import com.shine.modal.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<Message, String> {

}
