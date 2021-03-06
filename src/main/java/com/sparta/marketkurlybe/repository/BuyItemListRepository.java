package com.sparta.marketkurlybe.repository;

import com.sparta.marketkurlybe.model.BuyItemList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuyItemListRepository extends JpaRepository<BuyItemList, Long> {
    List<BuyItemList> findByUser_Id(Long userId);

    Optional<BuyItemList> findByItem_IdAndUser_Id(Long itemId, Long userId);
}
