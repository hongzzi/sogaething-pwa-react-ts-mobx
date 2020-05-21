package com.ssafy.market.domain.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.market.domain.chat.controller.WebSocketHandler;
import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

//    private final ChatRepository chatRepository;
//
//    public Boolean saveMessage(ChatMessage chatMessage){
//        try {
//            ChatMessage chatMessage2 = chatRepository.save(chatMessage);
//            System.out.println(chatMessage2);
//            return true;
//        } catch (RuntimeException e) {
//            throw e;
//        }
//    }

    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setRoomId(randomId);
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch(IOException e){
            logger.error(e.getMessage(), e);
        }
    }
}
