package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.redis.RedisPublisher;
import com.ssafy.market.domain.chat.repository.ChatMongoRepository;
import com.ssafy.market.domain.chat.repository.ChatRoomMongoRepository;
import com.ssafy.market.domain.chat.util.CacheKey;
import com.ssafy.market.domain.chat.util.TopicUtil;
import com.ssafy.market.domain.user.repository.UserRepository;
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

    // 게시 처리 서비스
    private final RedisPublisher redisPublisher;
    private Map<String, ChannelTopic> topics = TopicUtil.getTopicUtil();

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

    @CacheEvict(value = CacheKey.MESSAGE, key = "#chatMessage.roomId")
    @Transactional
    public Boolean sendMessage(ChatMessage chatMessage) {
        ChannelTopic channelTopic = getTopic(String.valueOf(chatMessage.getRoomId()));
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
        try {
            ChatRoom searchedChatRoom = chatRoomMongoRepository.getChaRoomByRoomId(roomId);
            List<ChatMessage> searchedMessages = chatMongoRepository.getChatMessagesByRoomId(roomId);
            Collections.reverse(searchedMessages);
            System.out.println(searchedChatRoom);
            Map<String, String> chatRoom = new HashMap<>();
            chatRoom.put("buyer", userRepository.findByUserId(Long.parseLong(searchedChatRoom.getBuyerId())).getName());
            chatRoom.put("seller", userRepository.findByUserId(Long.parseLong(searchedChatRoom.getSellerId())).getName());

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
