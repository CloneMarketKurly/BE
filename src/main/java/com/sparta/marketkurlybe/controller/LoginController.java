package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {
    @PostMapping("/user/login")
    public String login() {
     return "로그인";
    }

    @PostMapping("/user/join")
    public String join(@RequestBody JoinDto joinDto) {
        return "회원가입";
    }
}
