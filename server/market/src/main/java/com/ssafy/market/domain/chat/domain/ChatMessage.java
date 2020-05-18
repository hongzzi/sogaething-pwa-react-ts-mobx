package com.ssafy.market.domain.chat.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Data
@Document(collection = "chatMessage")
public class ChatMessage extends BaseTimeEntity {
    private MessageType type;
    private String content;
    private String sender;
    private ZonedDateTime createdDateTime = ZonedDateTime.now();

    @Override
    public String toString() {
        return "ChatMessage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", createdDateTime=" + createdDateTime +
                '}';
    }
}
