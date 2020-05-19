package com.ssafy.market.domain.chat.controller;

import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    // 채팅방 생성
    @PostMapping("/create/room")
    public Object createRoom(@RequestParam String name) {
        ChatRoom chatRoom = chatRoomRepository.createChatRoom(name);
        return chatRoom;
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> room() {
        System.out.println("목록 반환");
        return chatRoomRepository.findAllRoom();
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
