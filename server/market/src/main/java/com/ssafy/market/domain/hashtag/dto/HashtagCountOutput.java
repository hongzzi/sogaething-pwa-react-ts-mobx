package com.ssafy.market.domain.hashtag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface HashtagCountOutput {
    Long getProduct_id();
    Long getCount();
}