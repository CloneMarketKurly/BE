package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.model.Item;
import com.sparta.marketkurlybe.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //메인페이지 조회
//    @GetMapping("/")
//    public List<Item> getItemList(){
//        return itemService.getItemList();
//    }


    @GetMapping("/")
    public List<Item> getItemList(){
        return itemService.getItemList();
    }


    //상세페이지 조회
    @GetMapping("/item/details/{itemId}")
    public Map<String, Object> getItemDetails(@PathVariable Long itemId){
        System.out.println(itemId);
        return itemService.getItemDetails(itemId);
    }
}