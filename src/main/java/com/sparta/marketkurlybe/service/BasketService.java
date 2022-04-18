package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.BuyItemListDto;
import com.sparta.marketkurlybe.model.BuyItemList;
import com.sparta.marketkurlybe.model.Item;
import com.sparta.marketkurlybe.model.User;
import com.sparta.marketkurlybe.repository.BuyItemListRepository;
import com.sparta.marketkurlybe.repository.ItemRepository;
import com.sparta.marketkurlybe.repository.UserRepository;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BuyItemListRepository buyItemListRepository;

    //구매목료
    public void createBuyList(BuyItemListDto itemListDto) {

        Item item = itemRepository.findById(itemListDto.getItemId()).orElseThrow(
                () -> new NullPointerException("상품이 존재하지 않습니다.")
        );

        Item itemList = Item.builder()
                .title(item.getTitle())
                .price(item.getPrice())
                .image(item.getImage())
                .build();

        BuyItemList buyItemList = BuyItemList.builder()
                .item(itemList)
                .count(itemListDto.getCount())
                .build();

        buyItemListRepository.save(buyItemList);
    }




}
