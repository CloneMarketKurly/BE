package com.sparta.marketkurlybe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BuyItemListDto {
    private Long ItemId;
    private int count;
}
