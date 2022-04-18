package com.sparta.marketkurlybe.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BasketRequestDto {
//    private Long userId;
    private Long itemId;
    private int count;
}
