package com.sparta.marketkurlybe.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class JoinDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userId;

    @NotBlank(message = "닉네임는 필수 입력 값입니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "10글자 이상, 영문/숫자/특수문자(공백 제외)/2개 이상의 조합을 사용해주세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    private String passwordCheck;
}
