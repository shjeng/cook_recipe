package com.my.cook_recipe.user.application;

import com.my.cook_recipe.common.constant.CommonType;
import com.my.cook_recipe.common.error.exception.CustomException;
import com.my.cook_recipe.common.provider.JwtProvider;
import com.my.cook_recipe.common.util.StringUtil;
import com.my.cook_recipe.user.domain.User;
import com.my.cook_recipe.user.infra.UserRepository;
import com.my.cook_recipe.user.ui.request.LoginRequest;
import com.my.cook_recipe.user.ui.request.SignUpRequest;
import com.my.cook_recipe.user.ui.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public boolean idDupleCheck(String id) {
        return userRepository.findByUserId(id).isPresent();
    }

    public boolean nicknameDupleCheck(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    @Transactional
    public void signUp(SignUpRequest requestDto) {
        if (idDupleCheck(requestDto.getId()) || nicknameDupleCheck(requestDto.getNickname())) {
            throw new CustomException("아이디 중복확인 또는 닉네임 중복확인을 해주세요.", true);
        }
        User user = User.builder()
                .userId(requestDto.getId())
                .password(requestDto.getPassword())
                .nickname(requestDto.getNickname())
                .role("USER")
                .build();
        userRepository.save(user);
    }

    public LoginResponse login(@Valid LoginRequest loginRequest, String referer) {
        Optional<User> userOptional = userRepository.findByUserIdAndPassword(loginRequest.getId(), loginRequest.getPassword());
        User user = optionalCheck(userOptional);
        String accessToken = jwtProvider.create(CommonType.ACCESS, user.getUserId(), user.getRole(), 600000L);
        String refreshToken = jwtProvider.create(CommonType.REFRESH, user.getUserId(), user.getRole(), 1000 * 60 * 60 * 24L);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expirationTime(1000 * 60 * 60)
                .referer(referer)
                .build();
    }

    private User optionalCheck(Optional<User> userOptional) {
        return userOptional.orElseThrow(() -> new CustomException("일치하는 회원 정보가 없습니다.", true));
    }
}
