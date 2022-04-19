package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.BuyItemListDto;
import com.sparta.marketkurlybe.model.Basket;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    //유저정보는 장바구니에서 넣을 예정
    @PostMapping("/basketList")
    public void createBuyList(@RequestBody BuyItemListDto itemListDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        basketService.createBuyList(itemListDto, userDetails);
    }

    //결제 전 장바구니
    @GetMapping("/basketList")
    public Basket basketList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return basketService.basketList(userDetails);
    }

//    @DeleteMapping("/basketList/{basketId}")
//    public void deleteBasket(@PathVariable Long basketId,@AuthenticationPrincipal UserDetailsImpl userDetails){
//        basketService.deleteBasket(basketId ,userDetails);
//    }

}
