package com.sparta.marketkurlybe.controller;

import com.sparta.marketkurlybe.dto.JoinDto;
import com.sparta.marketkurlybe.service.UserService;
import com.sparta.marketkurlybe.validator.ErrorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/join")
    public ErrorResult join(@Valid @RequestBody JoinDto joinDto, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return new ErrorResult(false, message);
        }
        return userService.join(joinDto);
    }
}
