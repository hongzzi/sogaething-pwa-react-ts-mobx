package com.ssafy.market.domain.product.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name="post_id")
    private Long post_id;

    private Long price;
    private String category;
    private boolean state;
}