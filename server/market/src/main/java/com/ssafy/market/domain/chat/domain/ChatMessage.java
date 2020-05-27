package com.ssafy.market.domain.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "chatMessage")
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

//    private String createdDateTime = LocalDateTime.now().toString();

    @Override
    public String toString() {
        return "ChatMessage{" +
                "type=" + type +
                ", content='" + message + '\'' +
                ", sender='" + sender + '\'' +
//                ", createdDateTime=" + createdDateTime +
                '}';
    }
}
