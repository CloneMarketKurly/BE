package com.sparta.marketkurlybe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class BasketRequestDto {
    private Long itemId;
    private int count;
}
