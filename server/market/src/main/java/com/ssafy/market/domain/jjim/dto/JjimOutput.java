package com.ssafy.market.domain.jjim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JjimOutput {
    private Long jjimId;
    private Long postId;
    private String title;
    private String category;
    private String imgPath;
    private Long price;
    private String createdDate;
    private String modifiedDate;
}
