package com.ssafy.market.domain.chat.repository;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatMongoRepository {

    private final MongoTemplate mongoTemplate;

    public ChatMessage insertChatMessage(ChatMessage chatMessage) {
        return mongoTemplate.insert(chatMessage, "chatMessage");
    }

    public List<ChatMessage> getChatMessagesByRoomId(String roomId) {
        Query query = new Query(new Criteria("roomId").is(roomId));
        return mongoTemplate.find(query, ChatMessage.class, "chatMessage");
    }
}