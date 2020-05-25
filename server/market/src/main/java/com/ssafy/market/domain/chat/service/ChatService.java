package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.redis.RedisPublisher;
import com.ssafy.market.domain.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final ChatRepository chatRoomRepository;
    private final RedisPublisher redisPublisher;

    @PostConstruct
    private void init(){

//        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAllRoom();
        return chatRooms;
    }

    public ChatRoom findRoomById(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findRoomById(roomId);
        return chatRoom;
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = chatRoomRepository.createChatRoom(name);
        return chatRoom;
    }

    public boolean enterChatRoom(String roomId) {
        chatRoomRepository.enterChatRoom(roomId);
        return true;
    }

    public ChannelTopic getTopic(String roomId) {
        ChannelTopic channelTopic = chatRoomRepository.getTopic(roomId);
        return channelTopic;
    }

    public void sendMessage(ChatMessage message) {
        ChannelTopic channelTopic = chatRoomRepository.getTopic(message.getRoomId());
        redisPublisher.publish(channelTopic, message);
    }
}
