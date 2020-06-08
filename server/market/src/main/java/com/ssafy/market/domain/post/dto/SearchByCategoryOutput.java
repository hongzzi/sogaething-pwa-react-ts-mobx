package com.ssafy.market.domain.post.dto;

public interface SearchByCategoryOutput {
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
}