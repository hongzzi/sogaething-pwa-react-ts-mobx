package com.ssafy.market.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput {
    private Long userId;
    private String name;
    private String email;
    private String imageUrl;
    private String provider;
    private Long providerId;
    private String phone;
    private String address;
    private int trust;
}
