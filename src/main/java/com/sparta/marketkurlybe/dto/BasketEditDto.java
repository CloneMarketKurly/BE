package com.sparta.marketkurlybe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasketEditDto {
    private Long basketId;
    private int count;
}
