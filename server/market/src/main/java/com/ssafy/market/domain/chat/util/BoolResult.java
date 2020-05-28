package com.ssafy.market.domain.chat.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoolResult {
    // 수행 결과
    private Boolean result;
    // 수행 작업
    private String name;
    // 상태
    private String state;
}
