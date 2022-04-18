package com.sparta.marketkurlybe.validator;

import com.sparta.marketkurlybe.dto.JoinDto;
import com.sparta.marketkurlybe.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserValidator {

    public static void checkUser(Optional<User> found, JoinDto joinDto) {
        if(found.isPresent()){
            throw new NullPointerException("아이디가 중복됩니다.");
        }
        if(!joinDto.getPasswordCheck().equals(joinDto.getPassword())){
            throw new NullPointerException("비밀번호 확인이 다릅니다.");
        }
    }
}
