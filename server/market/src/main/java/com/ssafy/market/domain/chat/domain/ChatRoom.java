package com.ssafy.market.domain.chat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ChatRoom implements Serializable {
    private static final long serializableUID = 6494678977089006639L;

//    private String roomId;
//    private String name;
//    private Long sellerId;
//    private Long buyerId;
    private String postId;
//    private boolean isBuyerExit;
//    private boolean isSellerExit;

//    public static ChatRoom create(String name) {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.roomId = UUID.randomUUID().toString();
//        chatRoom.name = name;
//        return chatRoom;
//    }

}
