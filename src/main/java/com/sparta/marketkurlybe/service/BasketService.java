//package com.sparta.marketkurlybe.service;
//
//import com.sparta.marketkurlybe.dto.BuyItemListDto;
//import com.sparta.marketkurlybe.dto.BuyListResponseDto;
//import com.sparta.marketkurlybe.model.Basket;
//import com.sparta.marketkurlybe.model.BuyItemList;
//import com.sparta.marketkurlybe.model.Item;
//import com.sparta.marketkurlybe.model.User;
//import com.sparta.marketkurlybe.repository.BasketRepository;
//import com.sparta.marketkurlybe.repository.BuyItemListRepository;
//import com.sparta.marketkurlybe.repository.ItemRepository;
//import com.sparta.marketkurlybe.repository.UserRepository;
//import com.sparta.marketkurlybe.security.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class BasketService {
//
//    private final UserRepository userRepository;
//    private final ItemRepository itemRepository;
//    private final BuyItemListRepository buyItemListRepository;
//    private final BasketRepository basketRepository;
//
//    //구매목록
//    @Transactional
//    public void createBuyList(BuyItemListDto itemListDto, UserDetailsImpl userDetails) {
//
//        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
//                () -> new NullPointerException("회원정보가 존재하지 않습니다.")
//        );
//        Item item = itemRepository.findById(itemListDto.getItemId()).orElseThrow(
//                () -> new NullPointerException("상품이 존재하지 않습니다.")
//        );
//
//        BuyItemList buyItemList = BuyItemList.builder()
//                .item(item)
//                .count(itemListDto.getCount())
//                .user(user)
//                .build();
//
//        buyItemListRepository.save(buyItemList);
//    }
//
//    //결제전 장바구니 조회 및 저장
//    @Transactional
//    public Basket basketList(UserDetailsImpl userDetails) {
//        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
//                () -> new NullPointerException("회원정보가 존재하지 않습니다.")
//        );
//        List<BuyItemList> buyItemList = buyItemListRepository.findByUser_Id(user.getId());
//        int deliverFee = 0;
//        int sumPrice = 0;
//
//        for (BuyItemList a : buyItemList){
//            int price = a.getItem().getPrice();
//            int cnt = a.getCount();
//            int sum = price * cnt;
//            sumPrice += sum;
//        }
//
//        if (sumPrice < 40000){
//            deliverFee += 3000;
//        }else {
//            deliverFee = 0;
//        }
//
//        int totalPrice = deliverFee + sumPrice;
//
//
//        Basket basket = Basket.builder()
//                .buyItemList(buyItemList)
//                .sumPrice(sumPrice)
//                .deliverFee(deliverFee)
//                .totalPrice(totalPrice)
//                .build();
//
//        basketRepository.save(basket);
//
//        return basket;
//    }
//
////    //장바구니 전체 삭제
////    @Transactional
////    public void allDeleteBasket(Long basketId, UserDetailsImpl userDetails) {
////        User user = userRepository.findByUserId(userDetails.getUsername()).orElseThrow(
////                () -> new NullPointerException("회원 정보가 존재하지 않습니다.")
////        );
////
////        Basket basket = basketRepository.findById(basketId).orElseThrow(
////                () -> new NullPointerException("장바구니가 존재하지 않습니다.")
////        );
////
////        basketRepository.delete(basket);
////
////    }
//
//    //장바구니 선택 삭제
//    @Transactional
//    public void deleteBasket(Long buyItemListId) {
//        BuyItemList buyItemList = buyItemListRepository.findById(buyItemListId).orElseThrow(
//                () -> new NullPointerException("상품 정보가 존재하지 않습니다.")
//        );
//        buyItemListRepository.delete(buyItemList);
//    }
//
//    //장바구니 수정(선택 수량 변경)
//    @Transactional
//    public void updateBasket(Long buyItemListId, BuyListResponseDto responseDto) {
//        BuyItemList buyItemList = buyItemListRepository.findById(buyItemListId).orElseThrow(
//                () -> new NullPointerException("상품 정보가 존재하지 않습니다.")
//        );
//        buyItemList.setCount(responseDto.getCount());
//    }
//}
