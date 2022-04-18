package com.sparta.marketkurlybe.dto;

import com.sparta.marketkurlybe.model.Basket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
public class BasketRequestDto {
    private Long itemId;
    private int count;
}
