//package com.sparta.marketkurlybe.model;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.marketkurlybe.UserDto;
//import com.sparta.marketkurlybe.dto.JoinDto;
//import lombok.Builder;
//import lombok.Getter;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class UserTest {
//    // given
//    // when
//    // then
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private HttpHeaders headers;
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @BeforeEach
//    public void setup() {
//        headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//    }
//
//    @Nested
//    @DisplayName("유저 회원가입")
//    class RegisterUser {
//        @Test
//        @Order(1)
//        @DisplayName("유저 등록")
//        void test1() throws JsonProcessingException {
//            // given
//            UserJoinDto userRequest = UserJoinDto.builder()
//                    .userId(null)
//                    .userName("devlee")
//                    .password("a123456789")
//                    .passwordCheck("a123456789")
//                    .build();
//
//            String requestBody = mapper.writeValueAsString(userRequest);
//            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
//
//            // when
//            ResponseEntity<UserJoinDto> response = restTemplate.postForEntity(
//                    "/user/join",
//                    request,
//                    UserJoinDto.class);
//
//            // then
//            assertEquals(HttpStatus.OK, response.getStatusCode());
//
//            RestaurantDto restaurantResponse = response.getBody();
//            assertNotNull(restaurantResponse);
//            assertTrue(restaurantResponse.id > 0);
//            assertEquals(restaurantRequest.name, restaurantResponse.name);
//            assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
//            assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
//
//            // 음식점 등록 성공 시, registeredRestaurants 에 추가
//            registeredRestaurants.add(restaurantResponse);
//        }
//    }
//
//    @Getter
//    @Builder
//    class UserJoinDto {
//        @NotBlank(message = "아이디는 필수 입력 값입니다.")
//        private String userId;
//
//        @NotBlank(message = "닉네임는 필수 입력 값입니다.")
//        private String userName;
//
//        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{10,}$|^(?=.*\\d)(?=.*[$@$!%*#?&])[\\d$@$!%*#?&]{10,}$|^(?=.*[$@$!%*#?&])(?=.*[A-Za-z])[A-Za-z$@$!%*#?&]{10,}$|^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,}$", message = "10글자 이상, 영문/숫자/특수문자(공백 제외)/2개 이상의 조합을 사용해주세요.")
//        private String password;
//
//        @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
//        private String passwordCheck;
//    }
//
//}