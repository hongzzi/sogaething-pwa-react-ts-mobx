package com.ssafy.market.domain.hashtag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autocomplete {
    private String hashtag;
    private Long count;
}
