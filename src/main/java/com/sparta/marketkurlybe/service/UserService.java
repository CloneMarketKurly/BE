package com.sparta.marketkurlybe.service;

import com.sparta.marketkurlybe.dto.JoinDto;
import com.sparta.marketkurlybe.model.User;
import com.sparta.marketkurlybe.repository.UserRepository;
import com.sparta.marketkurlybe.validator.ErrorResult;
import com.sparta.marketkurlybe.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public ErrorResult join(JoinDto joinDto) {
        // Repository에서 Optional 타입으로 찾는다.
        Optional<User> found = userRepository.findByUserId(joinDto.getUserId());
        // 아이디 중복 검사, 비밀번호 확인 검사
        UserValidator.checkUser(found, joinDto);
        // User Entity 에 맞게 받아온 정보에서 빼낸다.
        String userId = joinDto.getUserId();
        String userName = joinDto.getUserName();
        String password = passwordEncoder.encode(joinDto.getPassword());
        // User 객체로 만든 뒤 Repository 에 저장한다.
        User user = new User(userId, userName, password);
        userRepository.save(user);

        return new ErrorResult(true,"회원가입 완료!");
    }
}
