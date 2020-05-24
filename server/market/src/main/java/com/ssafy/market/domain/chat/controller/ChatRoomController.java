package com.ssafy.market.domain.chat.controller;

import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.repository.ChatRoomRepository;
import com.ssafy.market.domain.chat.service.RedisExample;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final RedisExample redisExample;

    // 채팅방 생성
    @PostMapping("/create/room")
    public Object createRoom(@RequestParam String name) {
        ChatRoom chatRoom = chatRoomRepository.createChatRoom(name);
        return chatRoom;
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    // redis 예제
    @GetMapping("/redis")
    public Boolean redisTest(){
        redisExample.setGetValuesExam();

        redisExample.hashExam();
        return true;
    }
}
