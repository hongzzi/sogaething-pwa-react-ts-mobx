package com.ssafy.market.domain.chat.controller;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.domain.MessageType;
import com.ssafy.market.domain.chat.repository.ChatMongoRepository;
import com.ssafy.market.domain.chat.service.ChatService;
import com.ssafy.market.domain.chat.service.RedisExample;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatMongoRepository chatRoomRepository;
    private final ChatService chatService;
    private final RedisExample redisExample;

//    // 채팅방 생성
//    @PostMapping("/room")
//    public Object createChatRoom(@RequestParam String name) {
//        ChatRoom chatRoom = chatService.createChatRoom(name);
//        return chatRoom;
//    }

    // 채팅방 생성
    @PostMapping("/room")
    public Object createChatRoom(@RequestBody ChatRoom chatRoom) {
        System.out.println(chatRoom);
        ChatRoom result = chatService.createChatRoom(chatRoom);
        System.out.println(result);
        return chatRoom;
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{postId}")
    public ChatRoom roomInfo(@PathVariable String postId) {
        return chatService.findRoomById(postId);
    }

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (MessageType.ENTER.equals(message.getType())) {
            chatService.enterChatRoom(message.getRoomId());
//            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // WebSocket 에 발행된 메시지를 redis로 발행한다.(publish)
//        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
        chatService.sendMessage(message);
    }

    // redis 예제
    @GetMapping("/redis")
    public Boolean redisTest(){
        redisExample.setGetValuesExam();

//        redisExample.hashExam();
        return true;
    }
}
