package com.ssafy.market.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostInput {
    private Long postId;
    private String title;
    private String category;
    private String[] imgPaths;
    private String[] hashtag;
    private String contents;
    private String transaction;
    private Long price;
}
