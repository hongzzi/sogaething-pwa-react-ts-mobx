package com.ssafy.market.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInput {
    private String name;
    private String email;
    private String imageUrl;
    private String phone;
    private String address;
}
