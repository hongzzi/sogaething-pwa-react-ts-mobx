package com.ssafy.market.domain.file.domain;

import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "productId")
    private Product product;
//    private Long productId;

    private String imgPath;

    public File(Long fileId, Product product, String imgPath){
        this.fileId = fileId;
        this.product = product;
        this.imgPath = imgPath;
    }
    public void update (String imgPath){
        this.imgPath = imgPath;
    }
}
