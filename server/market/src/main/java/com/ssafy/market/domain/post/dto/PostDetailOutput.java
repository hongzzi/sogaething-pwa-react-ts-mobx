package com.ssafy.market.domain.post.dto;

import com.ssafy.market.domain.user.dto.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailOutput {
    private Long postId;
    private String title;
    private String category;
    private List<String> imgPaths;
    private List<String> hashtag;
    private String contents;
    private Long price;
    private UserInfoResponse user;
    private Long viewCount;
    private Boolean isBuy;
    private String deal;
    private String dealState;
    private String saleDate;
    private String transaction;
    private String createdDate;
    private String modifiedDate;
}
