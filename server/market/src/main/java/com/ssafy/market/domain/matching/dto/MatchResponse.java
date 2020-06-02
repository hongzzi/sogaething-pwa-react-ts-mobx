package com.ssafy.market.domain.matching.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchResponse {
    private Long matchingId;
    private String category;
    private int minPrice;
    private int maxPrice;
    private List<String> hashtag;
    private String transaction;
    private LocalDateTime createdDate;
    private String possibility;
}
