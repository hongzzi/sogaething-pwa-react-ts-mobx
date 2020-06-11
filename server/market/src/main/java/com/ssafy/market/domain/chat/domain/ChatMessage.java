package com.ssafy.market.domain.chat.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document(collection = "chatMessage")
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private MessageType type;
    private Long roomId;
    private String sender;
    private String message;
    private String createdDateTime;
}
