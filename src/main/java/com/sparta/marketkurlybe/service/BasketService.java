package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.*;
import com.sparta.marketkurlybe.model.*;
import com.sparta.marketkurlybe.repository.*;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

        Optional<BuyItemList> find = buyItemListRepository.findByItem_IdAndUser_Id(itemListDto.getItemId(), user.getId());

        if (find.isPresent()){
            find.get().setCount(itemListDto.getCount()+find.get().getCount());
        }else {
            BuyItemList buyItemList = BuyItemList.builder()
                    .user(user)
                    .item(item)
                    .count(itemListDto.getCount())
                    .build();

            buyItemListRepository.save(buyItemList);
        }
    }

    //결제전 장바구니 조회 + 징바구니 저장 + 수정
    @Transactional
    public BasketDto basketList(UserDetailsImpl userDetails) {

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

        return new BasketDto(basket);
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
    public void deleteBasket(Long buyItemListId, UserDetailsImpl userDetails) {
        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원정보가 존재하지 않습니다.")
        );

        BuyItemList buyItemList = buyItemListRepository.findById(buyItemListId).orElseThrow(
                () -> new NullPointerException("상품이 존재하지 않습니다.")
        );
        buyItemListRepository.delete(buyItemList);
    }

//    장바구니 수정(선택 수량 변경)
    @Transactional
    public BasketPutDto updateBasket(Long buyItemListId, BuyListPutDto responseDto, UserDetailsImpl userDetails) {
        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원정보가 존재하지 않습니다.")
        );
        BuyItemList buyItemList = buyItemListRepository.findById(buyItemListId).orElseThrow(
                () -> new NullPointerException("상품 정보가 존재하지 않습니다.")
        );

        //해당된 친구만 보내기
        //수정된 배송비만 보낼 수 있는 로직(바뀐 친구랑 배송비 총 가격)
        buyItemList.setCount(responseDto.getCount());

        BuyItemList buyItemList2 = buyItemListRepository.findById(buyItemListId).orElseThrow(
                () -> new NullPointerException("상품 정보가 존재하지 않습니다.")
        );

        BasketDto basket = basketList(userDetails);

        BasketPutDto basketPutDto = BasketPutDto.builder()
                .sumPrice(basket.getSumPrice())
                .count(responseDto.getCount())
                .deliverFee(basket.getDeliverFee())
                .totalPrice(basket.getTotalPrice())
                .buyItemList(buyItemList2)
                .build();

        return basketPutDto;
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
