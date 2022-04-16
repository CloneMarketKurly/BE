package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.JoinDto;
import com.sparta.marketkurlybe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/join")
    public String join(@RequestBody JoinDto joinDto) {
        return userService.join(joinDto);
    }
}
