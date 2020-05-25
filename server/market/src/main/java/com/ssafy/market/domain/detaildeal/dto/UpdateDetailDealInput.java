package com.ssafy.market.domain.detaildeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDetailDealInput {
    private Long dealId;
    private Long postId;
    private Long hashtagId;
    private String Title;
    private String Contents;
    private int price;
    private String deal;
    private String imgPath;
    private String category;
    private String hashtag;
    private String address;
}
