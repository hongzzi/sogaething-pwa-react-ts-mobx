package com.ssafy.market.domain.hashtag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHashtagInput {
    private Long hashtagId;
    private String hashtag;
}
