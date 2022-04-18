package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.JoinDto;
import com.sparta.marketkurlybe.service.UserService;
import com.sparta.marketkurlybe.validator.ErrorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/join")
    public ErrorResult join(@RequestBody JoinDto joinDto, Errors errors) {
        if(errors.hasErrors()){

        }
        userService.join(joinDto);
        return new ErrorResult(true,"회원가입 완료!");
    }
}
