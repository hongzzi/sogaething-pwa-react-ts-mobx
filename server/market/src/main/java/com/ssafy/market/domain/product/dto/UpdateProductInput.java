package com.ssafy.market.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductInput {
    private Long productId;
    private Long postId;
    private Long price;
    private String name;
    private String category;
//    private String state;
}
