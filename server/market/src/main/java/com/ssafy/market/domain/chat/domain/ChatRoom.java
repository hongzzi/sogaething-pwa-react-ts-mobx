package com.ssafy.market.domain.chat.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ChatRoom implements Serializable {
    private static final long serializableUID = 6494678977089006639L;

    private String roomId;
    private String name;
//    private Long sellerId;
//    private Long buyerId;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }   
}
