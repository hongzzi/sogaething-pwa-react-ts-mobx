package com.ssafy.market.domain.detaildeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDealOutput {
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
    private String address; //user
}

//게시글id, 게시글 사진, 게시글 명, 카테고리, 해시태그, 게시글 내용, 가격,
//        작성자, 작성자 이미지, 작성자 위치