package com.ssafy.market.domain.post.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postId")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "isBuy")
    private boolean isBuy;

    @Column(name = "title")
    private String title;

    @Column(name = "saleDate")
    private Date saleDate;

    @Column(name = "contents")
    private String contents;

    @Column(name = "viewCount")
    private Long viewCount;

    @Column(name = "deal")
    private String deal;

    @Column(name = "dealState")
    private String dealState;

    public Post(Long postId, User user, boolean isBuy, String title, Date saleDate, String contents, Long viewCount, String deal, String dealState) {
        this.postId = postId;
        this.user = user;
        this.isBuy = isBuy;
        this.title = title;
        this.saleDate = saleDate;
        this.contents = contents;
        this.viewCount = viewCount;
        this.deal = deal;
        this.dealState = dealState;
    }
    public void update(String title, String contents, String deal){
        this.title = title;
        this.contents = contents;
        this.deal = deal;
    }
}

