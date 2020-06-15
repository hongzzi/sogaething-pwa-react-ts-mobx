package com.ssafy.market.domain.chat.repository;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.domain.MessageType;
import com.ssafy.market.domain.chat.dto.RemitMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatMongoRepository {

    private final MongoTemplate mongoTemplate;

    public ChatMessage insertChatMessage(ChatMessage chatMessage) {
        chatMessage.setCreatedDateTime(LocalDateTime.now().toString());

        Query query = new Query(new Criteria("roomId").is(chatMessage.getRoomId()));
        Update update = new Update();
        update.set("modifiedDateTime", LocalDateTime.now().toString());
        mongoTemplate.findAndModify(query, update, ChatRoom.class, "chatRoom");
        return mongoTemplate.insert(chatMessage, "chatMessage");
    }

    public RemitMessageDto insertRemitMessage(RemitMessageDto remitMessageDto) {
        remitMessageDto.setCreatedDateTime(LocalDateTime.now().toString());

        Query query = new Query(new Criteria("roomId").is(remitMessageDto.getRoomId()));
        Update update = new Update();
        update.set("modifiedDateTime", LocalDateTime.now().toString());
        mongoTemplate.findAndModify(query, update, ChatRoom.class, "chatRoom");
        return mongoTemplate.insert(remitMessageDto, "chatMessage");
    }

    public List<ChatMessage> getChatMessagesByRoomId(Long roomId) {
        Query query = new Query(new Criteria("roomId").is(roomId));
        return mongoTemplate.find(query, ChatMessage.class, "chatMessage");
    }

    public String getLastChatMessageByRoomId(Long roomId) {
        Query query = new Query(new Criteria("roomId").is(roomId));
        query.with(Sort.by(Sort.Direction.DESC, "_id")).limit(1);
        ChatMessage result = mongoTemplate.findOne(query, ChatMessage.class, "chatMessage");
        if (result != null) {
            if (MessageType.REMIT.equals(result.getType())){
                result.setMessage("입금 요청");
            }
            return result.getMessage();
        }
        else return null;
    }
}