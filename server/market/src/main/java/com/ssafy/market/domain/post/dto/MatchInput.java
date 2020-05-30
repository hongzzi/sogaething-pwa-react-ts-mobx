package com.ssafy.market.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchInput {
    private String category;
    private int[] price;
    private String[] hashtag;
}
