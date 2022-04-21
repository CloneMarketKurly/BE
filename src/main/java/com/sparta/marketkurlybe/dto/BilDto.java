package com.sparta.marketkurlybe.dto;

import com.sparta.marketkurlybe.model.Item;
import com.sparta.marketkurlybe.model.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BilDto {
    private Long buyItemListId;
    private User user;
    private Item item;
    private int count;
}
