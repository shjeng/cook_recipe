package com.my.cook_recipe.user.ui.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String referer;

    private final Long accessTokenExpiredMs = 600000L;
    private final Long refreshTokenExpiredMs = 1000 * 60 * 60 * 24L * 7;
}
