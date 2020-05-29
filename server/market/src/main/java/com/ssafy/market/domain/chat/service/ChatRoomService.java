package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.dto.ChatRoomDto;
import com.ssafy.market.domain.chat.redis.RedisSubscriber;
import com.ssafy.market.domain.chat.repository.ChatMongoRepository;
import com.ssafy.market.domain.chat.repository.ChatRoomMongoRepository;
import com.ssafy.market.domain.chat.util.TopicUtil;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

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

    public boolean enterChatRoom(Long roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(String.valueOf(roomId));
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(String.valueOf(roomId), topic);
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

    public List<ChatRoomDto> findRoomsByUserId(String userId) {
        try {
            List<ChatRoom> searchedChatRoom = chatRoomMongoRepository.getChatRoomsByUserId(userId);
            List<ChatRoomDto> result = new ArrayList<>();
            for (ChatRoom chatRoom : searchedChatRoom) {
//                System.out.println(chatRoom);
                User buyer = userRepository.findByUserId(Long.parseLong(chatRoom.getBuyerId()));
//                System.out.println("buyer:" + buyer);
                User seller = userRepository.findByUserId(Long.parseLong(chatRoom.getSellerId()));
//                System.out.println("seller:" + seller);
                Map<String, String> sellerUser = new HashMap<>();
                sellerUser.put("userId", String.valueOf(seller.getUserId()));
                sellerUser.put("userName", seller.getName());
                Map<String, String> buyerUser = new HashMap<>();
                buyerUser.put("userId", String.valueOf(buyer.getUserId()));
                buyerUser.put("userName", buyer.getName());
                String createdDateTime = chatRoom.getCreatedDateTime();
                String modifiedDateTime = chatRoom.getModifiedDateTime();
                Boolean isSellerExit = chatRoom.isSellerExit();
                Boolean isBuyerExit = chatRoom.isBuyerExit();
                String lastMessage = chatMongoRepository.getLastChatMessageByRoomId(chatRoom.getRoomId());
                System.out.println("last message: "+ lastMessage);
                ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom.getRoomId(), sellerUser, buyerUser, createdDateTime, modifiedDateTime, isSellerExit, isBuyerExit, lastMessage);
                result.add(chatRoomDto);
            }
//        Map<String, List<ChatRoom>> result = new HashMap<>();
//        List<ChatRoom> roomsAsSeller = chatRoomMongoRepository.getChatRoomsByUserIdAsSeller(userId);
//        List<ChatRoom> roomsAsBuyer = chatRoomMongoRepository.getChatRoomsByUserIdAsBuyer(userId);
//        result.put("roomsAsSeller", roomsAsSeller);
//        result.put("roomsAsBuyer", roomsAsBuyer);
            return result;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
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
