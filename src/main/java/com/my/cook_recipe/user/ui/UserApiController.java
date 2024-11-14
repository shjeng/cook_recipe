package com.my.cook_recipe.user.ui;

import com.my.cook_recipe.common.error.exception.CustomException;
import com.my.cook_recipe.user.application.UserService;
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
    @PostMapping("/join/id-check")
    public ResponseEntity<?> idDupleCheck(@RequestBody Map<String, String> requestDto){
        String id = requestDto.get("id");
        if (!StringUtils.hasText(id) || !id.matches("^[a-zA-Z0-9]*$")) {
            throw new CustomException("영문자 또는 숫자만 입력 가능합니다", true);
        }
        if (userService.idDupleCheck(id)) {
            id = null;
        }
        return ResponseEntity.ok(id);
    }
}
