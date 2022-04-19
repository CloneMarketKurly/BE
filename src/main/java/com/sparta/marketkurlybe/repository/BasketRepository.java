package com.sparta.marketkurlybe.repository;

import com.sparta.marketkurlybe.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
