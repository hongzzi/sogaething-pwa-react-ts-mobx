package com.ssafy.market.domain.chat.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.dto.RemitMessageDto;
import com.ssafy.market.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(RedisSubscriber.class);

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;
    /**
     * Redis에서 메시지가 발행(publish)되면 대기하고 있던 onMessage가 해당 메시지를 받아 처리한다.
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // redis 에서 발행된 데이터를 받아 deserialize
//            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            // ChatMessage 객체로 맵핑
            System.out.println("after deserialize: " + publishMessage);

            if (publishMessage.startsWith("{\"type\":\"REMIT\"")) {
                RemitMessageDto roomMessage = objectMapper.readValue(publishMessage, RemitMessageDto.class);
                messagingTemplate.convertAndSend("/sub/chat/room" + roomMessage.getRoomId(), roomMessage);
            } else {
                ChatMessage roomMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
                // WebSocket 구독자에게 채팅 메시지 Send
                messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), roomMessage);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
