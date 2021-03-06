package com.sparta.marketkurlybe.repository;

import com.sparta.marketkurlybe.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByBasketBasketId(Long basketId);
}
