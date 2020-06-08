package com.ssafy.market.domain.product.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="product")
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name="postId")
    private Post post;

    private String name;
    private Long price;
    private String category;
    private Long state;

    public Product(Long productId, Post post, Long price, String category,Long state){
        this.productId = productId;
        this.post = post;
        this.price = price;
        this.category = category;
        this.state = state;
    }
    public void update(Post post, Long price, String category){
        this.post = post;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", post=" + post +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
