package com.ssafy.market.domain.chat.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringResult {
    // 전달할 문자열값
    private String content;
    // 수행 작업
    private String name;
    // 상태
    private String state;

}
