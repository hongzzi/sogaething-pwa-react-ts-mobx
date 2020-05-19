//package com.ssafy.market.domain.chat.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.market.domain.chat.domain.ChatMessage;
//import com.ssafy.market.domain.chat.domain.ChatRoom;
//import com.ssafy.market.domain.chat.service.ChatService;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Component
//@RequiredArgsConstructor
//public class WebSocketHandler extends TextWebSocketHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
//
//    private final ObjectMapper objectMapper;
//    private final ChatService chatService;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        super.handleTextMessage(session, message);
//
//        String payload = message.getPayload();
//        logger.info("payload {}", payload);
////        TextMessage textMessage = new TextMessage("Welcome chatting server~^^*");
////        session.sendMessage(textMessage);
//        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
//        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
//        room.handleActions(session, chatMessage, chatService);
//    }
//}
