package com.sparta.marketkurlybe.controller;


import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.HelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelpController {
    private final HelpService helpService;

    @PostMapping("/item/details/{itemId}/help")
    public Boolean help(@PathVariable Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return false;
        }
        return helpService.help(itemId, userDetails.getUsername());
    }
}
