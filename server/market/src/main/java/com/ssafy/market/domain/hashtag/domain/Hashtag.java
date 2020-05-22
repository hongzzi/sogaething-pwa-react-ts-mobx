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
    private Long hashtag_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String hashtag;
}