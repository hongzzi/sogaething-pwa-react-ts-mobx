package com.ssafy.market.domain.detaildeal.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "detail_deal")
public class DetailDeal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;

    @OneToOne(targetEntity = Post.class)
    @JoinColumn(name="postId")
    private Post post;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user;

    public DetailDeal(Long dealId, Post post, User user){
        this.dealId = dealId;
        this.post = post;
        this.user = user;
    }
}
