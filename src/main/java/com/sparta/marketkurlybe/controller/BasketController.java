package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.BuyItemListDto;
import com.sparta.marketkurlybe.dto.BuyListResponseDto;
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

    //구매목록 저장
    @PostMapping("/basketList")
    public void createBuyList(@RequestBody BuyItemListDto itemListDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        basketService.createBuyList(itemListDto, userDetails);
    }

    //결제 전 장바구니 조회 + 장바구니 저장 + 장바구니 수정
    @GetMapping("/basketList")
    public Basket basketList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return basketService.basketList(userDetails);
    }

//    //장바구니 선택 삭제
//    @DeleteMapping("/basketList/{buyItemListId}")
//    public void deleteBasket(@PathVariable Long buyItemListId) {
//        basketService.deleteBasket(buyItemListId);
//    }
//
    //장바구니 전체 삭제(개발자용)
    @DeleteMapping("/basketList/all/{buyItemListId}")
    public void deleteBasket2(@PathVariable Long buyItemListId) {
        basketService.allDeleteBasket(buyItemListId);
    }

//    //선택 상품 수정
//    @PutMapping("/basketList/{buyItemListId}")
//    public void updateBasket(@PathVariable Long buyItemListId, @RequestBody BuyListResponseDto responseDto){
//        basketService.updateBasket(buyItemListId, responseDto);
//
//    }

}
