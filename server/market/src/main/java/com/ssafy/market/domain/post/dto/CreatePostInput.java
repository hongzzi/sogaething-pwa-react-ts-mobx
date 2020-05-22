package com.ssafy.market.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostInput {
    private Long uploaderId;
    private String title;
    private Date saleDate;
    private String contents;
    private String deal;
}
