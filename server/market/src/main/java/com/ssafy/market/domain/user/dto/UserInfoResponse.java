package com.ssafy.market.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private Long userId;
    private String name;
    private String address;
    private int trust;
    private Long numOfPosts;
    private String imgurl;
}

