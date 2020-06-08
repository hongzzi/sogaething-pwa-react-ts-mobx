package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.dto.ChatRoomDto;
import com.ssafy.market.domain.chat.redis.RedisSubscriber;
import com.ssafy.market.domain.chat.repository.ChatMongoRepository;
import com.ssafy.market.domain.chat.repository.ChatRoomMongoRepository;
import com.ssafy.market.domain.chat.util.CacheKey;
import com.ssafy.market.domain.chat.util.TopicUtil;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomService.class);

    private final ChatMongoRepository chatMongoRepository;
    private final ChatRoomMongoRepository chatRoomMongoRepository;
    private final UserRepository userRepository;

    // 채팅방(topic)에 발행되는 메시지를 처리할 Listener
    private final RedisMessageListenerContainer redisMessageListener;
    private Map<Long, ChannelTopic> topics = TopicUtil.getTopicUtil();

    // 구독 처리 서비스
    private final RedisSubscriber redisSubscriber;

    @CachePut(value = CacheKey.ROOM, key = "#chatRoom.postId+#chatRoom.buyerId+#chatRoom.sellerId")
    @Transactional
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChatRoom result;
        result = chatRoomMongoRepository.insertChatRoom(chatRoom);
        return result;
    }

    public boolean enterChatRoom(Long roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(String.valueOf(roomId));
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);
        }
        return true;
    }

    public boolean leaveChatRoom(Long roomId) {
        if (topics.containsKey(roomId)) {
            topics.remove(roomId);
            return true;
        }
        return false;
    }
    @Transactional(readOnly = true)
    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRooms = chatRoomMongoRepository.getChatRooms();
        return chatRooms;
    }

    @Cacheable(value = CacheKey.ROOM, key = "#roomId", unless = "#result == null")
    @Transactional(readOnly = true)
    public ChatRoom findRoomByRoomId(Long roomId) {
        ChatRoom chatRoom = chatRoomMongoRepository.getChaRoomByRoomId(roomId);
        return chatRoom;
    }

//    @Cacheable(value = CacheKey.ROOMS, key = "#userId", unless = "#result.size() < 10")
    @Transactional(readOnly = true)
    public List<ChatRoomDto> findRoomsByUserId(String userId) {
        try {
            List<ChatRoom> searchedChatRoom = chatRoomMongoRepository.getChatRoomsByUserId(userId);
            List<ChatRoomDto> result = new ArrayList<>();
            for (ChatRoom chatRoom : searchedChatRoom) {
                User buyer = userRepository.findByUserId(Long.parseLong(chatRoom.getBuyerId()));
                User seller = userRepository.findByUserId(Long.parseLong(chatRoom.getSellerId()));
                Map<String, String> sellerUser = new HashMap<>();
                sellerUser.put("userId", String.valueOf(seller.getUserId()));
                sellerUser.put("userName", seller.getName());
                sellerUser.put("imageUrl", seller.getImageUrl());
                Map<String, String> buyerUser = new HashMap<>();
                buyerUser.put("userId", String.valueOf(buyer.getUserId()));
                buyerUser.put("userName", buyer.getName());
                buyerUser.put("imageUrl", buyer.getImageUrl());
                String createdDateTime = chatRoom.getCreatedDateTime();
                String modifiedDateTime = chatRoom.getModifiedDateTime();
                Boolean isSellerExit = chatRoom.isSellerExit();
                Boolean isBuyerExit = chatRoom.isBuyerExit();
                String lastMessage = chatMongoRepository.getLastChatMessageByRoomId(chatRoom.getRoomId());
                ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom.getRoomId(), sellerUser, buyerUser, createdDateTime, modifiedDateTime, isSellerExit, isBuyerExit, lastMessage);
                result.add(chatRoomDto);
            }
            return result;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @CachePut(value = CacheKey.ROOM, key = "#chatRoom.postId+#chatRoom.buyerId+#chatRoom.sellerId")
    @Transactional
    public Long updateChatRoom(ChatRoom chatRoom) {
        try {
            Long updatedCount = chatRoomMongoRepository.updateChatRoom(chatRoom);
            return updatedCount;
        } catch (RuntimeException e) {
            logger.error("updateChatRoom {}", e.getMessage());
            return (long) -1;
        }
    }

    @CacheEvict(value = CacheKey.ROOM, key = "#chatRoom.postId+#chatRoom.buyerId+#chatRoom.sellerId")
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
