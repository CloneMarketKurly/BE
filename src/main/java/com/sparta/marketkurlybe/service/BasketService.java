package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.BuyItemListDto;
import com.sparta.marketkurlybe.dto.BuyListPutDto;
import com.sparta.marketkurlybe.dto.OrdersRequestDto;
import com.sparta.marketkurlybe.model.*;
import com.sparta.marketkurlybe.repository.*;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BuyItemListRepository buyItemListRepository;
    private final BasketRepository basketRepository;
    private final OrdersRepository ordersRepository;

    //구매목록 저장
    @Transactional
    public void createBuyList(BuyItemListDto itemListDto, UserDetailsImpl userDetails) {

        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원정보가 존재하지 않습니다.")
        );

        Item item = itemRepository.findById(itemListDto.getItemId()).orElseThrow(
                () -> new NullPointerException("상품이 존재하지 않습니다.")
        );

        BuyItemList buyItemList = BuyItemList.builder()
                .user(user)
                .item(item)
                .count(itemListDto.getCount())
                .build();

        buyItemListRepository.save(buyItemList);
    }

    //결제전 장바구니 조회 + 징바구니 저장 + 수정
    @Transactional
    public Basket basketList(UserDetailsImpl userDetails) {

        //ToDo : Method GET
        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원정보가 존재하지 않습니다.")
        );
        List<BuyItemList> buyItemList = buyItemListRepository.findByUser_Id(user.getId());

        int deliverFee = 0;
        int sumPrice = 0;

        for (BuyItemList a : buyItemList){
            int price = a.getItem().getPrice();
            int cnt = a.getCount();
            int sum = price * cnt;
            sumPrice += sum;
        }

        if (sumPrice < 40000){
            deliverFee += 3000;
        }

        int totalPrice = deliverFee + sumPrice;


        Basket basket = Basket.builder()
                .user(user)
                .buyItemList(buyItemList)
                .sumPrice(sumPrice)
                .deliverFee(deliverFee)
                .totalPrice(totalPrice)
                .build();

        Basket findId = basketRepository.findByUser_Id(user.getId());


        //ToDo : Method POST
        //해당 유저의 장바구니 정보가 없다면, save
        //있다면 update
        if (findId == null){
            basketRepository.save(basket);
        }else {
            //ToDo : Method PUT
            basket.setBuyItemList(basket.getBuyItemList());
            basket.setDeliverFee(basket.getDeliverFee());
            basket.setSumPrice(basket.getSumPrice());
            basket.setTotalPrice(basket.getTotalPrice());
        }
        return basket;
    }

    //장바구니 전체 삭제(개발자용)
    @Transactional
    public void allDeleteBasket(Long basketId) {
        Basket basket = basketRepository.findById(basketId).orElseThrow(
                () -> new NullPointerException("장바구니가 존재하지 않습니다.")
        );
        basketRepository.delete(basket);
    }

    // 장바구니 선택 삭제
    @Transactional
    public void deleteBasket(Long buyItemListId) {
        BuyItemList buyItemList = buyItemListRepository.findById(buyItemListId).orElseThrow(
                () -> new NullPointerException("상품이 존재하지 않습니다.")
        );
        buyItemListRepository.delete(buyItemList);
    }

    //장바구니 수정(선택 수량 변경)
    @Transactional
    public Basket updateBasket(Long buyItemListId, BuyListPutDto responseDto, UserDetailsImpl userDetails) {
        BuyItemList buyItemList = buyItemListRepository.findById(buyItemListId).orElseThrow(
                () -> new NullPointerException("상품 정보가 존재하지 않습니다.")
        );
        buyItemList.setCount(responseDto.getCount());
        return basketList(userDetails);
    }

    //최종 결제 완료 주문
    @Transactional
    public void createOrders(OrdersRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원정보가 존재하지 않습니다.")
        );
        Basket basket = basketRepository.findByUser_Id(user.getId());

        Orders orders = Orders.builder()
                .basket(basket)
                .address(requestDto.getAddress())
                .build();

        ordersRepository.save(orders);
    }

    //결제완료 주문서 보기
    public Orders getOrders(UserDetailsImpl userDetails) {
        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원정보가 존재하지 않습니다.")
        );
        Basket basket = basketRepository.findByUser_Id(user.getId());
        Orders orders = ordersRepository.findByBasketBasketId(basket.getBasketId());

        return orders;
    }
}
