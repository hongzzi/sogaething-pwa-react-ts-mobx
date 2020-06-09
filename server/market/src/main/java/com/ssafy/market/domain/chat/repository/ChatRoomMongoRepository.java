package com.ssafy.market.domain.chat.repository;

import com.mongodb.client.result.DeleteResult;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.domain.CollectionCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatRoomMongoRepository {
    private final MongoTemplate mongoTemplate;

    public List<ChatRoom> getChatRooms(){
        return mongoTemplate.findAll(ChatRoom.class, "chatRoom");
    }

    public ChatRoom getChaRoomByRoomId(Long roomId){
        return mongoTemplate.findById(roomId, ChatRoom.class, "chatRoom");
    }

    public List<ChatRoom> getChatRoomsByUserIdAsSeller(String userId) {
        Query query = new Query(Criteria.where("sellerId").is(userId).andOperator(Criteria.where("isSellerExit").is(false)));
        return mongoTemplate.find(query, ChatRoom.class, "chatRoom");
    }

    public List<ChatRoom> getChatRoomsByUserIdAsBuyer(String userId) {
        Query query = new Query(Criteria.where("buyerId").is(userId).andOperator(Criteria.where("isBuyerExit").is(false)));
        return mongoTemplate.find(query, ChatRoom.class, "chatRoom");
    }

    public List<ChatRoom> getChatRoomsByUserId(String userId) {
        Query query = new Query(Criteria.where("buyerId").is(userId).andOperator(Criteria.where("isBuyerExit").is(false)));
        List<ChatRoom> result = mongoTemplate.find(query, ChatRoom.class, "chatRoom");
        query = new Query(Criteria.where("sellerId").is(userId).andOperator(Criteria.where("isSellerExit").is(false)));
        result.addAll(mongoTemplate.find(query, ChatRoom.class, "chatRoom"));
        Collections.sort(result);
        return result;
    }

    public Long getNextSequence(String name) {
        Query query = new Query(new Criteria("name").is(name));
        Update update = new Update();
        update.inc("seq", 1);
        CollectionCounter collectionCounter = mongoTemplate.findAndModify(query, update, CollectionCounter.class, "collectionCounter");
        Long result;
        if (collectionCounter == null) {
            mongoTemplate.insert(new CollectionCounter(name, (long) 1), "collectionCounter");
            result = (long) 1;
        } else {
            result = ((CollectionCounter)collectionCounter).getSeq();
        }
        return result;
    }
    public ChatRoom insertChatRoom(ChatRoom chatRoom) {
        try {
            Query query = new Query(Criteria.where("postId").is(chatRoom.getPostId()).andOperator(Criteria.where("sellerId").is(chatRoom.getSellerId()).andOperator(Criteria.where("buyerId").is(chatRoom.getBuyerId()))));

            ChatRoom existedRoom = mongoTemplate.findOne(query, ChatRoom.class, "chatRoom");
            if (existedRoom != null) return existedRoom;

            chatRoom.setRoomId(getNextSequence("roomId"));
            chatRoom.setBuyerExit(false);
            chatRoom.setSellerExit(false);
            chatRoom.setCreatedDateTime(LocalDateTime.now().toString());
            chatRoom.setModifiedDateTime(LocalDateTime.now().toString());

            return mongoTemplate.insert(chatRoom, "chatRoom");
        } catch (DuplicateKeyException e){
            throw e;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Long updateChatRoom(ChatRoom chatRoom) throws RuntimeException{
        try {
            // 조건절을 위한 쿼리
            Query query = new Query(new Criteria("roomId").is(chatRoom.getRoomId()));
            // 업데이트 내용을 저장할 객체 생성
            Update update = new Update();
            update.set("isBuyerExit", chatRoom.isBuyerExit());
            update.set("isSellerExit", chatRoom.isSellerExit());
//            update.set("modifiedDateTime", LocalDateTime.now().toString());

            // 쿼리 조건, update 변경 내용, 컬렉션 네임
            Object updateResult = mongoTemplate.findAndModify(query, update, ChatRoom.class, "chatRoom");
            Long updatedCount = updateResult == null ? (long) 0 : (long) 1;
            return updatedCount;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Long deleteChatRoom(ChatRoom chatRoom) throws RuntimeException{
        try {
            // 조건절
            Query query = new Query(new Criteria("roomId").is(chatRoom.getRoomId()));
            DeleteResult deleteResult = mongoTemplate.remove(query, ChatRoom.class, "chatRoom");
            long deletedCount = deleteResult.getDeletedCount();
            return deletedCount;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
