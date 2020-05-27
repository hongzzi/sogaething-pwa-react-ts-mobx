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

import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final ChatRepository chatRepository;
//    private final RedisPublisher redisPublisher;

    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRooms = chatRepository.findAllRoom();
        return chatRooms;
    }

    public ChatRoom findRoomById(String roomId) {
        ChatRoom chatRoom = chatRepository.findRoomById(roomId);
        return chatRoom;
    }

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        ChatRoom result = chatRepository.createChatRoom(chatRoom);
        return result;
    }

    public boolean enterChatRoom(String roomId) {
        chatRepository.enterChatRoom(roomId);
        return true;
    }

//    public ChannelTopic getTopic(String roomId) {
//        ChannelTopic channelTopic = chatRoomRepository.getTopic(roomId);
//        return channelTopic;
//    }

    public void sendMessage(ChatMessage chatMessage) {
        chatRepository.sendMessage(chatMessage);
//        ChannelTopic channelTopic = chatRepository.getTopic(message.getRoomId());
//        redisPublisher.publish(channelTopic, message);
    }
}
