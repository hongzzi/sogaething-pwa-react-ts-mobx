package com.ssafy.market.domain.post.dto;

import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentPostResponse {
    private Long postId;
    private User user;
    private List<Hashtag> hashTags;
    private Boolean isBuy;
    private Long price;
    private Date saleDate;
    private List<File> imgUrls;
    private String category;
    private String deal;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
