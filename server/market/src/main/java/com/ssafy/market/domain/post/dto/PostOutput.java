package com.ssafy.market.domain.post.dto;

import com.ssafy.market.domain.detaildeal.dto.FileArr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOutput {
    private Long postId;
    private Long userId;
    private Boolean isBuy;
    private String title;
    private String contents;
    private String deal;
    private String dealState;
    private String category;
    private String name;
    private Long price;
    private String hashtag;
    private List<FileArr> imgPaths; // file
}
