package com.sparta.marketkurlybe.dto;

import com.sparta.marketkurlybe.model.BuyItemList;
import com.sparta.marketkurlybe.model.User;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketPutDto {
    private User user;
    private int deliverFee;
    private int totalPrice;
    private int sumPrice;
    private int count;
    private BuyItemList buyItemList;
}
