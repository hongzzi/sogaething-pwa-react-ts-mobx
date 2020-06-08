package com.ssafy.market.domain.hashtag.domain;

import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="hashtag")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtagId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private String hashtag;

    public Hashtag(Long hashtagId, Product product, String hashtag){
        this.hashtagId = hashtagId;
        this.product = product;
        this.hashtag = hashtag;
    }
    public void update(String hashtag){
        this.hashtag = hashtag;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "hashtagId=" + hashtagId +
                ", product=" + product +
                ", hashtag='" + hashtag + '\'' +
                '}';
    }
}