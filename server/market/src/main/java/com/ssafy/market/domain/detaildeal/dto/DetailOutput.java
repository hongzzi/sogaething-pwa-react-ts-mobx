package com.ssafy.market.domain.detaildeal.dto;

import com.ssafy.market.domain.user.dto.UserInfoOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailOutput {
    private Long dealId;
    private Long postId;
    private List<FileArr> imgPaths; // file
    private String title; // post
    private String category; // product
    private String hashtag; // hashtag
    private String contents; // post
    private Long price; // product
    private Long buyerId;
    private Long sellerId;
    private UserInfoOutput user;
}
