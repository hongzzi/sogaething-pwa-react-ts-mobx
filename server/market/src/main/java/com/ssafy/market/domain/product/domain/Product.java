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
    private Long productId;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name="postId")
    private Post post;

//    private Long postId;
    private String name;
    private Long price;
    private String category;
    private Boolean state;

    public Product(Long productId, Post post, String name, Long price, String category,boolean state){
        this.productId = productId;
        this.post = post;
        this.name = name;
        this.price = price;
        this.category = category;
        this.state = state;
    }
}
