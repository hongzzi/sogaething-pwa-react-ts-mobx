package com.ssafy.market.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutput {
    private Long productId;
    private Long postId;
    private String name;
    private Long price;
    private String category;
//    private String state;
}
