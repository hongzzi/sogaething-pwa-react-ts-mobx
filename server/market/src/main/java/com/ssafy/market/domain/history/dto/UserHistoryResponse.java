package com.ssafy.market.domain.history.dto;

import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoryResponse {
    private User user;
    private Long postId;
    private Boolean isBuy;
    private String title;
    private Date saleDate;
    private String contents;
    private Long viewCount;
    private String deal;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<Hashtag> hashTags;
    private Long price;
    private List<File> imgUrls;
}
