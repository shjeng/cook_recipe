package com.my.cook_recipe.user.ui;

import com.my.cook_recipe.common.error.exception.CustomException;
import com.my.cook_recipe.user.application.UserService;
import com.my.cook_recipe.user.ui.request.DupleCheck;
import com.my.cook_recipe.user.ui.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

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
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest requestDto){
        userService.signUp(requestDto);
        return ResponseEntity.ok().build();
    }
}
