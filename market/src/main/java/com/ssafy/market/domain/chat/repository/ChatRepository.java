package com.ssafy.market.domain.chat.repository;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<ChatMessage, String> {

}
