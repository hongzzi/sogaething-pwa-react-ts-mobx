package com.ssafy.market.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker // WebSocket 서버를 활성화하는 데 사용
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //, WebSocketConfigurer { // 웹 소켓 연결을 구성하기 위한 메서드를 구현하고 제공

//    private final WebSocketHandler webSocketHandler;

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) { // 클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드 포인트를 등록
//        registry.addEndpoint("/websockethandler").withSockJS(); // SockJS는 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화하는 데 사용
//        // Fallback: 어떤 기능이 약해지거나 제대로 동작하지 않을 때, 이에 대처하는 기능 또는 동작
//        // STOMP 는 Simple Text Oriented Messaging Protocol 의 약어. 데이터 교환의 형식과 규칙을 정의하는 메시징 프로토콜
//
//        // STOMP 를 사용하는 이유? WebSocket 은 통신 프로토콜 일뿐 특정 주제를 구독한 사용자에게만 메시지를 보내는 방법 또는 특정 사용자에게 메시지를 보내는 방법과 같은 내용은 정의하지 않는다.
//        // 이러한 기능을 위해서는 STOMP 가 필요
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) { // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 하는 데 사용될 메시지 브로커를 구성
//        registry.enableSimpleBroker("/topic/"); // 메모리 기반 메세지 브로커가 해당 api 구독하고 있는 클라이언트에게 메세지 전달 ( "/topic" 시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의)
//        registry.setApplicationDestinationPrefixes("/app"); // 서버에서 클라이언트로부터의 메세지를 받음 api의 prefix ("/app" 시작되는 메시지가 message-handling methods 로 라우팅 되어야 한다는 것을 명시)
//        // 메시지 브로커는 특정 주제를 구독 한 연결된 모든 클라이언트에게 메시지를 broadcast 한다.
//        // 브로드 캐스팅: 송신 호스트가 전송한 데이터가 네트워크에 연결된 모든 호스트에 전송되는 방식을 의미한다.
//
//        // 위의 예에서 간단한 인 메모리 메시지 브로커를 활성화했다.
//        // 그러나 RabbitMQ 또는 ActiveMQ와 같은 다른 모든 기능을 갖춘 메시지 브로커를 자유롭게 사용할 수 있다.
//    }

//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
//    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*").withSockJS();
    }
}
