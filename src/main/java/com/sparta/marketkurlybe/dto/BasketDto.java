package com.sparta.marketkurlybe.dto;

import com.sparta.marketkurlybe.model.Basket;
import com.sparta.marketkurlybe.model.BuyItemList;
import com.sparta.marketkurlybe.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BasketDto {
    private Long basketId;
    private User user;
    private List<BilDto> buyItemList;
    private int deliverFee;
    private int totalPrice;
    private int sumPrice;

    public BasketDto(Basket basket){
        this.setBasketId(basket.getBasketId());
        this.setUser(basket.getUser());
        this.setDeliverFee(basket.getDeliverFee());
        this.setTotalPrice(basket.getTotalPrice());
        this.setSumPrice(basket.getSumPrice());
        List<BilDto> bilDtoList = new ArrayList<>();
        for (BuyItemList buyItemList : basket.getBuyItemList()){
            BilDto bilDto = BilDto.builder()
                    .buyItemListId(buyItemList.getBuyItemListId())
                    .user(buyItemList.getUser())
                    .item(buyItemList.getItem())
                    .count(buyItemList.getCount())
                    .build();
            bilDtoList.add(bilDto);
        }
        this.setBuyItemList(bilDtoList);
    }
}
