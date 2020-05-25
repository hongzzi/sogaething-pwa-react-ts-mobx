package com.ssafy.market.domain.history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryOutput {
    private Long userId;
    private Long postId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
