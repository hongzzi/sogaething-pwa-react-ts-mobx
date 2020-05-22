package com.ssafy.market.domain.hashtag.domain;

import com.ssafy.market.domain.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hashtagId;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "productId")
    private Product product;
//    private Long productId;
    private String hashtag;

    public Hashtag(Long hashtagId, Product product, String hashtag){
        this.hashtagId = hashtagId;
        this.product = product;
        this.hashtag = hashtag;
    }
}