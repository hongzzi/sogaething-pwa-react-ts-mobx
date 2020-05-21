package com.ssafy.market.domain.post.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    private boolean isBuy;

    private String title;

    private Date saleDate;

    private String contents;

    private Long viewCount;

    private String deal;

    private String dealState;

    public Post(Long postId) {
        this.postId = postId;
    }

    public Post(User user, String title, Date saleDate, String contents, String deal) {
        this.user = user;
//        this.uploaderId = uploaderId;
        this.title = title;
        this.saleDate = saleDate;
        this.contents = contents;
        this.deal = deal;
    }

    public Post(Long postId, User user, boolean isBuy, String title, Date saleDate, String contents, Long viewCount, String deal, String dealState) {
        this.postId = postId;
        this.user = user;
//        this.uploaderId = uploaderId;
        this.isBuy = isBuy;
        this.title = title;
        this.saleDate = saleDate;
        this.contents = contents;
        this.viewCount = viewCount;
        this.deal = deal;
        this.dealState = dealState;
    }
}

