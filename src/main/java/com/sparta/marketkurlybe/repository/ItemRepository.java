package com.sparta.marketkurlybe.repository;

import com.sparta.marketkurlybe.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {


}
