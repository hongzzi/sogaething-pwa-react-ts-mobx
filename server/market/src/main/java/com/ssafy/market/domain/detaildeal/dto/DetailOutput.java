package com.ssafy.market.domain.detaildeal.dto;

import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.user.dto.UserInfoOutput;
import com.ssafy.market.domain.user.dto.UserInfoResponse;
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
    private List<String> imgPaths; // file
    private String title; // post
    private String category; // product
//    private List<Hashtag> hashtag; // hashtag
    private List<String> hashtag; // hashtag
    private String contents; // post
    private Long price; // product
    private Long buyerId;
    private Long sellerId;
    private UserInfoResponse user;
    private String createdDate;
    private String modifiedDate;
}
