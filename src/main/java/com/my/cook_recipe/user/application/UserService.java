package com.my.cook_recipe.user.application;

import com.my.cook_recipe.common.error.exception.CustomException;
import com.my.cook_recipe.common.util.StringUtil;
import com.my.cook_recipe.user.domain.User;
import com.my.cook_recipe.user.infra.UserRepository;
import com.my.cook_recipe.user.ui.request.LoginRequest;
import com.my.cook_recipe.user.ui.request.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

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
                .build();
        userRepository.save(user);
    }

    public String login(@Valid LoginRequest loginRequest) {
        return null;
    }
}
