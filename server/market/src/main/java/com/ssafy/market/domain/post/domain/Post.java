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
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
    private Long userId;

    @Column(name = "is_buy")
    private boolean isBuy;

    @Column(name = "title")
    private String title;

    @Column(name = "sale_date")
    private Date saleDate;

    @Column(name = "contents")
    private String contents;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "deal")
    private String deal;

    @Column(name = "deal_state")
    private String dealState;
//    @Column(name = "deal_location")
//    private String dealLocation;

    public Post(Long postId) {
        this.postId = postId;
    }

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
//        this.dealLocation = dealLocation;
    }
    public void update(String title, String contents, String deal,String dealState){
        this.title = title;
        this.contents = contents;
        this.deal = deal;
        this.dealState=dealState;
    }
    public void update(Long viewCount){
        this.viewCount = viewCount;
    }
    public void update(Boolean isBuy){
        this.isBuy = isBuy;
    }
}

