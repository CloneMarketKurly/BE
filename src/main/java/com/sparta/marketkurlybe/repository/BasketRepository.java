package com.sparta.marketkurlybe.repository;

import com.sparta.marketkurlybe.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByUser_Id(String username);

    List<Basket> findAllByUserId(String username);

}
