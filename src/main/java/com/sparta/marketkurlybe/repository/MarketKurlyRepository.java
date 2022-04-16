package com.sparta.marketkurlybe.repository;

import com.sparta.marketkurlybe.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketKurlyRepository extends JpaRepository<Item, Long> {

}
