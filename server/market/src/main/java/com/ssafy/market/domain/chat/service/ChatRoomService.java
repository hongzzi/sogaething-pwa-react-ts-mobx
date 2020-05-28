package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.redis.RedisSubscriber;
import com.ssafy.market.domain.chat.repository.ChatRoomMongoRepository;
import com.ssafy.market.domain.chat.util.TopicUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomService.class);

    private final ChatRoomMongoRepository chatRoomMongoRepository;

    // 채팅방(topic)에 발행되는 메시지를 처리할 Listener
    private final RedisMessageListenerContainer redisMessageListener;
    private Map<String, ChannelTopic> topics = TopicUtil.getTopicUtil();

    // 구독 처리 서비스
    private final RedisSubscriber redisSubscriber;

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChatRoom result;
        try {
            result = chatRoomMongoRepository.insertChatRoom(chatRoom);
            return result;
        } catch (DuplicateKeyException e) {
            logger.error("createChatRoom {}", e.getMessage());
            return null;
        }
    }

    public boolean enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);
        }
        return true;
    }

    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRooms = chatRoomMongoRepository.getChatRooms();
        return chatRooms;
    }

    public ChatRoom findRoomByRoomId(String roomId) {
        ChatRoom chatRoom = chatRoomMongoRepository.getChaRoomByRoomId(roomId);
        return chatRoom;
    }

    public Map<String, List<ChatRoom>> findRoomsPerPositionByUserId(String userId) {
        Map<String, List<ChatRoom>> result = new HashMap<>();
        List<ChatRoom> roomsAsSeller = chatRoomMongoRepository.getChatRoomsByUserIdAsSeller(userId);
        List<ChatRoom> roomsAsBuyer = chatRoomMongoRepository.getChatRoomsByUserIdAsBuyer(userId);
        result.put("roomsAsSeller", roomsAsSeller);
        result.put("roomsAsBuyer", roomsAsBuyer);
        return result;
    }

    public Long updateChatRoom(ChatRoom chatRoom) {
        try {
            Long updatedCount = chatRoomMongoRepository.updateChatRoom(chatRoom);
            return updatedCount;
        } catch (RuntimeException e) {
            logger.error("updateChatRoom {}", e.getMessage());
            return (long) -1;
        }
    }

    public Long deleteChatRoom(ChatRoom chatRoom) {
        try {
            Long deletedCount = chatRoomMongoRepository.deleteChatRoom(chatRoom);
            return deletedCount;
        } catch (RuntimeException e) {
            logger.error("deleteChatRoom {}", e.getMessage());
            return (long) -1;
        }
    }
}
