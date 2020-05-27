//package com.ssafy.market.domain.post.domain;
//
//import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Builder
//@Document(indexName = "sogaething", type = "post")
//public class PostEl implements Serializable {
//    @Id
//    private Long postId;
//    private Long userId;
//    private boolean isBuy;
//    private String title;
//    private Date saleDate;
//    private String contents;
//    private Long viewCount;
//    private String deal;
//    private String dealState;
//    private LocalDateTime createdDate;
//    private LocalDateTime modifiedDate;
//}
