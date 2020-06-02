package com.ssafy.market.domain.chat.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "chatRoom")
public class ChatRoom implements Serializable {
    private static final long serializableUID = 6494678977089006639L;

    @Id
    private Long roomId;
    private String postId;
    private String sellerId;
    private String buyerId;
    private boolean isBuyerExit;
    private boolean isSellerExit;
    private String createdDateTime;
    private String modifiedDateTime;
}
