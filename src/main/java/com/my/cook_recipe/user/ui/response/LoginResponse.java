package com.my.cook_recipe.user.ui.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Integer expirationTime;
    private String referer;
}
