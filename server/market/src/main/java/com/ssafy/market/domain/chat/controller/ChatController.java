package com.ssafy.market.domain.chat.controller;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

// Controller 의 메서드는 message handling methods 이다.
//이 메서드들은 한 Client 에게서 message 를 수신한 다음, 다른 Client 에게 broadcast 한다.
@Controller
public class ChatController {

//    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if (username != null) {
//            logger.info("User Connected : " + username);
//        }
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}

// WebSocketConfig 에서
// "/app"로 시작하는 대상이 있는 클라이언트에서 보낸 모든 메시지는 @MessageMapping 어노테이션이 달린
// 메서드로 라우팅 된다.

// 예를 들어
// "/app/chat.sendMessage" 인 메세지는 sendMessage()로 라우팅 되며
// "/app/chat.addUser" 인 메시지는 addUser()로 라우팅된다.