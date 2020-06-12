package com.ssafy.market.domain.chat.dto;

import com.ssafy.market.domain.chat.domain.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@Document(collection = "chatMessage")
public class RemitMessageDto implements Serializable {
    private static final long serialVersionUID = 3L;
    private MessageType type;
    private Long roomId;
    private String sender;
    private Map<String, String> message;
    private String createdDateTime;
}
