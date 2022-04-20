package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.BuyItemListDto;
import com.sparta.marketkurlybe.dto.BuyListPutDto;
import com.sparta.marketkurlybe.dto.OrdersRequestDto;
import com.sparta.marketkurlybe.model.Basket;
import com.sparta.marketkurlybe.model.Orders;
import com.sparta.marketkurlybe.model.User;
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

    //장바구니 선택 삭제(삭제하면, 자동으로 장바구니 조회에 삭제된 정보 반영 됨)
    @DeleteMapping("/basketList/{buyItemListId}")
    public void deleteBasket(@PathVariable Long buyItemListId) {
        basketService.deleteBasket(buyItemListId);
    }

    //선택 상품 수정
    @PutMapping("/basketList/{buyItemListId}")
    public void updateBasket(@PathVariable Long buyItemListId, @RequestBody BuyListPutDto responseDto){
        basketService.updateBasket(buyItemListId, responseDto);
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
