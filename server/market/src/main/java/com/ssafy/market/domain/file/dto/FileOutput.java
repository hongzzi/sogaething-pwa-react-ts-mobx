package com.ssafy.market.domain.file.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileOutput {
    private Long fileId;
    private Long productId;
    private String imgPath;
}
