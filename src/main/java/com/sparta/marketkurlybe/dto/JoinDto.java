package com.sparta.marketkurlybe.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class JoinDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userId;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{3,}", message = "비밀번호는 3자 이상 및 영문 대 소문자, 숫자를 사용하세요.")
    private String password;


    private String passwordCheck;
}
