package com.ssafy.market.domain.detaildeal.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class DetailDeal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dealId;

    @OneToOne(targetEntity = Post.class)
    @JoinColumn(name="postId")
    private Long postId;

//    @OneToOne(targetEntity = User.class)
//    @JoinColumn(name="id")
    private Long buyerId;

//    @OneToOne(targetEntity = User.class)
//    @JoinColumn(name="id")
    private Long sellerId;

    private Long hashtagId;
}
