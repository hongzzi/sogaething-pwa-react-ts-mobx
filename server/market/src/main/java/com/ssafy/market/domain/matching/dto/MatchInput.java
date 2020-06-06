package com.ssafy.market.domain.matching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchInput {
    private String category;
    private int minPrice;
    private int maxPrice;
    private String[] hashtag;
    private String transaction;
}
