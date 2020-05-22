package com.ssafy.market.domain.file.domain;

import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String imgPath;
}
