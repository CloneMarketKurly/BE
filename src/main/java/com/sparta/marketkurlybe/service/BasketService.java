package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.BasketRequestDto;
import com.sparta.marketkurlybe.model.Basket;
import com.sparta.marketkurlybe.model.Item;
import com.sparta.marketkurlybe.model.User;
import com.sparta.marketkurlybe.repository.BasketRepository;
import com.sparta.marketkurlybe.repository.ItemRepository;
import com.sparta.marketkurlybe.repository.UserRepository;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final ItemRepository itemRepository;
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;

    //장바구니 담기(회원용)
    @Transactional
    public void crateBasket(BasketRequestDto requestDto, UserDetailsImpl userDetails) {

        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원이 존재하지 않습니다.")
        );
        Item item = itemRepository.findById(requestDto.getItemId()).orElseThrow(
                () -> new NullPointerException("상품이 존재하지 않습니다.")
        );

        int price = item.getPrice() * requestDto.getCount();

        //배송비 포함 총 금액은 장바구니 페이지로 이동시 get요청으로 처리
        //현재 조건을 주면, 일정금액 미만의 제품을 담을 때마다 계속 배달비가 포함되기 때문
        Basket basket = Basket.builder()
                .user(user)
                .title(item.getTitle())
                .image(item.getImage())
                .price(price)
                .build();

        basketRepository.save(basket);
    }
}
