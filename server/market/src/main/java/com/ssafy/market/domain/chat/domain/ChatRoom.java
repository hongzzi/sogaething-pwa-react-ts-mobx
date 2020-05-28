package com.ssafy.market.domain.chat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom implements Serializable {
    private static final long serializableUID = 6494678977089006639L;

//    private String roomId = UUID.randomUUID().toString();
//    private String name;
    private Long sellerId;
    private Long buyerId;
    private String postId;
    private boolean isBuyerExit;
    private boolean isSellerExit;
    private String createdDateTime = LocalDateTime.now().toString();

//    public static ChatRoom create(String name) {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.roomId = UUID.randomUUID().toString();
//        chatRoom.name = name;
//        return chatRoom;
//    }

}
