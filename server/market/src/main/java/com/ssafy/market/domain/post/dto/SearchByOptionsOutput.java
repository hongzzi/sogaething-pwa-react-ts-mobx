package com.ssafy.market.domain.post.dto;

public interface SearchByOptionsOutput {
    Long getPostId();
    String getTitle();
    String getCreatedDate();
    String getModifiedDate();
    String getImgPath();
    String getHashtag();
    Long getPrice();
    Boolean getIsBuy();
    Long getViewCount();
    String getDeal();
    String getDealState();
    String getSaleDate();
    String getTransaction();
    String getCategory();
    Long getUserId();
    String getName();
    String getAddress();
    int getTrust();
    String getImageUrl();
    String getContents();
}