package com.sparta.marketkurlybe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByUser_Id(String username);

    List<Basket> findAllByUserId(String username);

}
