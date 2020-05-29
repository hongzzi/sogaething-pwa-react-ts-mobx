package com.ssafy.market.domain.chat.repository;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatMongoRepository {

    private final MongoTemplate mongoTemplate;

    public ChatMessage insertChatMessage(ChatMessage chatMessage) {
        chatMessage.setCreatedDateTime(LocalDateTime.now().toString());
        return mongoTemplate.insert(chatMessage, "chatMessage");
    }

    public List<ChatMessage> getChatMessagesByRoomId(String roomId) {
        Query query = new Query(new Criteria("roomId").is(roomId));
        return mongoTemplate.find(query, ChatMessage.class, "chatMessage");
    }

    public String getLastChatMessageByRoomId(Long roomId) {
        Query query = new Query(new Criteria("roomId").is(roomId));
        query.with(Sort.by(Sort.Direction.DESC, "_id")).limit(1);
        ChatMessage result = mongoTemplate.findOne(query, ChatMessage.class, "chatMessage");
        if (result != null) return result.getMessage();
        else return null;
    }
}