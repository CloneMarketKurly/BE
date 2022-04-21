package com.sparta.marketkurlybe.controller;


import com.sparta.marketkurlybe.security.UserDetailsImpl;
import com.sparta.marketkurlybe.service.HelpService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/*
* 작성자: 이현재
* 기능명: 도움이돼요
* 기능 설명: POST 요청 시 유저가 도움이돼요 버튼을 눌렀는지 확인 후 누른 상태와 도움이돼요 카운트를 변경하여 클라이언트로 반환한다.
* 업데이트 날짜: 2022.04.20
*/
@RequiredArgsConstructor
@RestController
public class HelpController {

    private final HelpService helpService;

    @PostMapping("/item/details/{itemId}/{commentId}/help")
    public Map<String, Object> help(@PathVariable Long itemId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return Collections.emptyMap();
        }
        return helpService.help(commentId, itemId, userDetails.getUsername());
    }

}
