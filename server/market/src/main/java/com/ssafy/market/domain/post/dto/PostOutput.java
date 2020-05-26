package com.ssafy.market.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOutput {
    private Long postId;
    private Long userId;
    private Boolean isBuy;
    private String title;
    private String contents;
    private Long viewCount;
    private String deal;
}
