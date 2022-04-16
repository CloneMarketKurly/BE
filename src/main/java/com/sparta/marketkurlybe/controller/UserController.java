package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.JoinDto;
import com.sparta.marketkurlybe.dto.UserDto;
import com.sparta.marketkurlybe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestBody UserDto userDto) {
        userService.login(userDto);
        return "로그인";
    }

    @PostMapping("/user/join")
    public String join(@RequestBody JoinDto joinDto) {
        return userService.join(joinDto);
    }
}
