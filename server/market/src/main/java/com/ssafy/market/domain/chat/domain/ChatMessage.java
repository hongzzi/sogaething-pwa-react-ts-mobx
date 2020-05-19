package com.ssafy.market.domain.chat.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Data
@Document(collection = "chatMessage")
public class ChatMessage{
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private ZonedDateTime createdDateTime = ZonedDateTime.now();

    @Override
    public String toString() {
        return "ChatMessage{" +
                "type=" + type +
                ", content='" + message + '\'' +
                ", sender='" + sender + '\'' +
                ", createdDateTime=" + createdDateTime +
                '}';
    }
}
