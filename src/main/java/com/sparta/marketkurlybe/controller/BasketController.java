package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.BasketRequestDto;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/item/details")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    //장바구니 담기(회원용)
    @PostMapping("/basket")
    public void crateBasket(@RequestBody BasketRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //비회원 주문시, 예외처리 -> 프론트분들께 여쭤보기(로그인 회원이면 서버로 요청, 아니면 중간저장소에 저장 후 날림)
        String userId = userDetails.getUsername();
        basketService.crateBasket(requestDto, userId);
    }

    //장바구니 불러오기(회원용)
    @GetMapping("/basket/{basketId}")
    public Map<String,Object> getBasket(@AuthenticationPrincipal UserDetailsImpl userDetails){
       return basketService.getBasket(userDetails);
    }

//    //장바구니 수정
//    @PutMapping("/item/basket/{basketId}")
//    public Map<String, Object> editBasket(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long basketId, @RequestBody BasketEditDto basketEditDto){
//        return basketService.editBasket(userDetails, basketId, basketEditDto);
//    }

}
