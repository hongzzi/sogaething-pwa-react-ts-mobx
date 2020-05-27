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
//    private Date saleDate;
    private String contents;
    private String deal;
    private String dealState;
    private String category;
    private String productname;
    private String productState;
    private Long price;
    private String hashtag;
//    private String imgPath;
}
