package com.ssafy.market.domain.hashtag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashtagOutput {
    private Long hashtagId;
    private Long productId;
    private String hashtag;
}
