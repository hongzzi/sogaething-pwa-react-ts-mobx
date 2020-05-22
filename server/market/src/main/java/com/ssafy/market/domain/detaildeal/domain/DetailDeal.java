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
    private Long deal_id;

    @OneToOne
    @JoinColumn(name="post_id")
    private Post post;

    private Long buyer_id;

    private Long seller_id;
}
