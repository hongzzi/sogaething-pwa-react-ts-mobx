package com.ssafy.market.domain.chat.repository;

import com.ssafy.market.domain.chat.domain.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom;
        try {
            chatRoom= ChatRoom.create(name);
            chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
            System.out.println(chatRoom.getRoomId());
            return chatRoom;
        } catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }
}
