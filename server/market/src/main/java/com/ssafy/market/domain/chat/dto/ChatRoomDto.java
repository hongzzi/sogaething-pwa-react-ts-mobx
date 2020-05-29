package com.ssafy.market.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
public class ChatRoomDto implements Serializable {
    private static final long serialVersionUID = 2L;
    private Long roomId;
    private Map<String, String> sellerUser;
    private Map<String, String> buyerUser;
    private String createDateTime;
    private String modifiedDateTime;
    private Boolean isSellerExit;
    private Boolean isBuyerExit;
    private String lastMessage;
}
