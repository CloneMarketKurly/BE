package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.*;
import com.sparta.marketkurlybe.model.Basket;
import com.sparta.marketkurlybe.model.Orders;
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
    public BasketDto basketList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return basketService.basketList(userDetails);
    }

    //장바구니 선택 삭제(삭제하면, 자동으로 장바구니 조회에 삭제된 정보 반영 됨)
    @DeleteMapping("/basketList/{buyItemListId}")
    public void deleteBasket(@PathVariable Long buyItemListId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        basketService.deleteBasket(buyItemListId, userDetails);
    }

//    선택 상품 수정
    @PutMapping("/basketList/{buyItemListId}")
    public BasketPutDto updateBasket(@PathVariable Long buyItemListId, @RequestBody BuyListPutDto responseDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return basketService.updateBasket(buyItemListId, responseDto, userDetails);
    }

    //결제완료 주문서 저장
    @PostMapping("/basketList/order")
    public void createOrders(@RequestBody OrdersRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails ){
        basketService.createOrders(requestDto, userDetails);
    }

    //결제완료 주문서 보기
    @GetMapping("/basketList/order")
    public Orders getOrders(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return basketService.getOrders(userDetails);
    }

    //장바구니 전체 삭제(개발자용)
    @DeleteMapping("/basketList/all/{buyItemListId}")
    public void deleteBasket2(@PathVariable Long buyItemListId) {
        basketService.allDeleteBasket(buyItemListId);
    }

}
