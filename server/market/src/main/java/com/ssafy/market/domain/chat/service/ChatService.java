package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.domain.MessageType;
import com.ssafy.market.domain.chat.redis.RedisPublisher;
import com.ssafy.market.domain.chat.repository.ChatMongoRepository;
import com.ssafy.market.domain.chat.repository.ChatRoomMongoRepository;
import com.ssafy.market.domain.chat.util.CacheKey;
import com.ssafy.market.domain.chat.util.TopicUtil;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.global.apis.ImgurApi;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final ChatMongoRepository chatMongoRepository;
    private final ChatRoomMongoRepository chatRoomMongoRepository;
    private final UserRepository userRepository;
    private final ImgurApi imgurApi;

    // 게시 처리 서비스
    private final RedisPublisher redisPublisher;
    private Map<Long, ChannelTopic> topics = TopicUtil.getTopicUtil();

    public ChannelTopic getTopic(Long roomId) {
        return topics.get(roomId);
    }

    @CacheEvict(value = CacheKey.MESSAGE, key = "#chatMessage.roomId")
    @Transactional
    public Boolean sendMessage(ChatMessage chatMessage) {
        ChannelTopic channelTopic = getTopic(chatMessage.getRoomId());
        if (MessageType.IMAGE.equals(chatMessage.getType())) {
            String imagePath = imgurApi.uploadImg(chatMessage.getMessage());
            chatMessage.setMessage(imagePath);
        }
        // WebSocket 에 발행된 메시지를 redis 로 발행한다.(publish)
        redisPublisher.publish(channelTopic, chatMessage);
        ChatMessage result = chatMongoRepository.insertChatMessage(chatMessage);
        if (result != null){
            return true;
        } else {
            return false;
        }
    }

    @Cacheable(value = CacheKey.MESSAGE, key = "#roomId", unless = "#result == null")
    @Transactional(readOnly = true)
    public Map<String, Object> findChatMessagesByRoomId(Long roomId) {
//        System.out.println("findChatMessagesByRoomId");
        try {
            ChatRoom searchedChatRoom = chatRoomMongoRepository.getChaRoomByRoomId(roomId);
            List<ChatMessage> searchedMessages = chatMongoRepository.getChatMessagesByRoomId(roomId);
            Collections.reverse(searchedMessages);

            Map<String, Map<String, String>> chatRoom = new HashMap<>();

            Map<String, String> buyerMap = new HashMap<>();
            User buyer = userRepository.findByUserId(Long.parseLong(searchedChatRoom.getBuyerId()));
            buyerMap.put("name", buyer.getName());
            buyerMap.put("imageUrl", buyer.getImageUrl());
            Map<String, String> sellerMap = new HashMap<>();
            User seller = userRepository.findByUserId(Long.parseLong(searchedChatRoom.getSellerId()));
            sellerMap.put("name", seller.getName());
            sellerMap.put("imageUrl", seller.getImageUrl());

            chatRoom.put("buyer", buyerMap);
            chatRoom.put("seller", sellerMap);

            Map<String, Object> result = new HashMap<>();
            result.put("chatRoom", chatRoom);
            result.put("chatMessages", searchedMessages);

            return result;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
