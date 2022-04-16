package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //상세페이지 조회
    @GetMapping("/item/details/{itemId}")
    public Map<String, Object> getItemDetails(@PathVariable Long itemId){
        return itemService.getItemDetails(itemId);


    }
}