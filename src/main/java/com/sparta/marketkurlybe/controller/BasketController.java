package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.BasketRequestDto;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    //장바구니 담기(회원용)
    @PostMapping("/item/basket")
    public void crateBasket(@RequestBody BasketRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        //비회원 주문시, 예외처리 -> 프론트분들께 여쭤보기(로그인 회원이면 서버로 요청, 아니면 중간저장소에 저장 후 날림)

       basketService.crateBasket(requestDto, userDetails);
    }

}
