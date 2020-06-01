package com.ssafy.market.domain.post.dto;

import com.ssafy.market.domain.detaildeal.dto.FileArr;
import com.ssafy.market.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostInput {
    private String title;
    private String category;
    private String[] imgPaths;
    private String[] hashtag;
    private String contents;
    private String transaction;
    private Long price;
}
