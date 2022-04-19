package com.sparta.marketkurlybe.controller;


import com.sparta.marketkurlybe.dto.BoardDto;
import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.HelpService;
import com.sparta.marketkurlybe.validator.ErrorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelpController {
    private final HelpService helpService;

    @PostMapping("/item/details/{commentId}/help")
    public Boolean help(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return false;
        }
        return helpService.help(commentId, userDetails.getUsername());
    }

    @PostMapping("/item/details/{detailId}/board")
    public ErrorResult createBoard(@PathVariable Long detailId, @RequestBody BoardDto bordDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return new ErrorResult(false, "로그인한 사용자가 없습니다.");
        }
        return helpService.createBoard(detailId, bordDto, userDetails);
    }
}
