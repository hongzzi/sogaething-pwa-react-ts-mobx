package com.ssafy.market.domain.post.dto;

import com.ssafy.market.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostInput {
    private String title;
    private Date saleDate;
    private String contents;
    private String deal;
    private String dealState;
    private String category;
    private String productname;
    private String productState;
    private Long price;
    private String hashtag;
    private String imgPath;
}
