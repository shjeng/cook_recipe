package com.my.cook_recipe.user.ui;

import com.my.cook_recipe.common.error.exception.CustomException;
import com.my.cook_recipe.common.util.StringUtil;
import com.my.cook_recipe.user.application.UserService;
import com.my.cook_recipe.user.ui.request.DupleCheck;
import com.my.cook_recipe.user.ui.request.LoginRequest;
import com.my.cook_recipe.user.ui.request.SignUpRequest;
import com.my.cook_recipe.user.ui.response.TokenResponse;
import com.my.cook_recipe.user.ui.response.UserResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/te")
public class UserApiControllerTest {

    private final UserService userService;

    @GetMapping("/te")
    public ResponseEntity<UserResponse> getUserInfoByToken(HttpServletRequest request) {
        String access = request.getHeader("access");
        UserResponse result = userService.getUserById("test");
        return ResponseEntity.ok(result);
    }


    @PostMapping("/sign-up/id-check")
    public ResponseEntity<String> idDupleCheck(@RequestBody DupleCheck requestDto) {
        String id = requestDto.getStr();
        if (!StringUtils.hasText(id) || !id.matches("^[a-zA-Z0-9]*$")) {
            throw new CustomException("영문자 또는 숫자만 입력 가능합니다", true);
        }
        if (userService.idDupleCheck(id)) {
            id = null;
        }
        return ResponseEntity.ok(id);
    }

    @PostMapping("/sign-up/nickname-check")
    public ResponseEntity<String> nicknameDupleCheck(@RequestBody DupleCheck requestDto) {
        String nickname = requestDto.getStr();
        if (!StringUtils.hasText(nickname) || !nickname.matches("^[a-zA-Z0-9가-힣]*$")) {
            throw new CustomException("닉네임은 영문자, 숫자 또는 한글만 입력 가능합니다", true);
        }
        if (userService.nicknameDupleCheck(nickname)) {
            nickname = null;
        }
        return ResponseEntity.ok(nickname);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest requestDto){
        if (!requestDto.getId().equals(requestDto.getIdCheck())) {
            throw new CustomException("중복체크된 id와 가입하려는 id가 일치하지 않음.", false);
        }
        if (!requestDto.getNickname().equals(requestDto.getNicknameCheck())) {
            throw new CustomException("중복체크된 닉네임 가입하려는 닉네임이 일치하지 않음.", false);
        }
        if (StringUtil.notEquals(requestDto.getPassword(), requestDto.getPasswordCheck())) {
            throw new CustomException("비밀번호가 일치하지 않습니다.", true);
        }
        userService.signUp(requestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        String referer = request.getHeader("Referer");
        TokenResponse result = userService.login(loginRequest, referer);
        return ResponseEntity.ok(result);
    }
}
