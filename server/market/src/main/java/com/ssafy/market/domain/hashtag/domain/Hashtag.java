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
    private Long productId;

    private String hashtag;
}