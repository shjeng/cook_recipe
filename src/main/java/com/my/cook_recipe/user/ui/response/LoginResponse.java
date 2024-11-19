package com.my.cook_recipe.user.ui.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token; // front에 넘겨줄 token
    private String refreshToken; // 리프레시 토큰
    private int expirationTime; // 토큰 만료 시간
}
