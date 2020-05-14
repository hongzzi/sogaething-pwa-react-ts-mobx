package com.ssafy.market.domain.chat.controller;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.MessageType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

// event listner를 이용하여 소켓 연결(socket connect) 그리고 소켓 연결 끊기(disconnect) 이벤트를 수신하여
// 사용자가 채팅방을 참여(JOIN)하거나 떠날때(LEAVE)의 이벤트를 logging 하거나 broadcast 할 수 있다.

@Component // @Component - 어노테이션은 자바 클래스를 스프링 빈이라고 표시하는 역할을 합니다. 이 어노테이션을 사용함으로써 스프링의 component-scanning 기술이 이 클래스를 어플리케이션 컨텍스트에 빈으로 등록하게 된다.
@RequiredArgsConstructor
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {

//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        System.out.println(headerAccessor);
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if (username != null) {
//            logger.info("User Connected : " + username);
//            logger.info("Received a new web socket connection");
//        }
        logger.info("Received a new web socket connection");
        // 이미 ChatController 의 addUser()메서드에서 사용자 참여 이벤트를  broadcast 하였기 때문에
        // handleWebSocketConnectListener()에서 사용하는 SessionConnected 이벤트 에서는
        // 별다른 동작 없이 logging 처리.
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        System.out.println(headerAccessor);
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            logger.info("User Disconnected : " + username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }

        // SessionDisconnect 이벤트에서는 웹 소켓 세션에서 사용자 이름을 추출하고 연결된 모든 클라이언트에게 사용자 퇴장 이벤트를 broadcast 하는 코드를 작성.
    }
}

// @EventListener - Spring 4.2부터는 이벤트 리스너가 ApplicationListener 인터페이스를 구현하는 Bean 일 필요가 없어졌다.
// @EventListener 주석을 통해 관리되는 Bean 의 모든 public 메소드에 등록 할 수 있다.
// 해당 어노테이션은 Bean 으로 등록된 Class 의 메서드에서 사용할 수 있습니다.
// 해당 어노테이션이 적용되어 있는 메서드의 인수로 현재 SessionConnectedEvent 와 SessionDisconnectEvent 가 있다.
// 해당 클래스들의 상속관계를 거슬로 올라가다 보면 ApplicationEvent 를 상속 받는것을 알 수 있다. (Spring 4.2 부터는 ApplicationEvent 를 상속받지 않는 POJO 클래스로도 이벤트로 사용가능하다고 한다.)