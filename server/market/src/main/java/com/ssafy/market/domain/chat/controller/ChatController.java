package com.ssafy.market.domain.chat.controller;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.MessageType;
import com.ssafy.market.domain.chat.dto.RemitMessageDto;
import com.ssafy.market.domain.chat.service.ChatRoomService;
import com.ssafy.market.domain.chat.service.ChatService;
import com.ssafy.market.domain.chat.util.StringResult;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;
    private final ChatRoomService chatRoomService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage message) {
        logger.debug("message {}", message);
        if (MessageType.ENTER.equals(message.getType())) {
            chatRoomService.enterChatRoom(message.getRoomId());
        } else {
            chatService.sendMessage(message);
        }
    }

    @MessageMapping("/chat/remit")
    public void remitMessage(RemitMessageDto remitMessageDto){
        logger.debug("remitMessage {}", remitMessageDto);
        if (MessageType.REMIT.equals(remitMessageDto.getType())) {
            chatService.sendRemitMessage(remitMessageDto);
        }
    }

    @ApiOperation(value = "특정 방의 채팅메시지를 가져온다.", response=Map.class)
    @GetMapping("/message/{roomId}")
    public ResponseEntity<Object> findChatMessagesByRoomId(@PathVariable Long roomId){
        try {
            Map<String, Object> result = chatService.findChatMessagesByRoomId(roomId);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            String errorMessage = e.getMessage();
            logger.error("findChatMessagesByRoomId {}", errorMessage);
            StringResult stringResult = new StringResult(errorMessage, "findChatMessagesByRoomId", "FAIL");
            return new ResponseEntity<Object>(stringResult, HttpStatus.BAD_REQUEST);
        }
    }
}